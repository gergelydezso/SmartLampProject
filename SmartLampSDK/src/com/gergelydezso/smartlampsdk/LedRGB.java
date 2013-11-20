package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

public class LedRGB {

  private SmartLampCommunicationBridge mBridge;

  public LedRGB(SmartLampCommunicationBridge bridge) {
    mBridge = bridge;
  }

  public void setLedValue(int red, int green, int blue, CommandCallback callback) {
    mBridge.sendSetLedCommand(red, green, blue, callback);
//    Log.v("LedRGB", "Red: " + red + ", Green: " + green + ",Blue: " + blue);
  }
}
