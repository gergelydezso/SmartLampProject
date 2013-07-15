package com.gergelydezso.smartlampsdk.sampleapp;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;

public class SmartLampAPIHolder {

  private static SmartLampAPI mApi;

  public void setAPI(SmartLampAPI api) {
    SmartLampAPIHolder.mApi = api;
  }

  public static SmartLampAPI getApi() {
    return SmartLampAPIHolder.mApi;
  }

}
