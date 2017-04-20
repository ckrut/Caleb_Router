package com.krut.caleb_router.UI;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.daemons.LL1Daemon;
import com.krut.caleb_router.networks.datagram.LL2PFrame;
import com.krut.caleb_router.networks.datagram_fields.CRC;
import com.krut.caleb_router.networks.datagram_fields.DatagramPayloadField;
import com.krut.caleb_router.networks.datagram_fields.LL2PAddressField;
import com.krut.caleb_router.networks.datagram_fields.LL2PTypeField;
import com.krut.caleb_router.networks.table.TableInterface;
import com.krut.caleb_router.networks.tablerecord.AdjacencyRecord;
import com.krut.caleb_router.support.LabException;

/**
 * Created by caleb.krut on 4/20/2017.
 */

public class AdjacencyTableUI extends SingleTableUI {
    private LL1Daemon ll1Daemon;

    public AdjacencyTableUI(Activity activity, int touchedIndex, TableInterface tableInterface, LL1Daemon tableManager){
        super(activity,touchedIndex,tableInterface);
        tableListViewWidget.setOnItemClickListener(sendEchoRequest);
        tableListViewWidget.setOnItemLongClickListener(removeAdjacency);
        this.ll1Daemon = tableManager;
    }

    private AdapterView.OnItemClickListener sendEchoRequest = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AdjacencyRecord adjacencyRecord = (AdjacencyRecord) tableList.get(position);

            LL2PAddressField destinationAddress = new LL2PAddressField(adjacencyRecord.getLl2pAddress(), false);
            LL2PAddressField sourceAddress = new LL2PAddressField(Constants.LL2_ADDRESS, true);
            LL2PTypeField typeField = new LL2PTypeField(Constants.LL2P_TYPE_ECHO_REQUEST);
            DatagramPayloadField payloadField = new DatagramPayloadField(Constants.LL2P_TYPE_ECHO_REQUEST, "Echo Contents");
            CRC crc = new CRC("3030");

            LL2PFrame frame = new LL2PFrame(destinationAddress,sourceAddress,typeField,payloadField,crc);

            ll1Daemon.getInstance().sendFrame(frame);
            Log.d(Constants.logTag,"Frame sent in the OnItemClickListener SendEcho Request Method.");
        }
    };

    private AdapterView.OnItemLongClickListener removeAdjacency = new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            AdjacencyRecord adjacencyRecord = (AdjacencyRecord) tableList.get(position);
            try {
                ll1Daemon.removeAdjacency(adjacencyRecord);
                updateView();
                return true;
            } catch (LabException e) {
                e.printStackTrace();
            }
            Log.d(Constants.logTag,"Frame sent in the OnItemClickListener SendEcho Request Method.");
            return false;
        }
    };
}

/*
package com.pinkpineapplenetworking.insanerouter.support.ui;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.pinkpineapplenetworking.insanerouter.networks.Constants;
import com.pinkpineapplenetworking.insanerouter.networks.daemon.LL1Daemon;
import com.pinkpineapplenetworking.insanerouter.networks.datagram_fields.CRC;
import com.pinkpineapplenetworking.insanerouter.networks.datagram_fields.DatagramPayloadField;
import com.pinkpineapplenetworking.insanerouter.networks.datagram_fields.LL2PAddressField;
import com.pinkpineapplenetworking.insanerouter.networks.datagram_fields.LL2PTypeField;
import com.pinkpineapplenetworking.insanerouter.networks.datagrams.LL2PFrame;
import com.pinkpineapplenetworking.insanerouter.networks.table.Table;
import com.pinkpineapplenetworking.insanerouter.networks.table.TableInterface;
import com.pinkpineapplenetworking.insanerouter.networks.tablerecord.AdjacencyRecord;
import com.pinkpineapplenetworking.insanerouter.support.LabException;
import java.util.ArrayList;
import java.util.Observer;

public class AdjacencyTableUI extends SingleTableUI implements Observer {
    private LL1Daemon ll1Daemon; // this holds the reference to the ll1Daemon

    public AdjacencyTableUI(Activity activity, int touchedIndex, TableInterface tableInterface, LL1Daemon tableManager){
        super(activity, touchedIndex, tableInterface);
        tableListViewWidget.setOnItemClickListener(sendEchoRequest);
        tableListViewWidget.setOnItemLongClickListener(removeAdjacency);
        this.ll1Daemon = tableManager;
    }

    private AdapterView.OnItemClickListener sendEchoRequest = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AdjacencyRecord adjacencyRecord = (AdjacencyRecord) tableList.get(position);

            LL2PAddressField destinationAddress = new LL2PAddressField(adjacencyRecord.getLl2pAddress(), false);
            LL2PAddressField sourceAddress = new LL2PAddressField(Constants.MY_LL2P_ADDRESS, true);
            LL2PTypeField typeField = new LL2PTypeField(Constants.LL2P_TYPE_IS_ECHO_REQUEST);
            DatagramPayloadField payloadField = new DatagramPayloadField(Constants.LL2P_TYPE_IS_ECHO_REQUEST, "Echo Contents");
            CRC crc = new CRC("3030");

            LL2PFrame frame = new LL2PFrame(destinationAddress,sourceAddress,typeField,payloadField,crc);

            ll1Daemon.getInstance().sendFrame(frame);
            Log.d(Constants.logTag,"Frame sent in the OnItemClickListener SendEcho Request Method.");
        }
    };

    private AdapterView.OnItemLongClickListener removeAdjacency = new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            AdjacencyRecord adjacencyRecord = (AdjacencyRecord) tableList.get(position);
            try {
                ll1Daemon.removeAdjacency(adjacencyRecord);
                updateView();
                return true;
            } catch (LabException e) {
                e.printStackTrace();
            }
            Log.d(Constants.logTag,"Frame sent in the OnItemClickListener SendEcho Request Method.");
            return false;
        }
    };
}
 */