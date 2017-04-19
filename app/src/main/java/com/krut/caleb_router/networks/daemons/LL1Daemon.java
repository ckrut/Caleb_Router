package com.krut.caleb_router.networks.daemons;

import android.os.AsyncTask;
import android.util.Log;

import com.krut.caleb_router.UI.UIManager;
import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.datagram.LL2PFrame;
import com.krut.caleb_router.networks.table.Table;
import com.krut.caleb_router.networks.tablerecord.AdjacencyRecord;
import com.krut.caleb_router.support.LabException;
import com.krut.caleb_router.support.PacketInformation;
import com.krut.caleb_router.support.Factory;
import com.krut.caleb_router.support.FrameLogger;
import com.krut.caleb_router.support.GetIPAddress;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import static com.krut.caleb_router.networks.Constants.UDP_PORT;

/**
 * Created by caleb.krut on 3/9/2017.
 */
public class LL1Daemon extends Observable implements Observer {
    //^^^^^^^^^^^^^^^^^^^^^^FIELDS^^^^^^^^^^^^^^^^^^^^^^
    private static LL1Daemon ourInstance = new LL1Daemon();
    private DatagramSocket receiveSocket;
    private DatagramSocket sendSocket;
    private Table adjacencyTable;
    private GetIPAddress nameserver;
    private UIManager uiManager;
    private Factory factory;
    //private LL2PDaemon ll2pDaemon;

    //^^^^^^^^^^^^^^^^^^^^^METHODS^^^^^^^^^^^^^^^^^^^^^^
    //empty constructor
    private LL1Daemon() {
    }

    public static LL1Daemon getInstance() {
        return ourInstance;
    }

    @Override
    public void update(Observable o, Object arg) {
        openSockets();
        adjacencyTable = new Table();
        nameserver = GetIPAddress.getInstance();
        factory = Factory.getInstance();
        addObserver(FrameLogger.getInstance());
        uiManager = UIManager.getInstance();
        //Doesn't exist yet
        //ll2pDaemon = LL2PDaemon.getInstance();
        new ReceiveUnicastFrame().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, receiveSocket);
    }

    public void removeAdjacency(AdjacencyRecord recordToRemove) throws LabException {
        adjacencyTable.removeItem(recordToRemove.getKey());
    }

    public Table getAdjacencyTable() {
        return adjacencyTable;
    }

    public void addAdjacency(String ll2pAddress, String ipAddress) {
        Object[] Data = new Object[2];
        Data[0] = GetIPAddress.getInstance().getInetAddress(ipAddress);
        Data[1] = Integer.parseInt(ll2pAddress);
        AdjacencyRecord adjacencyRecord = (AdjacencyRecord) factory.getInstance().
                getTableRecord(Constants.TABLE_RECORD_TYPE_ADJACENCY, Data);
        adjacencyRecord.setIpAddress(nameserver.getInetAddress(ipAddress));
        adjacencyRecord.setLl2pAddress(Integer.valueOf(ll2pAddress, 16));

        adjacencyTable.addItem(adjacencyRecord);

        setChanged();
        notifyObservers(adjacencyRecord);
    }

    private void openSockets() {
        try {
            sendSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Log.d(Constants.logTag, "Send Socket Setup Successful");

        try {
            receiveSocket = new DatagramSocket(UDP_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Log.d(Constants.logTag, "Receive Socket Setup Successful");

    }

    public void sendFrame(LL2PFrame ll2p) {

        String frameToSend = new String(ll2p.toString());
        boolean foundValidAddress = true;

        uiManager.raiseToast("Sending Packet");
        InetAddress IPAddress = null;

        try {
            AdjacencyRecord record = (AdjacencyRecord) adjacencyTable.
                    getItem(Integer.valueOf(ll2p.getDestinationAddress().toString(), 16));
            IPAddress = record.getIpAddress();
        } catch (LabException e) {
            foundValidAddress = false;
        }

        if (foundValidAddress) {
            DatagramPacket sendPacket = new DatagramPacket(frameToSend.getBytes(),
                    frameToSend.length(), IPAddress, UDP_PORT);
            new SendUnicastFrame().execute(new PacketInformation(sendSocket, sendPacket));
        }
        setChanged();
        notifyObservers(ll2p);
    }

    public AdjacencyRecord getAdjacencyRecord(Integer recordNumber) {
        AdjacencyRecord record=null;
        try {
            record = (AdjacencyRecord) adjacencyTable.getItem(recordNumber);
        } catch (LabException e) {
            e.printStackTrace();
        }
        return record;
    }

    //^^^^^^^^^^^^^^^^^Interface Methods^^^^^^^^^^^^^^^^^
    protected class SendUnicastFrame extends AsyncTask<PacketInformation, Void, Void> {
        @Override
        protected Void doInBackground(PacketInformation... packetInformation) {
            PacketInformation packetInfo = packetInformation[0];
            try {
                packetInfo.getSocket().send(packetInfo.getPacket());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private class ReceiveUnicastFrame extends AsyncTask<DatagramSocket, Void, byte[]> {

        @Override
        protected byte[] doInBackground(DatagramSocket... socketList) {
            byte[] receiveData = new byte[1024];   // byte array to store received bytes.
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                receiveSocket.receive(receivePacket); // check the socket for packet.
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new String(receivePacket.getData()).substring(0, receivePacket.getLength()).getBytes();

        }
    }
}