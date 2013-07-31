package com.gergelydezso.smartlampsdk.connection.bluetooth;

public interface ConnectedListener {

  public void onConnectionReady();

  public void onConnectionFailed();

  public void onConnectionLost();

}
