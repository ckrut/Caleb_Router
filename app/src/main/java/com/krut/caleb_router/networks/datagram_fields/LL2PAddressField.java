package com.krut.caleb_router.networks.datagram_fields;

import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.support.Utilities;

/**
 * Created by caleb.krut on 1/26/2017.
 */

public class LL2PAddressField implements DatagramHeaderField{
    //^^^^^^^^^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^^^^^
    //Contains the integer value of the address
    private Integer address;

    //True if the address is a source address
    private boolean isSourceAddress;

    //The string explanation of the contents
    private String explanation;

    //^^^^^^^^^^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^^^^^
    //Constructor
    public LL2PAddressField(int address, boolean isSource){
        this.address = address;
        this.isSourceAddress = isSource;
        setExplanation();
    }

    //A second constuctor that will accept a string instead of an integer
    public LL2PAddressField(String address, boolean isSource){
        this.address = Integer.valueOf(address, 16);
        this.isSourceAddress = isSource;
        setExplanation();
    }

    //Uses the internal fields to create an explanation string
    private void setExplanation(){
        if(isSourceAddress== true)
            explanation = "Source LL2P Address: " + address;
        else
            explanation = "Destination LL2P Address: " + address;
    }

    //Returns true if this is a sourse address
    public Boolean isSourceAddressField(){
        return isSourceAddress;
    }

    //^^^^^^^^^^^^^^^^DatagramHeaderField Methods^^^^^^^^^^^^^^^
    public String toString(){
        return toTransmissionString();
    }

    public String toTransmissionString(){
        return toHexString();
    }

    public String toHexString(){
        String hex = Integer.toHexString(address);
        hex = Utilities.padHexString(hex, Constants.LL2P_ADDRESS_FIELD_LENGTH*2);
        return hex;
    }

    public String explainSelf(){
        return explanation;
    }

    public String toAsciiString(){
        return Utilities.convertToASCII(Integer.toHexString(address));
    }
}