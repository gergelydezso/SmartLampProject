package com.gergelydezso.smartlampsdk.connection;

public interface BridgeCreationListener {

  public void onConnectionReady(SmartLampCommunicationBridge bridge);

  public void onConnectionFailed();

  public void onConnectionLost();

}
