package com.gergelydezso.smartlampsdk.command;


import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

public class BatchCommand implements Command {

  private long mTimeStamp;
  private CommandCallback mCallback;
  private SmartLampCommunicationBridge mBridge;
  private ComponentsBatchBuilder mConfig;


  public BatchCommand(SmartLampCommunicationBridge bridge, ComponentsBatchBuilder config, CommandCallback callback) {
    this.mCallback = callback;
    this.mConfig = config;
    this.mBridge = bridge;
  }

  @Override
  public void execute() {
    mBridge.sendSetAllCommand(mConfig.getCommand(), mCallback);
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
