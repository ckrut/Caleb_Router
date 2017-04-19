package com.krut.caleb_router.support;

import com.krut.caleb_router.networks.tablerecord.Datagram;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by caleb.krut on 3/9/2017.
 */

public class PacketInformation {
    //^^^^^^^^^^^^^^^^^^^FIELDS^^^^^^^^^^^^^^^^^
    private DatagramSocket socket;
    private DatagramPacket packet;

    //^^^^^^^^^^^^^^^^^^^METHODS^^^^^^^^^^^^^^^^
    public PacketInformation(DatagramSocket socket, DatagramPacket packet){
        this.socket = socket;
        this.packet = packet;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public DatagramPacket getPacket() {
        return packet;
    }
}
