package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothCommunicationBridge;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectedListener;

// TODO - CODE REVIEW - andrei|Oct 29, 2013 - this is a Bridge Factory
public class ConnectionFactory {

  private SmartLampCommunicationBridge bridge;
  private BridgeBuildCallback mCallback;

  // TODO - CODE REVIEW - andrei|Oct 29, 2013 - take care with correct spelling
  public void bulidBridge(ConnectionType type, BridgeBuildCallback callback) {

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

        }

        @Override
        public void onConnectionLost() {

        }
      });
    }

    if (type == ConnectionType.WIFI) {
      // Build WiFi connection
    }

  }

}
