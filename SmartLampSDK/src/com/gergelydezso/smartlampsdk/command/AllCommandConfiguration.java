package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;

public class AllCommandConfiguration {

  private int mServo1Value;
  private int mServo2Value;
  private int mServo3Value;
  private int mServo4Value;
  private int mServo5Value;
  private int mLedRedValue;
  private int mLedGreenValue;
  private int mLedBlueValue;

  public void setServoValue(ServoMotorEntity id, int value) {

    switch (id.getValue()) {
      case 1:
        this.mServo1Value = value;
        break;
      case 2:
        this.mServo2Value = value;
        break;
      case 3:
        this.mServo3Value = value;
        break;
      case 4:
        this.mServo4Value = value;
        break;
      case 5:
        this.mServo5Value = value;
        break;
    }

  }

  public void setLedColor(int redVlue, int greenValue, int blueValue) {
    this.mLedRedValue = redVlue;
    this.mLedGreenValue = greenValue;
    this.mLedBlueValue = blueValue;
  }

  public String getCommand() {
    return "111111111111111111111111";
  }

}
