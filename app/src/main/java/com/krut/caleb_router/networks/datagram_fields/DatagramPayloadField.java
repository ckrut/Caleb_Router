package com.krut.caleb_router.networks.datagram_fields;

import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.datagram.TextDatagram;
import com.krut.caleb_router.networks.tablerecord.Datagram;

import java.net.DatagramPacket;

/**
 * Created by caleb.krut on 2/2/2017.
 */

public class DatagramPayloadField implements DatagramHeaderField {
    //^^^^^^^^^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^^^^^
    //Contains the integer value of the address
    private Datagram packet;
    private String text;

    //^^^^^^^^^^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^^^^^

    //Constructor
    public DatagramPayloadField(Datagram pkt) {
        packet = pkt;
    }

    //Optional Constructor
    public DatagramPayloadField(int payloadType, String text) {
        this.text=text;
        Datagram result;
        switch (payloadType) {
            case Constants.LL2P_TYPE_TEXT:
                packet = new TextDatagram(text);
                break;
            case Constants.LL2P_ARP_ADDRESS:
            case Constants.LL2P_TYPE_LL3P:
            case Constants.LL2P_TYPE_RESERVED:
            case Constants.LL2P_TYPE_LRP:
            case Constants.LL2P_TYPE_ECHO_REQUEST:
            case Constants.LL2P_TYPE_ECHO_REPLY:
            case Constants.LL2P_TYPE_ARP_REQUEST:
            case Constants.LL2P_TYPE_ARP_REPLY:
                result = null;
            default:
                result = null;
        }
    }

    //returns datagram object
    public Datagram getPayload() {
        packet = new TextDatagram(text);
        return packet;
    }

    //^^^^^^^^^^^^^^^DatagramHeaderField Methods^^^^^^^^^^^^^^^^^
    public String toString() {
        return toTransmissionString();
    }

    public String toTransmissionString() {
        return text;
    }

    public String toHexString() {
        return packet.toHexString();
    }

    public String explainSelf() {
        return packet.toProtocolExplanationString();
    }

    public String toAsciiString() {
        StringBuilder output = new StringBuilder();
        String hex = packet.toHexString();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
}

