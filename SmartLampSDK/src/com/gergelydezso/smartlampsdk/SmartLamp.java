package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

// TODO - CODE REVIEW - andrei|Oct 29, 2013 - javadoc
public class SmartLamp {

  private LedRGB mLed = null;
  private ServoMotor mServo = null;
  private SmartLampCommunicationBridge mBridge = null;

  public SmartLamp(SmartLampCommunicationBridge bridge) {
    this.mBridge = bridge;
    mServo = new ServoMotor(mBridge);
    mLed = new LedRGB(mBridge);
  }

  public LedRGB getLed() {
    return mLed;
  }

  public ServoMotor getServo() {
    return mServo;
  }

  public SmartLampCommunicationBridge getBridge() {
    return mBridge;
  }

  public void setAllParameters() {

  }

}
