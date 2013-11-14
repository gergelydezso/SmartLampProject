package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.SmartLamp;

public interface ConnectionListener {

  public void onConnectionReady(SmartLamp lamp);

  public void onConnectionFailed();

  public void onConnectionLost();

}
