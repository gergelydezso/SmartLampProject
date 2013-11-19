package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotor;
import com.gergelydezso.smartlampsdk.ServoMotorEntity;

public class ServoCommand implements Command {

  private ServoMotorEntity mId;
  private int mDegree;
  private CommandCallback mCallback;
  private ServoMotor mServo;
  private long mTimeTicket;

  public ServoCommand(ServoMotor motor, ServoMotorEntity id, int degree, CommandCallback callback) {
    this.mServo = motor;
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
    mServo.setServoPosition(mId, mDegree, mCallback);
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
