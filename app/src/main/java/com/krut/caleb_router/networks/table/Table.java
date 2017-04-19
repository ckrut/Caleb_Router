package com.krut.caleb_router.networks.table;

import com.krut.caleb_router.networks.tablerecord.TableRecord;
import com.krut.caleb_router.support.LabException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by caleb.krut on 3/9/2017.
 */

public class Table extends Observable implements TableInterface{
    //^^^^^^^^^^^^^^^^^^^^^^^^FIELDS^^^^^^^^^^^^^^^^^^^^^^^
    protected ArrayList<TableRecord> table;

    //^^^^^^^^^^^^^^^^^^^^^^^^METHODS^^^^^^^^^^^^^^^^^^^^^^
    public Table(){
        table = new ArrayList<TableRecord>();
    }

    public void updateObservers(){
        setChanged();
        notifyObservers();
    }

    //^^^^^^^^^^^^^^^^^^^^^TABLEINTERFACE METHODS^^^^^^^^^^
    @Override
    public List<TableRecord> getTableAsArrayList(){
        return table;
    }

    public TableRecord addItem(TableRecord passedTable){
            table.add(passedTable);
            return passedTable;
    }

    public TableRecord getItem(TableRecord passedTable) throws LabException{
        return getItem(passedTable.getKey());
    }

    public TableRecord removeItem(Integer recordKey) throws LabException{
        try{
            TableRecord record = getItem(recordKey);
            table.remove(record);
            updateObservers();
            return record;
        }
        catch (LabException e){
            e.getMessage();
        }
        throw new LabException("Item Not Removed!");
    }

    public TableRecord getItem(Integer recordKey) throws LabException {
        for (TableRecord record : table) {
            if (record.getKey().compareTo(recordKey) == 0) {
                return record;
            }
        }
        throw new LabException("Record with key: " + recordKey + " Was not found");
    }

    public void Clear(){
        table.clear();
    }
}