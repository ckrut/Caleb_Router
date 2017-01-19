package com.krut.caleb_router;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.krut.caleb_router.support.BootLoader;


public class MainActivity extends AppCompatActivity {
//is the start up function that will go to the boot loader to load the router
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BootLoader bootLoader = new BootLoader(this);
    }
}
