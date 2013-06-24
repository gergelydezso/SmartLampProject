package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotor;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class ServoStateCommand implements Command {

  private SmartLamp mLamp;
  private ServoMotor mServo;
  private CommandCallback mCallback;

  public ServoStateCommand(SmartLamp lamp, CommandCallback callback) {
    this.mLamp = lamp;
    this.mCallback = callback;
  }

  @Override
  public void execute() {
    mServo = mLamp.getServo();
    mServo.getServoState(mCallback);
  }

}
