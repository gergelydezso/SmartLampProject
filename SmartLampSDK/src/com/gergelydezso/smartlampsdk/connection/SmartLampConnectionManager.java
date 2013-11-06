package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;

import android.content.Context;

// TODO - CODE REVIEW - andrei|Oct 29, 2013 - don't see it's purpose
public class SmartLampConnectionManager {

  private ConnectionFactory mFactory = new ConnectionFactory();
  private UserAppContextHolder mConext = new UserAppContextHolder();
  private ConnectionStatusListener mListener;

  public SmartLampConnectionManager(Context context) {
    mConext.setContext(context);
  }

  public void makeConnection(ConnectionType type, ConnectionStatusListener listener) {
    this.mListener = listener;
    mFactory.bulidBridge(type, new BridgeBuildCallback() {

      @Override
      public void onConnectionReady(SmartLampCommunicationBridge bridge) {
        mListener.onConnectionReady(new SmartLampAPI(bridge));

      }

      @Override
      public void onConnectionFailed() {

      }

      @Override
      public void onConnectionLost() {

      }
    });
  }

}
