package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothCommunicationBridge;

public class SmartLamp {

  // - last code review modifications: CommunicationBridege -> SmartLampCommunicationBridge
  // BluetoothConnection -> BluetoothCommunicationBridge;
  // private SmartLampCommunicationBridge mPipe;

  private SmartLampCommunicationBridge mPipe = new BluetoothCommunicationBridge();

  private ServoMotor mServo = new ServoMotor(mPipe);
  private LedRGB mLed = new LedRGB(mPipe);

  // public SmartLamp(SmartLampCommunicationBridge bridge) {
  // this.mPipe = bridge;
  // }

  public LedRGB getLed() {
    return mLed;
  }

  public ServoMotor getServo() {
    return mServo;
  }

}
