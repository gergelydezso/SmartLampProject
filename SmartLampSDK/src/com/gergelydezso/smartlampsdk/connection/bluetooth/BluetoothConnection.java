package com.gergelydezso.smartlampsdk.connection.bluetooth;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class BluetoothConnection implements CommunicationBridge {

  // private static final String TAG = "BluetoothConnection";
  private BluetoothConnectionService mConnectionService;

  public BluetoothConnection() {
    mConnectionService = BluetoothConnectionHolder.getConnection();
  }

  @Override
  public void sendSetCommand(String id, int value, CommandCallback callback) {
    mConnectionService.write(Integer.toString(value), callback);
  }

  @Override
  public void sendRequestCommand(String id, CommandCallback callback) {
    mConnectionService.write(id, callback);
  }
}
