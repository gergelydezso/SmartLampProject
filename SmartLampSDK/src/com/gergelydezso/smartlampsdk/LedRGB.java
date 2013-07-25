package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

public class LedRGB {

  private SmartLampCommunicationBridge mBridge;
  private static final String mLedID = "led";

  public LedRGB(SmartLampCommunicationBridge bridge) {
    mBridge = bridge;
  }

  public void setLedValue(int red, int green, int blue, CommandCallback callback) {

    mBridge.sendSetLedCommand(red, green, blue, callback);
    Log.v("LedRGB", "Red: " + red + ", Green: " + green + ",Blue: " + blue);
  }

  public void getLedState(CommandCallback callback) {
    mBridge.sendRequestCommand(mLedID, callback);
  }

}
