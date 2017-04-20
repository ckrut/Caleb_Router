package com.krut.caleb_router.support;

import java.util.Observable;
import android.app.Activity;
import android.util.Log;

import com.krut.caleb_router.UI.UIManager;
import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.daemons.LL1Daemon;
import com.krut.caleb_router.networks.datagram.LL2PFrame;
import com.krut.caleb_router.networks.table.Table;
import com.krut.caleb_router.networks.tablerecord.AdjacencyRecord;
import com.krut.caleb_router.support.ParentActivities;

import static com.krut.caleb_router.networks.Constants.LL2P_ADDRESS_FIELD_LENGTH;
import static com.krut.caleb_router.networks.Constants.LL2_ADDRESS;
import static com.krut.caleb_router.networks.Constants.TABLE_RECORD_TYPE_ADJACENCY;
import static com.krut.caleb_router.networks.Constants.logTag;
import static com.krut.caleb_router.support.Utilities.padHexString;


/**
 * Created by caleb.krut on 1/12/2017.
 */

//will load up the router upon starting the program
public class BootLoader extends Observable {

    //Constructor for the boot loader
    public BootLoader(Activity activity){
        bootRouter(activity);
    }

    //Boots the router
    private void bootRouter(Activity myActivity){
        ParentActivities.setParentActivity(myActivity);
        addObserver(Constants.getInstance());
        addObserver(UIManager.getInstance());
        addObserver(FrameLogger.getInstance());
        addObserver(LL1Daemon.getInstance());
        addObserver(UIManager.getInstance().getTableUI());

        //Boots the router
        setChanged();
        notifyObservers();
        Log.i(logTag, "Router is booted!  IP Address is "+Constants.IP_ADDRESS);

        try {
            testRouterComponents();
        } catch (LabException e) {
            e.printStackTrace();
        }
    }

    private void testRouterComponents() throws LabException{

        LL1Daemon ll1Daemon = LL1Daemon.getInstance();
        Log.d(logTag, "Table Created");
    }
}

/*
package com.pinkpineapplenetworking.insanerouter.support;

import android.app.Activity;
import android.util.Log;

import com.pinkpineapplenetworking.insanerouter.networks.Constants;
import com.pinkpineapplenetworking.insanerouter.networks.daemon.LL1Daemon;
import com.pinkpineapplenetworking.insanerouter.networks.datagrams.LL2PFrame;
import com.pinkpineapplenetworking.insanerouter.networks.table.Table;
import com.pinkpineapplenetworking.insanerouter.networks.tablerecord.AdjacencyRecord;
import com.pinkpineapplenetworking.insanerouter.support.ui.SnifferUI;
import com.pinkpineapplenetworking.insanerouter.support.ui.TableUI;
import com.pinkpineapplenetworking.insanerouter.support.ui.UIManager;

import java.util.Observable;

import static com.pinkpineapplenetworking.insanerouter.networks.Constants.RECORD_TYPE_IS_ADJACENCY;
import static com.pinkpineapplenetworking.insanerouter.networks.Constants.logTag;
import static com.pinkpineapplenetworking.insanerouter.networks.daemon.LL1Daemon.getInstance;

public class BootLoader extends Observable {

    public BootLoader(Activity bootLoaderActivity) {
        bootRouter(bootLoaderActivity);
    }

    private void bootRouter(Activity bootRouterActivity){
        ParentActivity.setParentActivity(bootRouterActivity);
        addObserver (Constants.getInstance());
        addObserver(UIManager.getInstance());
        addObserver(FrameLogger.getInstance());
        addObserver(LL1Daemon.getInstance());
        addObserver(UIManager.getInstance().getTableUI());
        addObserver(UIManager.getInstance().getSnifferUI());
        setChanged(); // Notify Observers things have changed
        notifyObservers();

        UIManager.getInstance().displayMessage("Router Is Up!");
        try {
            testRun();
        } catch (LabException e) {
            e.printStackTrace();
        }

        Log.i(logTag, "Router Booted!");

    }
    private void testRun() throws LabException {
        LL1Daemon ll1Daemon = LL1Daemon.getInstance();
        ll1Daemon.addAdjacency("112233", "10.30.56.172");
    }
}

 */