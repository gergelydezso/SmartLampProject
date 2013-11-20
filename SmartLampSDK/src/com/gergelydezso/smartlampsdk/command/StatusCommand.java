package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

/**
 * Created by dezso.gergely on 11/14/13.
 */
public class StatusCommand implements Command {

  private long mTimeStamp;
  private CommandCallback mCallback;
  private SmartLampCommunicationBridge mBridge;

  public StatusCommand(SmartLampCommunicationBridge bridge, CommandCallback callback) {
    this.mBridge = bridge;
    this.mCallback = callback;
  }

  @Override
  public void execute() {
    mBridge.sendRequestCommand(mCallback);
  }

  @Override
  public void setTimeStamp(long time) {
    this.mTimeStamp = time;
  }

  @Override
  public long getTimeStamp() {
    return mTimeStamp;
  }
}
