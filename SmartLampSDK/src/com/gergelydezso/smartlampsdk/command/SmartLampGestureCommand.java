package com.gergelydezso.smartlampsdk.command;

public class SmartLampGestureCommand implements Command {

  private long mTimeTicket;

  @Override
  public void execute() {
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
