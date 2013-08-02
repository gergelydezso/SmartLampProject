package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.SmartLamp;

public class SetAllCommand implements Command {

  private long mTimeTicket;
  private CommandCallback mCallback;
  private SmartLamp mLamp;
  private AllCommandConfiguration mConfig;

  public SetAllCommand(SmartLamp lamp, AllCommandConfiguration config, CommandCallback callback) {
    this.mCallback = callback;
    this.mLamp = lamp;
    this.mConfig = config;
  }

  @Override
  public void execute() {
    mLamp.getBridge().sendSetAllCommand(mConfig.getCommand(), mCallback);

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
