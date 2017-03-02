package com.krut.caleb_router.networks.datagram;

import com.krut.caleb_router.UI.UIManager;
import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.datagram_fields.CRC;
import com.krut.caleb_router.networks.datagram_fields.DatagramPayloadField;
import com.krut.caleb_router.networks.datagram_fields.LL2PAddressField;
import com.krut.caleb_router.networks.datagram_fields.LL2PTypeField;
import com.krut.caleb_router.networks.tablerecord.Datagram;
import com.krut.caleb_router.support.Factory;
import com.krut.caleb_router.support.Utilities;

import static com.krut.caleb_router.networks.Constants.LL2P_ADDRESS_FIELD_LENGTH;
import static com.krut.caleb_router.networks.Constants.LL2P_CRC_ADDRESS;
import static com.krut.caleb_router.networks.Constants.LL2P_CRC_FIELD_LENGTH;
import static com.krut.caleb_router.networks.Constants.LL2P_DESTINATION_ADDRESS;
import static com.krut.caleb_router.networks.Constants.LL2P_DEST_ADDRESS_OFFSET;
import static com.krut.caleb_router.networks.Constants.LL2P_PAYLOAD_OFFSET;
import static com.krut.caleb_router.networks.Constants.LL2P_SOURCE_ADDRESS;
import static com.krut.caleb_router.networks.Constants.LL2P_SRC_ADDRESS_OFFSET;
import static com.krut.caleb_router.networks.Constants.LL2P_TYPE_ADDRESS;
import static com.krut.caleb_router.networks.Constants.LL2P_TYPE_FIELD_LENGTH;
import static com.krut.caleb_router.networks.Constants.LL2P_TYPE_OFFSET;

/**
 * Created by caleb.krut on 2/2/2017.
 */

public class LL2PFrame implements Datagram {
    //^^^^^^^^^^^^^^^^^^^^Getters^^^^^^^^^^^^^^^^^^^^^^
    public LL2PAddressField getDestinationAddress() {
        return destinationAddress;
    }

    public LL2PAddressField getSourceAddress() {
        return sourceAddress;
    }

    public LL2PTypeField getType() {
        return type;
    }

    public DatagramPayloadField getPayload() {
        return payload;
    }

    public CRC getCrc() {
        return crc;
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^^^^^
    private LL2PAddressField destinationAddress;

    private LL2PAddressField sourceAddress;

    private LL2PTypeField type;

    private DatagramPayloadField payload;

    private CRC crc;

    //^^^^^^^^^^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^^^^^
    //Testing constructor
    public LL2PFrame(LL2PAddressField destAdd, LL2PAddressField srcAdd,
                     LL2PTypeField tp, DatagramPayloadField payld, CRC checksum){
        destinationAddress = destAdd;
        sourceAddress = srcAdd;
        type = tp;
        payload = payld;
        crc = checksum;
    }

    //Constructor
    public LL2PFrame(byte[] ll2pframe) {

        //Partitions the correct sets of bytes to each field which are retreived from the factory
        String transmissionString = new String(ll2pframe);
        Integer length = transmissionString.length();
        Factory factory = Factory.getInstance();

        //Sets aside the first 6 bytes for the destination address
        destinationAddress = (LL2PAddressField) (factory.getDatagramHeaderField((LL2P_DESTINATION_ADDRESS),
                transmissionString.substring(LL2P_DEST_ADDRESS_OFFSET, LL2P_DEST_ADDRESS_OFFSET +
                        LL2P_ADDRESS_FIELD_LENGTH*2)));

        //Sets aside the second 6 bytes for the source address
        sourceAddress =  (LL2PAddressField) (factory.getDatagramHeaderField(LL2P_SOURCE_ADDRESS,
                transmissionString.substring(LL2P_SRC_ADDRESS_OFFSET, LL2P_SRC_ADDRESS_OFFSET +
                        LL2P_ADDRESS_FIELD_LENGTH*2)));

        //Sets aside the next 4 bytes for the type
        type =  (LL2PTypeField) (factory.getDatagramHeaderField(LL2P_TYPE_ADDRESS,
                transmissionString.substring(LL2P_TYPE_OFFSET, LL2P_TYPE_OFFSET +
                        LL2P_TYPE_FIELD_LENGTH*2)));

        //Sets aside all the remaining bytes except for the last 4 for the payload
        payload = makePayloadField(type, transmissionString.substring(LL2P_PAYLOAD_OFFSET,
                length-LL2P_CRC_FIELD_LENGTH*2));

        //Sets aside the final 4 bytes for the CRC
        crc =  (CRC)(factory.getDatagramHeaderField(LL2P_CRC_ADDRESS,
                transmissionString.substring(length-LL2P_CRC_FIELD_LENGTH*2, length)));
    }

    //Sets up the payload
    //This has not been tested as well as other sections
    private DatagramPayloadField makePayloadField(LL2PTypeField typeField, String stringConvert){
        return new DatagramPayloadField(Integer.valueOf(type.toString()), stringConvert);
    }

    //*********************Datagram methods**********************
    public String toString(){
        return toTransmissionString();
    }

    public String toTransmissionString(){
        //return(destinationAddress.toAsciiString() + sourceAddress.toAsciiString() + type.toAsciiString() + payload.toAsciiString() + crc.toAsciiString());
        return destinationAddress.toTransmissionString()+sourceAddress.toTransmissionString()+payload.toTransmissionString()+crc.toTransmissionString();
    }

    public String toHexString(){
        return Utilities.convertStringToHex(toTransmissionString());
    }

    public String toProtocolExplanationString() {
        return "LL2P Frame: " + type;
    }

    public String toSummaryString() {
        return destinationAddress.toHexString() + " | " + sourceAddress.toHexString() + " | " + "LL2P Frame "+ "| " + payload.explainSelf();
    }



}