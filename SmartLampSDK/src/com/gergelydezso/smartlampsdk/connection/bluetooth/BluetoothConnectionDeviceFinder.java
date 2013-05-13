package com.gergelydezso.smartlampsdk.connection.bluetooth;

import java.util.Set;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BluetoothConnectionDeviceFinder {

  private static final String TAG = "BluetoothConnectionDeviceFinder";
  private BluetoothAdapter mBtAdapter = null;
  private Context mContext;
  private IntentFilter filter = null;
  private ConnectionCallback mCallback;

  public BluetoothConnectionDeviceFinder(Context context, ConnectionCallback callback) {
    this.mContext = context;
    this.mCallback = callback;
  }

  public void enableBluetooth() {

    filter = new IntentFilter();
    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
    filter.addAction(BluetoothDevice.ACTION_FOUND);
    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
    mContext.registerReceiver(mReceiver, filter);

    mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    if (!mBtAdapter.isEnabled()) {
      Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      mContext.startActivity(enableIntent);
    }
  }

  public void searchSmartLamp() {

    boolean found = false;

    Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

    if (pairedDevices.size() > 0) {
      for (BluetoothDevice device : pairedDevices) {
        Log.d(TAG, device.getName());
        if (device.getName().equals("SmartLamp")) {
          Log.d(TAG, "SmartLamp found!");
          found = true;
          mCallback.foundSmartLamp(device.getAddress());
        }
      }
    }
    if (!found)
      doDiscovery();
  }

  private void doDiscovery() {
    if (mBtAdapter.isDiscovering()) {
      mBtAdapter.cancelDiscovery();
    }
    mBtAdapter.startDiscovery();
  }

  private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      Log.d(TAG, action);

      if (BluetoothDevice.ACTION_FOUND.equals(action)) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
          if (device.getName().equals("SmartLamp")) {
            Log.d(TAG, "SmartLamp found!");
            mCallback.foundSmartLamp(device.getAddress());
            // mBtAdapter.cancelDiscovery();
          }
        }
      }
      else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
        mContext.unregisterReceiver(mReceiver);
      }
      else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 1);
        Log.d(TAG, "Enabling" + state);
        if (state == 12) {
          searchSmartLamp();
        }
      }
    }
  };

}
