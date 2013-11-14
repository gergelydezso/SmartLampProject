package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.LedRGB;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class LedCommand implements Command {

  private int mRed;
  private int mGreen;
  private int mBlue;
  private CommandCallback mCallback;
  private LedRGB mLed;
  private long mTimeTicket;

  public LedCommand(LedRGB led, int red, int green, int blue, CommandCallback callback) {
    this.mLed = led;
    this.mRed = red;
    this.mGreen = green;
    this.mBlue = blue;
    this.mCallback = callback;

  }

  @Override
  public void execute() {
    mLed.setLedValue(mRed, mGreen, mBlue, mCallback);
  }

  @Override
  public void setTimeStamp(long time) {
    this.mTimeTicket = time;

  }

  @Override
  public long getTimeStamp() {
    return mTimeTicket;
  }

}
