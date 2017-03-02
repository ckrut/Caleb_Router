package com.krut.caleb_router.networks.datagram_fields;

import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.support.Utilities;

/**
 * Created by caleb.krut on 2/2/2017.
 */

public class LL2PTypeField implements DatagramHeaderField{
    //^^^^^^^^^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^^^^^
    private Integer type;

    private String explanation;

    //^^^^^^^^^^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^^^^^

    //Constructor
    public LL2PTypeField(Integer typeValue){
        type = typeValue;
        setExplanation();
    }

    //Constructor that works with a string
    public LL2PTypeField(String typeValueString){
        type = Integer.valueOf(typeValueString,16);
        setExplanation();
    }

    //Creates an explanation String by comparing the passed type to determine the type of packet
    private void setExplanation(){
        switch (type){
            case Constants.LL2P_TYPE_LL3P: explanation = "LL3P type (0x"+type+")";
                break;
            case Constants.LL2P_TYPE_RESERVED: explanation = "reserved type (0x"+type+")";
                break;
            case Constants.LL2P_TYPE_LRP: explanation = "LRP (0x"+type+")";
                break;
            case Constants.LL2P_TYPE_ECHO_REQUEST: explanation = "Echo Request (0x"+type+")";
                break;
            case Constants.LL2P_TYPE_ECHO_REPLY: explanation = "Echo Reply (0x"+type+")";
                break;
            case Constants.LL2P_TYPE_ARP_REQUEST: explanation = "ARP request (0x"+type+")";
                break;
            case Constants.LL2P_TYPE_ARP_REPLY: explanation = "ARP Reply (0x"+type+")";
                break;
            case Constants.LL2P_TYPE_TEXT : explanation = "Text (0x"+type+")";
                break;
        }
    }

    //^^^^^^^^^^^^^^^DatagramHeaderField Methods^^^^^^^^^^^^^^^^^
    public String toString(){
        return toTransmissionString();
    }

    public String toTransmissionString(){
        return type.toString();
    }

    public String toHexString(){
        String hex = Integer.toHexString(type);
        hex = Utilities.padHexString(hex,Constants.LL2P_TYPE_FIELD_LENGTH*2);
        return hex;
    }

    public String explainSelf(){
        return explanation;
    }

    public String toAsciiString(){
        return Utilities.convertToASCII(Integer.toHexString(type));
    }
}