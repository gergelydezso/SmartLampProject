package com.gergelydezso.smartlampsdk.sampleapp;


import com.gergelydezso.smartlampsdk.SmartLamp;

public class SmartLampHolder {

  private static SmartLamp mLamp = null;

  public static void setSmartLamp(SmartLamp lamp) {
    mLamp = lamp;
  }

  public static SmartLamp getSmartLamp() {
    return SmartLampHolder.mLamp;
  }

}
