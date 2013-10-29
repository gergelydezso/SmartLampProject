package com.gergelydezso.smartlampsdk.connection;

// TODO - CODE REVIEW - andrei|Oct 29, 2013 - BridgeCreationListener
public interface BridgeBuildCallback {

  public void onConnectionReady(SmartLampCommunicationBridge bridge);

  public void onConnectionFailed();

  public void onConnectionLost();

}
