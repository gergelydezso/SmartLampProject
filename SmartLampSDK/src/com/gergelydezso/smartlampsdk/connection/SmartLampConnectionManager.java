package com.gergelydezso.smartlampsdk.connection;

import android.content.Context;

import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionControl;

public class SmartLampConnectionManager {

  private Context mContext;

  public SmartLampConnectionManager(Context context) {
    this.mContext = context;
  }

  public void makeConnection(ConnectionStatusListener connectionListener) {

    BluetoothConnectionControl b = new BluetoothConnectionControl(mContext, connectionListener);
    // connectionListener.onConnectionReady(new SmartLampAPI(new BluetoothCommunicationBridge()));

  }

  public void breakConnection() {

  }

}
