package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class LedRGB {

  private CommunicationBridge mPipe;
  private String mLedID = "led";

  public LedRGB(CommunicationBridge pipe) {
    mPipe = pipe;
  }

  public void setLedValue(LedPinTypes pin, int intensity, CommandCallback callback) {

    mPipe.sendSetCommand(pin.name(), intensity, callback);
    Log.v("LedRGB", "LedPin: " + pin + " Value: " + intensity);
  }

  public void getLedState(CommandCallback callback) {
    // Integer.toString(9) - jut a test
    mPipe.sendRequestCommand(Integer.toString(9), callback);
  }

}
