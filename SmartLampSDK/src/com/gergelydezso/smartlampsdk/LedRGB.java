package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

public class LedRGB {

  private SmartLampCommunicationBridge mPipe;
  private String mLedID = "led";

  public LedRGB(SmartLampCommunicationBridge pipe) {
    mPipe = pipe;
  }

  public void setLedValue(int red, int green, int blue, CommandCallback callback) {

    mPipe.sendSetLedCommand(red, green, blue, callback);
    Log.v("LedRGB", "Red: " + red + ", Green: " + green + ",Blue: " + blue);
  }

  public void getLedState(CommandCallback callback) {
    mPipe.sendRequestCommand(mLedID, callback);
  }

}
