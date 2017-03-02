package com.krut.caleb_router.networks.datagram_fields;

/**
 * Created by caleb.krut on 2/2/2017.
 */

public interface DatagramHeaderField {
    String toString();

    /*Will return integers as hex. If it receives ASCII string, it
    * convert it to the hex value of ASCII.
    **/
    String toHexString();

    /*Returns a format to display the content and meaning of the field.
    * For example, if an LL2P TYPE FIELD contained 0x8001 for LL3P, it
    * would return "LL2P Type Field. Value = 0x8001, payload is an LL3P Packet
    **/
    String explainSelf();

    //Converts hex bytes to ASCII characters
    String toAsciiString();

    String toTransmissionString();
}

