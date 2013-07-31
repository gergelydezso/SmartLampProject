package com.gergelydezso.smartlampsdk.connection;

public interface BridgeBuildCallback {

  public void onConnectionReady(SmartLampCommunicationBridge bridge);

  public void onConnectionFailed();

  public void onConnectionLost();

}
