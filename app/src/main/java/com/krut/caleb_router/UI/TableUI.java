package com.krut.caleb_router.UI;

import android.app.Activity;

import com.krut.caleb_router.R;
import com.krut.caleb_router.networks.daemons.LL1Daemon;
import com.krut.caleb_router.support.BootLoader;
import com.krut.caleb_router.support.ParentActivities;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by caleb.krut on 4/19/2017.
 */

public class TableUI implements Runnable, Observer {
    Activity parentActivity;
    private SingleTableUI adjacencyUI;
    private SingleTableUI arpTableUI;
    private SingleTableUI routingTableUI;
    private SingleTableUI forwardingUI;

    public TableUI(){
    }

    private void adjacencyUI(){
        parentActivity = ParentActivities.getParentActivity();
        adjacencyUI= new AdjacencyTableUI(parentActivity, R.id.AdjacencyTableList, LL1Daemon.getInstance().getAdjacencyTable(),LL1Daemon.getInstance());// this will implement the AdjacencyTableUI

    }

    @Override
    public void run() {
    }

    @Override
    public void update(Observable observable, Object arg) {
        if(observable.getClass().equals(BootLoader.class)){
            adjacencyUI();
        }
    }
}

/*
package com.pinkpineapplenetworking.insanerouter.support.ui;

        import android.app.Activity;

        import com.pinkpineapplenetworking.insanerouter.R;
        import com.pinkpineapplenetworking.insanerouter.networks.daemon.LL1Daemon;
        import com.pinkpineapplenetworking.insanerouter.support.BootLoader;
        import com.pinkpineapplenetworking.insanerouter.support.ParentActivity;

        import java.util.Observable;
        import java.util.Observer;

/**
 * This class holds all references for other table UI objects, each of which manages a table.
 * observer of the Bootloader.
 */
/*
public class TableUI implements Runnable, Observer {
    Activity parentActivity;
    private SingleTableUI adjacencyUI;
    private SingleTableUI arpTableUI;
    private SingleTableUI routingTableUI;
    private SingleTableUI forwardingUI;

    public TableUI(){

//        We wait until the router is booted to build UI objects.
//


    }


    private void adjacencyUI(){
        parentActivity = ParentActivity.getParentActivity();
        adjacencyUI= new AdjacencyTableUI(parentActivity, R.id.adjacencyTableList, LL1Daemon.getInstance().getAdjacencyTable(),LL1Daemon.getInstance());// this will implement the AdjacencyTableUI.
    }





    @Override
    public void run() {
//    o	this method is called when a scheduler’s timer (built later in the semester) calls it.
//  Eventually it will run once every second to keep the displays current. For now, this is an empty method!
    }

    @Override
    public void update(Observable observable, Object arg) {
        if(observable.getClass().equals(BootLoader.class)){
            adjacencyUI();
        }
    }
}
*/