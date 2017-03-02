package com.krut.caleb_router.support;

import java.util.Calendar;

/**
 * Created by caleb.krut on 1/12/2017.
 */
//Will be used as storage for various tasks or functions that will be needed throughout the app
public class Utilities {
    //***********************FIELDS*********************
    private static Utilities ourInstance = new Utilities();

    public static long baseDateInSeconds = Calendar.getInstance().getTimeInMillis()/1000;

    //***********************METHODS********************

    //Constructor
    private Utilities() {
    }

    //Returns the current instance of utilities
    public static Utilities getInstance() {
        return ourInstance;
    }

    //Pad Hex String will be used to prepend hex strings with 0s
    public static String padHexString(String hexString, Integer finalLength){
        //Finds the length of the zeros needed
        Integer numOfZeros = finalLength - hexString.length();

        //creates a buffer to fill with zeros
        StringBuilder zeroBuffer = new StringBuilder();

        //adds a 0 to the buffer of zeros = the final length - the length of the passed string
        for(int loopcounter=0;loopcounter<numOfZeros;loopcounter++)
        {
            zeroBuffer.append("0");
        }

        //converts the stringbuilder to a string
        String bufferString = zeroBuffer.toString();

        //returns both strings added together
        return bufferString+hexString;
    }

    //Converts a hex string to ASCII
    public static String convertToASCII(String hexString){
        String myString = new String ("");
        char character; //this will hold each individual character in the hex string
        int valueOfHexString = Integer.valueOf(hexString,16);
        while (valueOfHexString>0){
            character = (char) (valueOfHexString % 256);
            myString = character + myString;
            valueOfHexString = valueOfHexString / 256;
        }
        return myString;
    }

    //Converts from an ASCII string to hex
    public static String convertASCIIToHex(String asciiString){
        char[] chars = asciiString.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }
        return hex.toString();
    }

    //Converts a string to a hex string
    public static String convertStringToHex(String str) {

        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    //Gets the current time in seconds from the program start
    public static int getTimeInSeconds(){
        return (int) (Calendar.getInstance().getTimeInMillis()/1000 - baseDateInSeconds);
    }
}
