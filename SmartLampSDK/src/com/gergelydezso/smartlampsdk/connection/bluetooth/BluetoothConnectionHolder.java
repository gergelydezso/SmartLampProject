package com.gergelydezso.smartlampsdk.connection.bluetooth;

public class BluetoothConnectionHolder {

  private static BluetoothConnectionService mConnectionService;

  public void setConnection(BluetoothConnectionService conncetion) {
    BluetoothConnectionHolder.mConnectionService = conncetion;
  }

  public static BluetoothConnectionService getConnection() {
    return BluetoothConnectionHolder.mConnectionService;
  }

}
