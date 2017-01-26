package com.krut.caleb_router.support;

import java.util.Observable;
import android.app.Activity;
import android.util.Log;

import com.krut.caleb_router.UI.UIManager;
import com.krut.caleb_router.networks.Constants;
import com.krut.caleb_router.support.ParentActivities;


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
        UIManager.getInstance().raiseToast("Router is booted!");

        //Calls the UIManager to raise a message indicating the router is up

    }


}
