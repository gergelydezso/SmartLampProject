package com.gergelydezso.smartlampsdk.connection.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

// TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - Might be appropriate to implement a Finite State Machine instead of using the if/else constructs to check the state. Ask me for an FSM example.
public class BluetoothConnectionService {

  private CommandCallback mCallback;
  private String mOutValue;
  private static final String TAG = "BluetoothConnectionService";
  private static final boolean D = true;
  // Unique UUID for this application
  private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  private final BluetoothAdapter mAdapter;
  private final Handler mHandler;
  private ConnectThread mConnectThread;
  private ConnectedThread mConnectedThread;
  private int mState;
  // Constants that indicate the current connection state
  public static final int STATE_NONE = 0;
  public static final int STATE_CONNECTING = 2;
  public static final int STATE_CONNECTED = 3;

  public BluetoothConnectionService(Handler handler) {
    mAdapter = BluetoothAdapter.getDefaultAdapter();
    mState = STATE_NONE;
    mHandler = handler;
  }

  private synchronized void setState(int state) {
    if (D) {
      Log.d(TAG, "setState() " + mState + " -> " + state);
    }
    mState = state;
    mHandler.obtainMessage(BluetoothConnectionControl.MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
  }

  public synchronized int getState() {
    return mState;
  }

  public synchronized void connect(BluetoothDevice device) {
    if (D) {
      Log.d(TAG, "connect to: " + device);
    }

    if (mState == STATE_CONNECTING) {
      if (mConnectThread != null) {
        mConnectThread.cancel();
        mConnectThread = null;
      }
    }

    if (mConnectedThread != null) {
      mConnectedThread.cancel();
      mConnectedThread = null;
    }

    mConnectThread = new ConnectThread(device);
    mConnectThread.start();
    setState(STATE_CONNECTING);
  }

  public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
    if (D) {
      Log.d(TAG, "connected");
    }

    if (mConnectThread != null) {
      mConnectThread.cancel();
      mConnectThread = null;
    }

    if (mConnectedThread != null) {
      mConnectedThread.cancel();
      mConnectedThread = null;
    }

    mConnectedThread = new ConnectedThread(socket);
    mConnectedThread.start();
    // Send the name of the connected device back to the UI Activity
    Message msg = mHandler.obtainMessage(BluetoothConnectionControl.MESSAGE_DEVICE_NAME);
    Bundle bundle = new Bundle();
    bundle.putString(BluetoothConnectionControl.DEVICE_NAME, device.getName());
    msg.setData(bundle);
    mHandler.sendMessage(msg);
    setState(STATE_CONNECTED);
  }

  public synchronized void disconnect() {

    if (mConnectThread != null) {
      mConnectThread.cancel();
      mConnectThread = null;
    }

    if (mConnectedThread != null) {
      mConnectedThread.cancel();
      mConnectedThread = null;
    }
    setState(STATE_NONE);
  }

  public void write(String out, CommandCallback callback) {

    Log.d("BluetoothConnectionService", "command: " + out);

    this.mCallback = callback;
    this.mOutValue = out;
    ConnectedThread connectedThread;
    synchronized (this) {
      if (mState != STATE_CONNECTED) {
        return;
      }
      connectedThread = mConnectedThread;
    }
    connectedThread.write(out.getBytes());
  }

  private void connectionFailed() {
    Message msg = mHandler.obtainMessage(BluetoothConnectionControl.MESSAGE_TOAST);
    Bundle bundle = new Bundle();
    bundle.putString(BluetoothConnectionControl.TOAST, "Unable to connect device");
    msg.setData(bundle);
    mHandler.sendMessage(msg);
  }

  private void connectionLost() {
    Message msg = mHandler.obtainMessage(BluetoothConnectionControl.MESSAGE_TOAST);
    Bundle bundle = new Bundle();
    bundle.putString(BluetoothConnectionControl.TOAST, "Device connection was lost");
    msg.setData(bundle);
    mHandler.sendMessage(msg);
  }

  private class ConnectThread extends Thread {

    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;

    public ConnectThread(BluetoothDevice device) {
      mmDevice = device;
      BluetoothSocket tmp = null;

      try {
        tmp = device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
      }
      catch (IOException e) {
        Log.e(TAG, "Create() failed", e);
      }
      mmSocket = tmp;
    }

    public void run() {
      Log.i(TAG, "BEGIN mConnectThread");
      setName("ConnectThread");
      mAdapter.cancelDiscovery();
      try {
        mmSocket.connect();
      }
      catch (IOException e) {
        try {
          mmSocket.close();
        }
        catch (IOException e2) {
          Log.e(TAG, "unable to close() socket during connection failure", e2);
        }
        connectionFailed();
        return;
      }
      synchronized (BluetoothConnectionService.this) {
        mConnectThread = null;
      }
      connected(mmSocket, mmDevice);
    }

    public void cancel() {
      try {
        mmSocket.close();
      }
      catch (IOException e) {
        Log.e(TAG, "close() of connect socket failed", e);
      }
    }
  }

  private class ConnectedThread extends Thread {

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ConnectedThread(BluetoothSocket socket) {
      Log.d(TAG, "create ConnectedThread: ");
      mmSocket = socket;
      InputStream tmpIn = null;
      OutputStream tmpOut = null;

      try {
        tmpIn = socket.getInputStream();
        tmpOut = socket.getOutputStream();
      }
      catch (IOException e) {
        Log.e(TAG, "temp sockets not created", e);
      }
      mmInStream = tmpIn;
      mmOutStream = tmpOut;
    }

    public void run() {
      Log.i(TAG, "BEGIN mConnectedThread");
      byte[] buffer = new byte[1024];
      int bytes;

      while (true) {
        try {
          bytes = mmInStream.read(buffer);
          String inValue = new String(buffer, 0, 1);
//          Log.d("BluetoothConnectionService", "InValue: " + inValue);

          if (inValue.equals(mOutValue)) {
            mCallback.onSuccess();
          }
          else {
            mCallback.onError();
          }

          if (mOutValue.equals(Integer.toString(9))) {

            mCallback.onResult(inValue);
          }
          mHandler.obtainMessage(BluetoothConnectionControl.MESSAGE_READ, bytes, -1, buffer).sendToTarget();
        }
        catch (IOException e) {
          connectionLost();
          break;
        }
      }
    }

    public void write(byte[] buffer) {
      try {
        mmOutStream.write(buffer);
        mHandler.obtainMessage(BluetoothConnectionControl.MESSAGE_WRITE, -1, -1, buffer).sendToTarget();
      }
      catch (IOException e) {
        Log.e(TAG, "Exception during write", e);
      }
    }

    public void cancel() {
      try {
        mmSocket.close();
      }
      catch (IOException e) {
        Log.e(TAG, "close() of connect socket failed", e);
      }
    }

  }

}
