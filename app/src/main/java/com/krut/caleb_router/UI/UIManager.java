package com.krut.caleb_router.UI;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.krut.caleb_router.support.ParentActivities;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by caleb.krut on 1/25/2017.
 */
//UI Manager class which will be used to provide control of the UI in the system and delegates
//specific UI management to lower level UI classes
public class UIManager implements Observer {
    //**************FIELDS*******************
    //Gets the activity from the parent activity class
    private Activity parentActivity;

    private TableUI tableUI;

    //provides access to widgets
    private Context context;

    //this is "us"
    private static UIManager ourInstance = new UIManager();

    //**************METHODS*******************

    //constructor for the UI manager
    private UIManager() {
        tableUI = new TableUI();
    }

    public TableUI getTableUI() {
        return tableUI;
    }

    //returns the UI manager to classes that need it
    public static UIManager getInstance() {
        return ourInstance;
    }

    //displays a toast message on the screen
    public void raiseToast(String message, int displayTime){
        //method to raise a toast message with a specific display time
        Toast.makeText(context,message,displayTime).show();
    }

    //provides a simpler raise toast. allows message to display but doesn't have a time
    public void raiseToast(String message){
        //Used by other classes without needing a display time
        raiseToast(message,Toast.LENGTH_LONG); //default is long time
    }

    //Used if we want to access any on screen widgets
    private void setUpWidgets(){}

    //a method for observers. observes the bootloader. allows the bootloader to notify this
    //class when the UI manager can connect to other widgets and classes
    public void update(Observable bootloader, Object arg){
        //set the local parent activity to the same one as the parent activity class
        context = ParentActivities.getParentActivity().getBaseContext();
        setUpWidgets();
    }


}


/*
package com.pinkpineapplenetworking.insanerouter.support.ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pinkpineapplenetworking.insanerouter.support.BootLoader;
import com.pinkpineapplenetworking.insanerouter.support.ParentActivity;

import java.util.Observable;
import java.util.Observer;

public class UIManager implements Observer {
    private static UIManager ourInstance = new UIManager();
    private Activity parentActivity;
    private Context context;
    private TableUI tableUI;
    private SnifferUI snifferUI;

    public static UIManager getInstance() {
        return ourInstance;
    }

    public SnifferUI getSnifferUI() {
        return snifferUI;
    }

    private UIManager() {
        tableUI = new TableUI();
        snifferUI = new SnifferUI();

    }
    public TableUI getTableUI(){
        return tableUI;
    }
    public void displayMessage(String message, int displayTime){
        if(context != null)
            Toast.makeText(context, message, displayTime).show();

    }
    public void displayMessage(String message){
        displayMessage(message, Toast.LENGTH_SHORT);
    }
    private void setUpWidgets(){
    }

    public void update(Observable observable, Object object){
        if(observable.getClass() == BootLoader.class) {
            parentActivity = ParentActivity.getParentActivity();
            context = parentActivity.getBaseContext();
            Log.i("TAG", "Should be before this");
            Log.i("TAG", context+"Should be before this");
            setUpWidgets();
        }
    }
}
 */