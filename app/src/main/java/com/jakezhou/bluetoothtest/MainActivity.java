package com.jakezhou.bluetoothtest;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BA.isEnabled()) {
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_LONG);
        }
        else {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);

        }
    }

    public void turnBluetoothOff(View v) {
        BA.disable();
        if(BA.isEnabled()) {
            Toast.makeText(this, "Bluetooth is still on", Toast.LENGTH_LONG);
        }
        else {

            Toast.makeText(this, "Bluetooth has been disabled", Toast.LENGTH_LONG);

        }
    }

    public void findDiscoverableDevices(View v) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void viewPairedDevices(View v) {
        Set<BluetoothDevice> devices = BA.getBondedDevices();

        ListView pairedDevicesListView = findViewById(R.id.pairedDevicesListView);
        ArrayList<String> pairedDevicesArrayList = new ArrayList<>();

        for(BluetoothDevice d : devices) {
            pairedDevicesArrayList.add(d.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pairedDevicesArrayList);
        pairedDevicesListView.setAdapter(adapter);
    }
}