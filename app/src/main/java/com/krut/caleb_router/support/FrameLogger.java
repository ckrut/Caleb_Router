package com.krut.caleb_router.support;

import com.krut.caleb_router.networks.daemons.LL1Daemon;
import com.krut.caleb_router.networks.datagram.LL2PFrame;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by caleb.krut on 2/2/2017.
 */
public class FrameLogger extends Observable implements Observer{
    //^^^^^^^^^^^^^^^^^^^^^^FIELDS^^^^^^^^^^^^^^^^^^^^^^^^
    private static FrameLogger ourInstance = new FrameLogger();

    private ArrayList<LL2PFrame> frameList;

    //^^^^^^^^^^^^^^^^^^^^^METHODS^^^^^^^^^^^^^^^^^^^^^^^^^

    //Initial Constructor
    private FrameLogger() {
        frameList = new ArrayList<LL2PFrame>();
    }

    //Constructor
    public FrameLogger(ArrayList<LL2PFrame> frameList) {
        this.frameList = frameList;
    }

    //Return instance
    public static FrameLogger getInstance() {
        return ourInstance;
    }

    //Update function to allow observers to be notified
    public void update(Observable observable, Object o){
        //TODO change when adding daemon
        if (observable.getClass().equals(LL1Daemon.class)){
            //frameList.add((LL2PFrame) o); // recast the object and add it.
        }
        setChanged();
        notifyObservers();
    }

    //is a getter for the frame list
    public ArrayList<LL2PFrame> getFrameList(){
        return frameList;
    }
}

/*
public class FrameLogger extends Observable implements Observer{

    private static FrameLogger ourInstance = new FrameLogger(); // the instance of the frameLogger.

    private ArrayList<LL2PFrame> frameList; // Variable to hold the framelist

    public static FrameLogger getInstance() {
        return ourInstance;
    } // returns the list to anyone

    private FrameLogger() {
        frameList = new ArrayList<LL2PFrame>(); // Constructor that creates a new list
    }

    public FrameLogger(ArrayList<LL2PFrame> frameList) {
        //initializes an empty array list
        this.frameList = frameList;
    }

    public ArrayList<LL2PFrame> getFrameList(){
        //this method returns the current frameList.
        return this.frameList;
    }

    public void update(Observable observable, Object o){
        if (observable.getClass().equals(LL1Daemon.class)){
            frameList.add((LL2PFrame) o); // recast the object and add it.
        }
        setChanged();
        notifyObservers(); // notify the snifferUI there is a new frame.
    }
}

 */