package com.gergelydezso.smartlampsdk.connection.bluetooth;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

public class BluetoothCommunicationBridge implements SmartLampCommunicationBridge {

  private BluetoothConnectionService mConnectionService;

  public BluetoothCommunicationBridge() {
    mConnectionService = BluetoothConnectionHolder.getConnection();
  }

  @Override
  public void sendSetLedCommand(int red, int green, int blue, CommandCallback callback) {
    mConnectionService.write("L" + fillWithZero(red) + fillWithZero(green) + fillWithZero(blue), callback);
    Log.d("BluetoothCommunicationBridge", "Command: " + "L" + fillWithZero(red) + fillWithZero(green)
        + fillWithZero(blue));
  }

  @Override
  public void sendSetServoCommand(String id, int value, CommandCallback callback) {

    int servoId = 0;

    if (id.equals("SERVO1")) {
      servoId = 1;
    }
    else if (id.equals("SERVO5")) {
      servoId = 5;
    }

    mConnectionService.write("S" + Integer.toString(servoId) + fillWithZero(value), callback);
  }

  @Override
  public void sendRequestCommand(String id, CommandCallback callback) {
    mConnectionService.write(id, callback);
  }

  public String fillWithZero(int value) {

    String result = String.format("%03d", value);
    Log.d("BluetoothommunicationBrdige", result);
    return result;

  }

}