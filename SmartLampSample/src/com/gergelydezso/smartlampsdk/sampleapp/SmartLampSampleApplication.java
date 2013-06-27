package com.gergelydezso.smartlampsdk.sampleapp;

import com.gergelydezso.smartlampsdk.sampleapp.notifications.DeviceNotifications;

import android.app.Application;

public class SmartLampSampleApplication extends Application {

  @Override
  public void onCreate() {
    DeviceNotifications.init(getApplicationContext());
  }
  
}
