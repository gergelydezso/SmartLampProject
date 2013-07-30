package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotor;
import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class ServoSetCommand implements Command {

  private SmartLamp mLamp;
  private ServoMotorEntity mId;
  private int mDegree;
  private CommandCallback mCallback;
  private ServoMotor mServo;
  private long mTimeTicket;

  public ServoSetCommand(SmartLamp lamp, ServoMotorEntity id, int degree, CommandCallback callback) {
    this.mLamp = lamp;
    this.mId = id;
    this.mDegree = degree;
    this.mCallback = callback;
  }

  public ServoMotorEntity getServoID() {
    return mId;
  }

  public int getServoDegree() {
    return mDegree;
  }

  @Override
  public void execute() {
    mServo = mLamp.getServo();
    mServo.setServoPosition(mId, mDegree, mCallback);
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
