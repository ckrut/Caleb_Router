package com.krut.caleb_router.support;

import com.krut.caleb_router.networks.datagram.LL2PFrame;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by caleb.krut on 2/2/2017.
 */
public class FrameLogger extends Observable{
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
        frameList.add((LL2PFrame)o);
        setChanged();
        notifyObservers();
    }

    //is a getter for the frame list
    public ArrayList<LL2PFrame> getFrameList(){
        return frameList;
    }
}
