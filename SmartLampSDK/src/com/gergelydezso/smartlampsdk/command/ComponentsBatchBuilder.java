package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;

/**
 * ComponentsBatchBuilder class.
 */
public class ComponentsBatchBuilder {

  private int mServo1Value;
  private int mServo2Value;
  private int mServo3Value;
  private int mServo4Value;
  private int mServo5Value;
  private int mLedRedValue;
  private int mLedGreenValue;
  private int mLedBlueValue;


  /**
   * Adjust servo motor components.
   *
   * @param servoPin ServoMotor identifier.
   * @param value    angle of the ServoMotor.
   */
  public void adjustServoComponent(ServoMotorEntity servoPin, int value) {

    switch (servoPin.getValue()) {
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

  /**
   * Adjust the LED components.
   *
   * @param redValue   LedRGB red pin value
   * @param greenValue LedRGB green pin value
   * @param blueValue  LedRGB blue pin value
   */
  public void adjustLedComponent(int redValue, int greenValue, int blueValue) {
    this.mLedRedValue = redValue;
    this.mLedGreenValue = greenValue;
    this.mLedBlueValue = blueValue;
  }

  public String getCommand() {
    return "translated batch command";
  }
}
