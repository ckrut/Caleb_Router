package com.krut.caleb_router.networks.tablerecord;

import com.krut.caleb_router.support.Utilities;

/**
 * Created by caleb.krut on 3/2/2017.
 */

public class TableRecordClass implements TableRecord {
    //^^^^^^^^^^^^^^^^^^^^^^^Fields^^^^^^^^^^^^^^^^^^^^^^^
    private int lastTimeTouched;

    //^^^^^^^^^^^^^^^^^^^^^^Methods^^^^^^^^^^^^^^^^^^^^^^^
    //Constructor which just sets the last time touched
    public TableRecordClass(){
        updateTime();
    }

    //Sets the lastTimeTouched to the current time
    public void updateTime(){
        lastTimeTouched = Utilities.getTimeInSeconds();
    }

    //Allows records to compare to each other. Returns - if it is less than the passed record
    //It returns 0 if they are equal and + if it is greater than the passed one
    public int compareTo(TableRecord tableRecord){
        return this.getKey().compareTo(tableRecord.getKey());
    }

    //^^^^^^^^^^^^^^Table Record Methods^^^^^^^^^^^^^^^^^
    //Returns the differece between the current time and the last time touched
    public Integer getAgeInSeconds(){
        return Utilities.getTimeInSeconds() - lastTimeTouched;
    }

    //Each inheriting class will need to override this because this will return null
    public Integer getKey(){
        return null;
    }
}
