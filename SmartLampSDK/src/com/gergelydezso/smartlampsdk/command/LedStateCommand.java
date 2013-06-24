package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.LedRGB;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class LedStateCommand implements Command {

  private SmartLamp mLamp;
  private LedRGB mLed;
  private CommandCallback mCallback;

  public LedStateCommand(SmartLamp lamp, CommandCallback callback) {
    this.mLamp = lamp;
    this.mCallback = callback;
  }

  @Override
  public void execute() {
    mLed = mLamp.getLed();
    mLed.getLedState(mCallback);

  }

}
