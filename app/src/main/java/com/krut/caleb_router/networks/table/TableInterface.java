
package com.krut.caleb_router.networks.table;

import com.krut.caleb_router.networks.tablerecord.TableRecord;
import com.krut.caleb_router.support.LabException;

import java.util.List;

/**
 * Created by caleb.krut on 2/9/2017.
 */

public interface TableInterface {
    List<TableRecord> getTableAsArrayList();

    TableRecord addItem(TableRecord passedTable) throws LabException;

    TableRecord getItem(TableRecord passedTable) throws LabException;

    TableRecord removeItem(Integer recordKey) throws LabException;

    TableRecord getItem(Integer recordKey) throws LabException;

    void Clear();
}