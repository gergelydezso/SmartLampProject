package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothCommunicationBridge;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectedListener;

public class ConnectionFactory {

  private SmartLampCommunicationBridge bridge;
  private BridgeBuildCallback mCallback;

  public void bulidBridge(ConnectionType type, BridgeBuildCallback callback) {

    this.mCallback = callback;

    if (type == ConnectionType.BLUETOOTH) {
      bridge = new BluetoothCommunicationBridge();
      bridge.connect(new ConnectedListener() {

        @Override
        public void onConnected() {
          mCallback.onConnected(bridge);

        }
      });
    }

    if (type == ConnectionType.WIFI) {
      // Build WiFi connection
    }

  }

}
