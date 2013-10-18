package com.gergelydezso.smartlampsdk.sampleapp.motioncontrol;

/**
 * Created by dezso.gergely on 9/26/13.
 */
public class LampModel {

  private int servo1Angle;
  private int servo2Angle;
  private int servo3Angle;
  private int servo4Angle;
  private int servo5Angle;

  public LampModel() {

  }

  public void setServo1Angle(int angle) {
    this.servo1Angle = angle;
  }

  public void setServo2Angle(int angle) {
    this.servo1Angle = angle;
  }

  public void setServo3Angle(int angle) {
    this.servo1Angle = angle;
  }

  public void setServo4Angle(int angle) {
    this.servo1Angle = angle;
  }

  public void setServo5Angle(int angle) {
    this.servo1Angle = angle;
  }

  public int getServo1Angle() {
    return servo1Angle;
  }

  public int getServo2Angle() {
    return servo2Angle;
  }

  public int getServo3Angle() {
    return servo3Angle;
  }

  public int getServo4Angle() {
    return servo4Angle;
  }

  public int getServo5Angle() {
    return servo5Angle;
  }
}
