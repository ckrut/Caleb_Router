package com.krut.caleb_router.networks;

import java.util.Observer;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Observable;

/**
 * Created by caleb.krut on 1/12/2017.
 */
public class Constants implements Observer {
    private static Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }

    //sets the router name
    public static String routerName = new String("K-2S0");
    public static String logTag = new String("K-2S0: ");

    //LL2 Address
    public static String LL2_ADDRESS = new String("2E1DA1");

    //Types of LL2P
    final public static int LL2P_TYPE_LL3P = 0x8001;
    final public static int LL2P_TYPE_RESERVED = 0x8002;
    final public static int LL2P_TYPE_LRP = 0x8003;
    final public static int LL2P_TYPE_ECHO_REQUEST = 0x8004;
    final public static int LL2P_TYPE_ECHO_REPLY = 0x8005;
    final public static int LL2P_TYPE_ARP_REQUEST = 0x8006;
    final public static int LL2P_TYPE_ARP_REPLY = 0x8007;
    final public static int LL2P_TYPE_TEXT = 0x8008;

    //LL2P type fields address
    final public static int LL2P_SOURCE_ADDRESS = 25;
    final public static int LL2P_DESTINATION_ADDRESS = 21;
    final public static int LL2P_TYPE_ADDRESS = 22;
    final public static int LL2P_PAYLOAD_ADDRESS = 23;
    final public static int LL2P_CRC_ADDRESS = 24;
    final public static int LL2P_ARP_ADDRESS = 26;

    //LL2P Address Lengths and positions
    public static int LL2P_DEST_ADDRESS_OFFSET = 0;
    public static int LL2P_ADDRESS_FIELD_LENGTH = 3;
    public static int LL2P_SRC_ADDRESS_OFFSET = 6;
    public static int LL2P_TYPE_OFFSET = 12;
    public static int LL2P_TYPE_FIELD_LENGTH = 2;
    public static int LL2P_PAYLOAD_OFFSET = 16;
    public static int LL2P_CRC_FIELD_LENGTH = 2;

    //Values for records
    final public static int TABLE_RECORD_TYPE_ARP = 30;
    final public static int TABLE_RECORD_TYPE_ADJACENCY = 31;
    final public static int TABLE_RECORD_TYPE_ROUTING = 32;

    final public static int UDP_PORT = 49999;

    public static String IP_ADDRESS;	// the IP address of this system
    //will be stored here in dotted decimal notation
    public static String IP_ADDRESS_PREFIX; // the prefix will be stored here
    /*
     * Constructor for Constants -- will eventually find out my IP address and do other nice
     * things that need to be set up in the constants file.
     */
    protected Constants (){
// call the local method to get the IP address of this device.
        IP_ADDRESS = getLocalIpAddress();
        int lastDot = IP_ADDRESS.lastIndexOf(".");
        int secondDot = IP_ADDRESS.substring(0, lastDot-1).lastIndexOf(".");
        IP_ADDRESS_PREFIX = IP_ADDRESS.substring(0, secondDot+1);
    }

    /**
     * getLocalIPAddress - this function goes through the network interfaces,
     *    looking for one that has a valid IP address.
     * Care must be taken to avoid a loopback address and IPv6 Addresses.
     * @return - a string containing the IP address in dotted decimal notation.
     */
    public String getLocalIpAddress() {
        String address= null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public void update(Observable o, Object arg){

    }
}