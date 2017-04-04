package com.example.vivek.pdlab;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        final ImageView ivLock1 = (ImageView)findViewById(R.id.iv_lock1);
        final ImageView ivLock2 = (ImageView)findViewById(R.id.iv_lock2);
        final SwitchCompat sc_lock1 = (SwitchCompat)findViewById(R.id.sc_lock1);
        SwitchCompat sc_lock2 = (SwitchCompat)findViewById(R.id.sc_lock2);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(this, "No bluetooth found, closing app...", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {

            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            else{
                // todo pair with device and send data
            }
        }

        sc_lock1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    ivLock1.setImageResource(R.drawable.ic_lock_outline_black_24px);
                }
                else{
                    ivLock1.setImageResource(R.drawable.ic_lock_open_black_24px);
                }
            }
        });

        sc_lock2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    ivLock2.setImageResource(R.drawable.ic_lock_outline_black_24px);
                }
                else{
                    ivLock2.setImageResource(R.drawable.ic_lock_open_black_24px);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_logout:
                startActivity(new Intent(getParent(), MainActivity.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
