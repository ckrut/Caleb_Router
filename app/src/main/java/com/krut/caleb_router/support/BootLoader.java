package com.krut.caleb_router.support;

import java.util.Observable;
import android.app.Activity;
import android.util.Log;

import com.krut.caleb_router.UI.UIManager;
import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.daemons.LL1Daemon;
import com.krut.caleb_router.networks.datagram.LL2PFrame;
import com.krut.caleb_router.networks.table.Table;
import com.krut.caleb_router.networks.tablerecord.AdjacencyRecord;
import com.krut.caleb_router.support.ParentActivities;

import static com.krut.caleb_router.networks.Constants.LL2P_ADDRESS_FIELD_LENGTH;
import static com.krut.caleb_router.networks.Constants.LL2_ADDRESS;
import static com.krut.caleb_router.networks.Constants.TABLE_RECORD_TYPE_ADJACENCY;
import static com.krut.caleb_router.networks.Constants.logTag;
import static com.krut.caleb_router.support.Utilities.padHexString;


/**
 * Created by caleb.krut on 1/12/2017.
 */

//will load up the router upon starting the program
public class BootLoader extends Observable {

    //Constructor for the boot loader
    public BootLoader(Activity activity){
        bootRouter(activity);
    }

    //Boots the router
    private void bootRouter(Activity myActivity){
        ParentActivities.setParentActivity(myActivity);
        addObserver(Constants.getInstance());
        addObserver(UIManager.getInstance());

        //Boots the router
        setChanged();
        notifyObservers();
        Log.i(logTag, "Router is booted!  IP Address is "+Constants.IP_ADDRESS);
        //UIManager.getInstance().raiseToast("Router is booted!");

        try {
            testRouterComponents();
        } catch (LabException e) {
            e.printStackTrace();
        }
    }

    /*Test for lab 3
    private void testRouterComponents(){
        String frameString = "0011223141598008Hi0000";
        LL2PFrame frame = new LL2PFrame(frameString.getBytes());
        Log.i(Constants.logTag, "Frame is: "+frame.toString());
        Log.i(Constants.logTag, "Frame hex is: "+frame.toHexString());
    }*/

    //Test for lab 4
    private void testRouterComponents() throws LabException{
        String frameString = "0011223141598008Hi0000";
        LL2PFrame frame = new LL2PFrame(frameString.getBytes());

        UIManager.getInstance().raiseToast("Frame is: " + frame.toTransmissionString());
        UIManager.getInstance().raiseToast("Protocol Explanation: " + frame.toProtocolExplanationString());//explanation protocol
        UIManager.getInstance().raiseToast("The payload is: " + frame.getPayload().toAsciiString());//ascii characters
        UIManager.getInstance().raiseToast("Hex Characters are: " + frame.toHexString());//Hex String
        UIManager.getInstance().raiseToast("Summary String is: " + frame.toSummaryString());//Summary

        LL1Daemon ll1Daemon = LL1Daemon.getInstance();

        AdjacencyRecord adjacencyRecord = new AdjacencyRecord(GetIPAddress.getInstance().getInetAddress("10.1.1.1"), 0x314159);
        AdjacencyRecord adjacencyRecord1 = new AdjacencyRecord(GetIPAddress.getInstance().getInetAddress("10.1.1.2"), 0x2E1DA1);
        Log.d(logTag, "Adjacency Records Created");

        Table table = new Table();
        Log.d(logTag, "Table Created");
        table.addItem(adjacencyRecord);
        table.addItem(adjacencyRecord1);
        Log.d(logTag, "adjacency records added to the table");
        table.removeItem(0x314159);
        Log.d(logTag, "adjacency record removed from the table");

    }
}