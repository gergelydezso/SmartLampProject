package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.LedPinTypes;
import com.gergelydezso.smartlampsdk.LedRGB;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class LedSetCommand implements Command {

  private SmartLamp mLamp;
  private LedPinTypes mPin;
  private int mIntensity;
  private CommandCallback mCallback;
  private LedRGB mLed;

  public LedSetCommand(SmartLamp lamp, LedPinTypes pin, int intensity, CommandCallback callback) {
    this.mLamp = lamp;
    this.mPin = pin;
    this.mIntensity = intensity;
    this.mCallback = callback;

  }

  @Override
  public void execute() {
    mLed = mLamp.getLed();
    mLed.setLedValue(mPin, mIntensity, mCallback);
  }

}
