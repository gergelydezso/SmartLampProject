package com.gergelydezso.smartlampsdk.sampleapp;

import android.app.Application;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.sampleapp.notifications.DeviceNotifications;

public class SmartLampSampleApplication extends Application {

  @Override
  public void onCreate() {
    DeviceNotifications.init(getApplicationContext());
  }

}
