package com.krut.caleb_router.UI;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.krut.caleb_router.networks.table.Table;
import com.krut.caleb_router.networks.table.TableInterface;
import com.krut.caleb_router.networks.tablerecord.TableRecord;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by caleb.krut on 4/19/2017.
 */

public class SingleTableUI implements Observer {
    protected Activity parentActivity;
    protected Table tableToDisplay;
    protected List<TableRecord> tableList;
    protected ListView tableListViewWidget;
    private ArrayAdapter arrayAdapter;

    SingleTableUI(Activity activity, int viewID, TableInterface tableInterface){
        parentActivity= activity;
        tableListViewWidget = (ListView) parentActivity.findViewById(viewID);
        tableToDisplay = (Table) tableInterface;
        tableList= tableInterface.getTableAsArrayList();

        arrayAdapter = new ArrayAdapter(parentActivity.getBaseContext(),
                android.R.layout.simple_list_item_1, tableList);

        tableListViewWidget.setAdapter(arrayAdapter);
        tableToDisplay.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }

    public void updateView(){
        parentActivity.runOnUiThread(new Runnable() {
            @Override // this is a mini-Runnable class’s run method!
            public void run() {
                arrayAdapter.notifyDataSetChanged();
            }
        });
    };
}

/*
package com.pinkpineapplenetworking.insanerouter.support.ui;

        import android.app.Activity;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

        import com.pinkpineapplenetworking.insanerouter.R;
        import com.pinkpineapplenetworking.insanerouter.networks.table.Table;
        import com.pinkpineapplenetworking.insanerouter.networks.table.TableInterface;
        import com.pinkpineapplenetworking.insanerouter.networks.tablerecord.TableRecord;

        import java.util.List;
        import java.util.Observable;
        import java.util.Observer;

public class SingleTableUI implements Observer {
    protected Activity parentActivity;// this holds the reference to the activity
    protected Table tableToDisplay;// The class that manages the table is here.
    protected List<TableRecord> tableList;// This contains a List of table records that will be displayed on the screen.
    protected ListView  tableListViewWidget;// object that refers to the screen ListView Widget.
    private ArrayAdapter arrayAdapter; // This object adapts the underlying table (array) to the screen ListView widget.

    SingleTableUI(Activity activity, int viewID, TableInterface tableInterface){
        parentActivity= activity;
        tableListViewWidget = (ListView) parentActivity.findViewById(viewID);
        tableToDisplay = (Table) tableInterface;
        tableList= tableInterface.getTableAsArrayList();

        arrayAdapter = new ArrayAdapter(parentActivity.getBaseContext(),
                android.R.layout.simple_list_item_1, tableList);

        tableListViewWidget.setAdapter(arrayAdapter);
        tableToDisplay.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
    public void updateView(){
        parentActivity.runOnUiThread(new Runnable() {
            @Override // this is a mini-Runnable class’s run method!
            public void run() {
                arrayAdapter.notifyDataSetChanged();
            }
        });
    };
}
*/