package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnection;

public class SmartLamp {

  private CommunicationBridge mPipe = new BluetoothConnection();

  private ServoMotor mServo = new ServoMotor(mPipe);
  private LedRGB mLed = new LedRGB(mPipe);

  public LedRGB getLed() {
    return mLed;
  }

  public ServoMotor getServo() {
    return mServo;
  }

}
