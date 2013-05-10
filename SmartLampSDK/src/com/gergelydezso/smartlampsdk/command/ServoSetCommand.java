package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotor;
import com.gergelydezso.smartlampsdk.ServoMotorEntities;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class ServoSetCommand implements Command {

  private SmartLamp mLamp;
  private ServoMotorEntities mId;
  private int mDegree;
  private CommandCallback mCallback;
  private ServoMotor mServo;

  public ServoSetCommand(SmartLamp lamp, ServoMotorEntities id, int degree, CommandCallback callback) {
    this.mLamp = lamp;
    this.mId = id;
    this.mDegree = degree;
    this.mCallback = callback;

  }

  @Override
  public void execute() {
    mServo = mLamp.getServo();
    mServo.setServoPosition(mId, mDegree, mCallback);
  }

}
