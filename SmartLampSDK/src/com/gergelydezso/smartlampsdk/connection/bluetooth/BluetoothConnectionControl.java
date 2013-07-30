package com.gergelydezso.smartlampsdk.connection.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class BluetoothConnectionControl {

  public static final int MESSAGE_STATE_CHANGE = 1;
  public static final int MESSAGE_READ = 2;
  public static final int MESSAGE_WRITE = 3;
  public static final int MESSAGE_DEVICE_NAME = 4;
  public static final int MESSAGE_TOAST = 5;
  public static final String DEVICE_NAME = "device_name";
  public static final String TOAST = "toast";
  private static final boolean D = true;

  private Context mContext;
  private static final String TAG = "BluetoothConnecctionControl";
  private BluetoothDeviceFinder mDeviceFinder = null;
  public BluetoothConnectionService mConnectionService = null;
  public BluetoothAdapter mBluetoothAdapter = null;
  private ConnectedListener mListener = null;

  public BluetoothConnectionControl(Context context) {
    this.mContext = context;
  }

  public void makeConnection(ConnectedListener listener) {

    this.mListener = listener;

    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    mConnectionService = new BluetoothConnectionService(mHandler);
    mDeviceFinder = new BluetoothDeviceFinder(mContext, new FinderCallback() {

      @Override
      public void foundSmartLamp(String address) {
        Log.d(TAG, address);
        connectDevice(address);
      }
    });
    mDeviceFinder.initConnection();
  }

  public void connectDevice(String address) {
    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
    mConnectionService.connect(device);
  }

  private final Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
      case MESSAGE_STATE_CHANGE:
        if (D)
          Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);

        switch (msg.arg1) {
        case BluetoothConnectionService.STATE_CONNECTED:
          BluetoothConnectionHolder connectionHolder = new BluetoothConnectionHolder();
          connectionHolder.setConnection(mConnectionService);

          mListener.onConnected();

          Toast.makeText(mContext, "Connected to SmartLamp", Toast.LENGTH_SHORT).show();
          break;
        case BluetoothConnectionService.STATE_CONNECTING:
          // TODO
          break;
        case BluetoothConnectionService.STATE_NONE:
          // TODO
          break;
        }
        break;
      case MESSAGE_WRITE:
        break;
      case MESSAGE_READ:
        break;
      case MESSAGE_DEVICE_NAME:
        break;
      case MESSAGE_TOAST:
        break;
      }
    }
  };

}
