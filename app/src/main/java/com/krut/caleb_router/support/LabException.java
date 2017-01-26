package com.krut.caleb_router.support;

/**
 * Created by caleb.krut on 1/25/2017.
 */

//class for the lab exceptions we may generate.
public class LabException extends Exception {

    private static final long serialVersionUID = 1L;

    //Calls the super constructor
    public LabException(String errorMessage){
        super(errorMessage);
    }
}
