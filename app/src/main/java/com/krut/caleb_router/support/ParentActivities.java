package com.krut.caleb_router.support;
import android.app.Activity;
/**
 * Created by caleb.krut on 1/12/2017.
 */
//The parentactivity class is used to make sure there is only a single activity for the app
public class ParentActivities {
    //*************************FIELDS****************************
    private static Activity parentActivity;

    private static ParentActivities ourInstance = new ParentActivities();

    //*************************METHODS***************************

    //Constructor
    private ParentActivities() {
    }

    //Functions as the getter and setter for the activity
    public static Activity getParentActivity() {
        return parentActivity;
    }
    public static void setParentActivity(Activity myParent) {
        parentActivity = myParent;
    }

    //Returns the current instance of the parent activity
    public static ParentActivities getInstance() {
        return ourInstance;
    }
}
