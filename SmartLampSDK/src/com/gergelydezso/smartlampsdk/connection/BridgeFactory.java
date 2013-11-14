package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothCommunicationBridge;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectedListener;

public class BridgeFactory {

  private SmartLampCommunicationBridge bridge;
  private BridgeCreationListener mCallback;

  public void buildBridge(ConnectionType type, BridgeCreationListener callback) {

    this.mCallback = callback;

    if (type == ConnectionType.BLUETOOTH) {
      bridge = new BluetoothCommunicationBridge();
      bridge.connect(new ConnectedListener() {

        @Override
        public void onConnectionReady() {
          mCallback.onConnectionReady(bridge);

        }

        @Override
        public void onConnectionFailed() {
          mCallback.onConnectionFailed();

        }

        @Override
        public void onConnectionLost() {
          mCallback.onConnectionLost();

        }
      });
    }

    if (type == ConnectionType.WIFI) {
      // Build WiFi connection
    }

  }

}
