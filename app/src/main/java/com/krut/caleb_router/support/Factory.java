package com.krut.caleb_router.support;

import android.util.Log;

import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.datagram.LL2PFrame;
import com.krut.caleb_router.networks.datagram_fields.CRC;
import com.krut.caleb_router.networks.datagram_fields.DatagramHeaderField;
import com.krut.caleb_router.networks.datagram_fields.DatagramPayloadField;
import com.krut.caleb_router.networks.datagram_fields.LL2PAddressField;
import com.krut.caleb_router.networks.datagram_fields.LL2PTypeField;
import com.krut.caleb_router.networks.tablerecord.AdjacencyRecord;
import com.krut.caleb_router.networks.tablerecord.Datagram;
import com.krut.caleb_router.networks.tablerecord.TableRecord;
import com.krut.caleb_router.networks.tablerecord.TableRecordClass;

import java.net.InetAddress;

import static com.krut.caleb_router.networks.Constants.logTag;

/**
 * Created by caleb.krut on 2/16/2017.
 */
public class Factory {
    //^^^^^^^^^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^^^^^
    //The actual Factory object
    private static Factory ourInstance = new Factory();

    //^^^^^^^^^^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^^^^
    //Empty Constructor
    private Factory(){
    }

    //Returns factory's instance
    public static Factory getInstance(){
        return ourInstance;
    }

    //returns a datagram field by comparing the field value to find what field is needed
    public DatagramHeaderField getDatagramHeaderField(int FieldValue, String contents){
        DatagramHeaderField datagramfound;
        switch(FieldValue){
            case Constants.LL2P_SOURCE_ADDRESS: datagramfound = new LL2PAddressField(contents,true);
                break;
            case Constants.LL2P_DESTINATION_ADDRESS: datagramfound = new LL2PAddressField(contents,false);
                break;
            case Constants.LL2P_ARP_ADDRESS:
            case Constants.LL2P_TYPE_LL3P:
            case Constants.LL2P_TYPE_RESERVED:
            case Constants.LL2P_TYPE_LRP:
            case Constants.LL2P_TYPE_ECHO_REQUEST:
            case Constants.LL2P_TYPE_ECHO_REPLY:
            case Constants.LL2P_TYPE_ARP_REQUEST:
            case Constants.LL2P_TYPE_ARP_REPLY:
            case Constants.LL2P_TYPE_TEXT: datagramfound = new DatagramPayloadField(FieldValue, contents);
                break;
            case Constants.LL2P_TYPE_ADDRESS: datagramfound = new LL2PTypeField(contents);
                break;
            case Constants.LL2P_CRC_ADDRESS: datagramfound = new CRC(contents);
                break;
            default:datagramfound = null;
                break;
        }
        return datagramfound;
    }

    public TableRecordClass getTableRecord(Integer recordId, Object [] data){
        switch (recordId){
            case Constants.TABLE_RECORD_TYPE_ADJACENCY:             //the number 31
                InetAddress address = (InetAddress) data[0];
                Integer ll2pAddress = (Integer) data[1];
                return new AdjacencyRecord(address,ll2pAddress);
            case Constants.TABLE_RECORD_TYPE_ARP:                   //the number 30
            case Constants.TABLE_RECORD_TYPE_ROUTING:               //the number 32
            default:
                Log.e(logTag, "createTableRecord: Record Type Factory Crash");
                return null;
        }
    }
}