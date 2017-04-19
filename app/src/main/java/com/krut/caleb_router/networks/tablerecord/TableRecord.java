package com.krut.caleb_router.networks.tablerecord;

/**
 * Created by caleb.krut on 2/2/2017.
 */

public interface TableRecord {
    //This method will return the key in the record
    Integer getKey();

    //This will return the time passed since the record was last referenced
    Integer getAgeInSeconds();
}


