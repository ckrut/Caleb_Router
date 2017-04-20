package com.krut.caleb_router.UI;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.krut.caleb_router.R;
import com.krut.caleb_router.networks.Constants;

import static com.krut.caleb_router.networks.Constants.logTag;

/**
 * Created by caleb.krut on 4/19/2017.
 */

public class AddAdjacencyDialog extends DialogFragment {

    //^^^^^^^^^^^^^^^^^^^^^^FIELDS^^^^^^^^^^^^^^^^^^^^^^^^^
    private EditText ipAddressEditText;
    private EditText ll2pAddressEditText;
    private Button addAdjacencyButton;
    private Button cancelButton;
    public interface AdjacencyPairListener{void onFinishedEditDialog(String ipAddress, String ll2PAddress);}

    //^^^^^^^^^^^^^^^^^^^^^METHODS^^^^^^^^^^^^^^^^^^^^^^^^^
    public AddAdjacencyDialog(){}   //Empty Constructor

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.enter_adjacency, container, false);
        getDialog().setTitle("Add Adjacency");
        ipAddressEditText = (EditText) rootView.findViewById(R.id.IPAddressField);
        ll2pAddressEditText = (EditText) rootView.findViewById(R.id.LL2PAddressField);
        addAdjacencyButton = (Button) rootView.findViewById(R.id.AddAdjacencyButton);
        addAdjacencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdjacencyPairListener activity = (AdjacencyPairListener) getActivity();
                //TODO Breaks on this line
                activity.onFinishedEditDialog(ipAddressEditText.getText().toString(), ll2pAddressEditText.getText().toString());
                dismiss();
            }
        });
        cancelButton = (Button) rootView.findViewById(R.id.CancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}

/*
package com.pinkpineapplenetworking.insanerouter.support.ui;

        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;

        import com.pinkpineapplenetworking.insanerouter.R;

/**
 * A Dialogue Fragment is a form of an Activity with lifecycle events
 * similar to the Activity class you’re now use to seeing. In order to
 * raise a dialogue and handle its events we need to create a class that
 * extends the base DialogFragment class.


public class AddAdjacencyDialog extends DialogFragment {

    private EditText ipAddressEditText;//Represents the widget on the screen that the user can type the IP address into.
    private EditText ll2pAddressEditText; //Represents the widget on the screen that the user can type the LL2P Address into.
    private Button addAdjacencyButton;//Button that the user clicks to close the window and add the adjacency specified.
    private Button cancelButton;//Cancels the dialog without entering an adjacency
    public interface AdjacencyPairListener {void onFinishedEditDialog(String ipAddress, String ll2PAddress);}
//    o	This interface provides a connection back to the main Activity (remember that the DialogFragment is actually running on a different Activity thread). See Below.
//    o	This interface has one method with the following signature:

//    void onFinishedEditDialog(String ipAddress, String ll2PAddress);


    public AddAdjacencyDialog() {} // Empty Constructor

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.adjacency_dialog_view, container, false);
        getDialog().setTitle("Add Adjacency");

        ipAddressEditText = (EditText) rootView.findViewById(R.id.ipAddressEditText);
        ll2pAddressEditText = (EditText) rootView.findViewById(R.id.ll2pAddressEditText);
        addAdjacencyButton = (Button) rootView.findViewById(R.id.addAdjacencyButton);
        cancelButton = (Button) rootView.findViewById(R.id.cancelButton);
//       Handle Button Presses
        addAdjacencyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AdjacencyPairListener activity = (AdjacencyPairListener) getActivity();
                activity.onFinishedEditDialog(ipAddressEditText.getText().toString(),
                        ll2pAddressEditText.getText().toString());
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // return super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
//      All This method does is call the superclass’s constructor, passing the savedInstanceState.
    }

}*/