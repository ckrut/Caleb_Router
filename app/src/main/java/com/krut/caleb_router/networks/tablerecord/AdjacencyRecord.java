package com.krut.caleb_router.networks.tablerecord;

import java.net.InetAddress;


/**
 * Created by caleb.krut on 2/2/2017.
 */

public class AdjacencyRecord extends TableRecordClass {
    //^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^
    private Integer ll2pAddress;

    private InetAddress ipAddress;

    //^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^
    //Constructor that stores the passed values
    public AdjacencyRecord(InetAddress ipAddress, Integer ll2pAddress){
        super();
        setIpAddress(ipAddress);
        setLl2pAddress(ll2pAddress);
    }

    @Override
    public String toString(){
       // return "LL2P Address: "+ll2pAddress+"; IP Address: "+ipAddress;
        return String.format("LL2P Address: " + Integer.toHexString(ll2pAddress)
                + " IP Address: " + ipAddress);
    }

    //^^^^^^^^^^^Table Record Methods^^^^^^^^^^^^^^^
    //Returns the ll2pAddress as the key
    @Override
    public Integer getKey(){
        return ll2pAddress;
    }

    //Returns 0 because Adjacency Record does not have any age
    public Integer getAgeInSeconds(){
        return 0;
    }

    //^^^^^^^^^^^Field Getters and Setters^^^^^^^^^^^
    public Integer getLl2pAddress() {
        return ll2pAddress;
    }

    public void setLl2pAddress(Integer ll2pAddress) {
        this.ll2pAddress = ll2pAddress;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}