package com.krut.caleb_router.networks.datagram;

import com.krut.caleb_router.networks.tablerecord.Datagram;
import com.krut.caleb_router.support.Utilities;

/**
 * Created by caleb.krut on 2/16/2017.
 */

public class TextDatagram implements Datagram {
    //*********************FIELDS************************
    private String text;

    //*********************METHODS***********************
    //Constructor
    public TextDatagram(String text) {
        this.text = text;
    }

    //*****************Datagram Methods******************
    @Override
    public String toString(){
        return toTransmissionString();
    }

    @Override
    public String toTransmissionString() {
        return text;
    }

    @Override
    public String toHexString() {
        return Utilities.convertASCIIToHex(text);
        // TODO: 2/28/2017 write the ascii to hex utilities method
    }

    @Override
    public String toProtocolExplanationString() { return "Text Datagram: " + text;
    }

    @Override
    public String toSummaryString() {
        return "Text Datagram with text of: " + this.text;
    }
}

