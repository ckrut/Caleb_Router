package com.krut.caleb_router.networks.datagram_fields;

import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.support.Utilities;

/**
 * Created by caleb.krut on 2/2/2017.
 */

public class CRC implements DatagramHeaderField {
    //^^^^^^^^^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^^^^^
    private String crcValue;

    //^^^^^^^^^^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^^^^
    //Constructor
    public CRC(String typeValueString){
        this.crcValue = typeValueString.substring(0, Constants.LL2P_CRC_FIELD_LENGTH * 2 );
    }

    //^^^^^^^^^^^^^^^DatagramHeaderField Methods^^^^^^^^^^^^^^^^
    public String toString(){
        return toTransmissionString();
    }

    public String toTransmissionString(){
        return toHexString();
    }

    public String toHexString(){
        return Utilities.padHexString(crcValue,Constants.LL2P_CRC_FIELD_LENGTH);
    }

    public String explainSelf(){
        return "LL2P CRC: " + crcValue;
    }

    public String toAsciiString(){
        return Utilities.convertToASCII(crcValue);
    }

}