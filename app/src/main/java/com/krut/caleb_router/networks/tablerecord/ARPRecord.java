package com.krut.caleb_router.networks.tablerecord;

import java.net.InetAddress;

/**
 * Created by caleb.krut on 3/9/2017.
 */

public class ARPRecord extends TableRecordClass {
    //^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^
    private Integer ll2pAddress;

    private Integer ll3pAddress;

    //^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^
    //Constructor that stores the passed values
    public ARPRecord(Integer ll3pAddress, Integer ll2pAddress){
        super();
        this.ll3pAddress = ll3pAddress;
        this.ll2pAddress = ll2pAddress;
    }

    @Override
    public String toString(){
        return "LL2P Address: "+ll2pAddress+"; LL3P Address: "+ll3pAddress;
    }

    //^^^^^^^^^^^Field Getters and Setters^^^^^^^^^^^
    public Integer getLl2pAddress() {
        return ll2pAddress;
    }

    public void setLl2pAddress(Integer ll2pAddress) {
        this.ll2pAddress = ll2pAddress;
    }

    public Integer getLl3pAddress() {
        return ll3pAddress;
    }

    public void setLl3pAddressAddress(Integer ll3pAddress) {
        this.ll3pAddress = ll3pAddress;
    }
}
