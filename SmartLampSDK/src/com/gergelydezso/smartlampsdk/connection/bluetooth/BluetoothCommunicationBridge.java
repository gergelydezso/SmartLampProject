package com.gergelydezso.smartlampsdk.connection.bluetooth;

import android.util.Log;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;
import com.gergelydezso.smartlampsdk.connection.UserAppContextHolder;

public class BluetoothCommunicationBridge implements SmartLampCommunicationBridge {

  private BluetoothConnectionService mConnectionService;
  private ConnectedListener mListener;

  public BluetoothCommunicationBridge() {
  }

  @Override
  public void connect(ConnectedListener listener) {

    this.mListener = listener;

    BluetoothConnectionControl connectionControl = new BluetoothConnectionControl(UserAppContextHolder.getContext());
    connectionControl.makeConnection(new ConnectedListener() {

      @Override
      public void onConnectionReady() {
        mConnectionService = BluetoothConnectionHolder.getConnection();
        mListener.onConnectionReady();

      }

      @Override
      public void onConnectionFailed() {
        mListener.onConnectionFailed();

      }

      @Override
      public void onConnectionLost() {
        mListener.onConnectionLost();
      }
    });

  }

  @Override
  public void disconnect() {
    mConnectionService.disconnect();
  }

  @Override
  public void sendSetLedCommand(int red, int green, int blue, CommandCallback callback) {
    mConnectionService.write("L" + fillWithZero(red) + fillWithZero(green) + fillWithZero(blue), callback);
    // Log.d("BluetoothCommunicationBridge", "Command: " + "L" + fillWithZero(red) + fillWithZero(green)
    // + fillWithZero(blue));
  }

  @Override
  public void sendSetServoCommand(ServoMotorEntity id, int value, CommandCallback callback) {
    mConnectionService.write("S" + Integer.toString(id.getValue()) + fillWithZero(value), callback);
  }

  @Override
  public void sendRequestCommand(CommandCallback callback) {
    mConnectionService.write("Status", callback);
  }

  @Override
  public void sendSetAllCommand(String command, CommandCallback callback) {
    mConnectionService.write(command, callback);
    Log.d("BluetoothCommunicationBridge", command);
  }

  public String fillWithZero(int value) {

    String result = String.format("%03d", value);
    Log.d("BluetoothommunicationBrdige", result);
    return result;

  }

}