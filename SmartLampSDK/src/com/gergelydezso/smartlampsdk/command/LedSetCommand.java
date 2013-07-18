package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.LedRGB;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class LedSetCommand implements Command {

  private SmartLamp mLamp;
  private int mRed;
  private int mGreen;
  private int mBlue;
  private CommandCallback mCallback;
  private LedRGB mLed;
  private long mTimeTicket;

  public LedSetCommand(SmartLamp lamp, int red, int green, int blue, CommandCallback callback) {
    this.mLamp = lamp;
    this.mRed = red;
    this.mGreen = green;
    this.mBlue = blue;
    this.mCallback = callback;

  }

  @Override
  public void execute() {
    mLed = mLamp.getLed();
    mLed.setLedValue(mRed, mGreen, mBlue, mCallback);
  }

  @Override
  public void setTimeTicket(long time) {
    this.mTimeTicket = time;

  }

  @Override
  public long getTimeTicket() {
    return mTimeTicket;
  }

}
