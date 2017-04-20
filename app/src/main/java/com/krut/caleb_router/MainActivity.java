package com.krut.caleb_router;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.krut.caleb_router.UI.AddAdjacencyDialog;
import com.krut.caleb_router.UI.UIManager;
import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.daemons.LL1Daemon;
import com.krut.caleb_router.networks.table.Table;
import com.krut.caleb_router.networks.tablerecord.AdjacencyRecord;
import com.krut.caleb_router.support.BootLoader;
import com.krut.caleb_router.support.GetIPAddress;

//Thr program will load here which will call on the boot loader to load the router
public class MainActivity extends AppCompatActivity implements AddAdjacencyDialog.AdjacencyPairListener {
    //Table table = new Table();
//is the start up function that will go to the boot loader to load the router
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BootLoader bootLoader = new BootLoader(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.showIPAddress){
            UIManager.getInstance().raiseToast("Your IP address is "+ Constants.IP_ADDRESS);
        }
        if(item.getItemId() == R.id.AddAdjacency){
            AddAdjacencyDialog dialog = new AddAdjacencyDialog();
            dialog.show(getFragmentManager(), "enter_adjacency");
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //@Override
    public void onFinishedEditDialog(String ipAddress, String ll2PAddress) {
        //AdjacencyRecord newRecord = new AdjacencyRecord(GetIPAddress.getInstance().getInetAddress(ipAddress),Integer.valueOf(ll2PAddress));
        //table.getTableAsArrayList();
        //LL1Daemon.addAdjacency(newRecord);
        LL1Daemon.getInstance().addAdjacency(ll2PAddress,ipAddress);

        UIManager.getInstance().raiseToast("Added record");

    }
}

/*
package com.pinkpineapplenetworking.insanerouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pinkpineapplenetworking.insanerouter.networks.Constants;
import com.pinkpineapplenetworking.insanerouter.networks.daemon.LL1Daemon;
import com.pinkpineapplenetworking.insanerouter.support.BootLoader;
import com.pinkpineapplenetworking.insanerouter.support.ui.AddAdjacencyDialog;
import com.pinkpineapplenetworking.insanerouter.support.ui.UIManager;

public class MainActivity extends AppCompatActivity implements AddAdjacencyDialog.AdjacencyPairListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        "this" (below) refers to that it is calling itself

        BootLoader bootLoader = new BootLoader(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /*
       Later when we add other menu events we’ll add the event item to the
       XML file and come here to add additional “else-if” commands.

        if (item.getItemId() == R.id.showIPAddress){
            UIManager.getInstance().displayMessage("Your IP address is "+ Constants.IP_ADDRESS);
        }

        else if (item.getItemId() == R.id.Base_Theme_AppCompat_Dialog) {
            UIManager.getInstance().displayMessage("Add Adjacency Menu Item works");
            AddAdjacencyDialog dialog = new AddAdjacencyDialog();
            dialog.show(getFragmentManager(), "add_adjacency_dialog");
        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFinishedEditDialog(String ipAddress, String ll2PAddress) {
        LL1Daemon.getInstance().addAdjacency(ll2PAddress,ipAddress);
    }
}

 */