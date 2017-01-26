package com.krut.caleb_router.support;

/**
 * Created by caleb.krut on 1/12/2017.
 */
//Will be used as storage for various tasks or functions that will be needed throughout the app
public class Utilities {
    private static Utilities ourInstance = new Utilities();

    public static Utilities getInstance() {
        return ourInstance;
    }

    private Utilities() {
    }
}
