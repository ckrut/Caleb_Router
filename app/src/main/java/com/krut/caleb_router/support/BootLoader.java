package com.krut.caleb_router.support;

import java.util.Observable;
import android.app.Activity;
import android.util.Log;

import com.krut.caleb_router.UI.UIManager;
import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.networks.datagram.LL2PFrame;
import com.krut.caleb_router.support.ParentActivities;

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


        setChanged();
        notifyObservers();
        Log.i(Constants.logTag, "Router is booted!  IP Address is "+Constants.IP_ADDRESS);
        //UIManager.getInstance().raiseToast("Router is booted!");
        testRouterComponents();

    }

    //Test for lab 3
    private void testRouterComponents(){
        String frameString = "0011223141598008Hi0000";
        LL2PFrame frame = new LL2PFrame(frameString.getBytes());
        Log.i(Constants.logTag, "Frame is: "+frame.toString());
        Log.i(Constants.logTag, "Frame hex is: "+frame.toHexString());
    }
}
