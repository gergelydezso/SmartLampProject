package com.gergelydezso.smartlampsdk.connection;

import android.content.Context;

import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionControl;

public class SmartLampConnectionManager {

  private Context mContext;

  public SmartLampConnectionManager(Context context) {
    this.mContext = context;
  }

  public void makeConnection(ConnectionStatusListener connectionListener) {

    BluetoothConnectionControl connectionControl = new BluetoothConnectionControl(mContext, connectionListener);
    connectionControl.makeConnection();
  }

  public void breakConnection() {

  }

}
