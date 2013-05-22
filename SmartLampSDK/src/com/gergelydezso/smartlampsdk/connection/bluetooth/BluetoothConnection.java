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
  public void sendSetLedCommand(int red, int green, int blue, CommandCallback callback) {
    mConnectionService.write(Integer.toString(red), callback);
  }

  @Override
  public void sendSetServoCommand(String id, int value, CommandCallback callback) {
    mConnectionService.write(id, callback);
  }

  @Override
  public void sendRequestCommand(String id, CommandCallback callback) {
    mConnectionService.write(id, callback);
  }

}