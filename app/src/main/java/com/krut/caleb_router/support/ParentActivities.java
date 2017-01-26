package com.krut.caleb_router.support;
import android.app.Activity;
/**
 * Created by caleb.krut on 1/12/2017.
 */
//The parentactivity class is used to make sure there is only a single activity for the app
public class ParentActivities {
    private static Activity parentActivity;

    private static ParentActivities ourInstance = new ParentActivities();

    //Functions as the getter and setter for the activity
    public static Activity getParentActivity() {
        return parentActivity;
    }
    public static void setParentActivity(Activity myParent) {
        parentActivity = myParent;
    }

    public static ParentActivities getInstance() {
        return ourInstance;
    }

    private ParentActivities() {
    }
}
