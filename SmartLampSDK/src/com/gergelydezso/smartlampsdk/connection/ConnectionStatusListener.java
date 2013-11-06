package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;

public interface ConnectionStatusListener {

  public void onConnectionReady(SmartLampAPI api);

  public void onConnectionFailed();

  public void onConnectionLost();

}
