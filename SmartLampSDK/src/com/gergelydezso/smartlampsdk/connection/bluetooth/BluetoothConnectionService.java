package com.gergelydezso.smartlampsdk.connection.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.gergelydezso.smartlampsdk.connection.ValueChackCallback;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

// TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - Might be appropriate to implement a Finite State Machine instead of using the if/else constructs to check the state. Ask me for an FSM example.
public class BluetoothConnectionService {

	private ValueChackCallback mValueChack;

	// BluetoothChat - Activity onDestroy() called
	private boolean mDestoryed;

	private static final String TAG = "BluetoothChatService";
	private static final boolean D = true;

	// Unique UUID for this application
	private static final UUID MY_UUID_SECURE = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");

	private final BluetoothAdapter mAdapter;

	private ConnectThread mConnectThread;
	private ConnectedThread mConnectedThread;
	private int mState;

	// public final Handler mHandler;

	// Constants that indicate the current connection state
	public static final int STATE_NONE = 0;
	public static final int STATE_LISTEN = 1;
	public static final int STATE_CONNECTING = 2;
	public static final int STATE_CONNECTED = 3;

	public BluetoothConnectionService() {

		mAdapter = BluetoothAdapter.getDefaultAdapter();
		mState = STATE_NONE;
		// mHandler = handler;
	}

	private synchronized void setState(int state) {
		if (D)
			Log.d(TAG, "setState() " + mState + " -> " + state);
		mState = state;

		// Give the new state to the Handler so the UI Activity can update
		// mHandler.obtainMessage(
		// BluetoothConnectionActivity.MESSAGE_STATE_CHANGE, state, -1)
		// .sendToTarget();
	}

	public synchronized int getState() {
		return mState;
	}

	public synchronized void start() {

		if (D)
			Log.d(TAG, "start");

		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

	}

	public synchronized void connect(BluetoothDevice device) {
		if (D)
			Log.d(TAG, "connect to: " + device);

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

	public synchronized void connected(BluetoothSocket socket,
			BluetoothDevice device) {
		if (D)
			Log.d(TAG, "connected");

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
		// Message msg = mHandler
		// .obtainMessage(BluetoothConnectionActivity.MESSAGE_DEVICE_NAME);
		// Bundle bundle = new Bundle();
		// bundle.putString(BluetoothConnectionActivity.DEVICE_NAME,
		// device.getName());
		// msg.setData(bundle);
		// mHandler.sendMessage(msg);

		setState(STATE_CONNECTED);
	}

	/**
	 * Stop all threads
	 */
	public synchronized void stop() {
		if (D)
			Log.d(TAG, "stop");

		mDestoryed = true;

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

	public void write(byte[] out, ValueChackCallback valueChack) {

		this.mValueChack = valueChack;

		ConnectedThread r;
		synchronized (this) {
			if (mState != STATE_CONNECTED)
				return;
			r = mConnectedThread;
		}
		r.write(out);
	}

	private void connectionFailed() {
		// Send a failure message back to the Activity
		// Message msg = mHandler
		// .obtainMessage(BluetoothConnectionActivity.MESSAGE_TOAST);
		// Bundle bundle = new Bundle();
		// bundle.putString(BluetoothConnectionActivity.TOAST,
		// "Unable to connect device");
		// msg.setData(bundle);
		// mHandler.sendMessage(msg);

		if (!mDestoryed) {
			BluetoothConnectionService.this.start();
		}
	}

	private void connectionLost() {
		// Send a failure message back to the Activity
		// Message msg = mHandler
		// .obtainMessage(BluetoothConnectionActivity.MESSAGE_TOAST);
		// Bundle bundle = new Bundle();
		// bundle.putString(BluetoothConnectionActivity.TOAST,
		// "Device connection was lost");
		// msg.setData(bundle);
		// mHandler.sendMessage(msg);

		if (!mDestoryed) {
			// Start the service over to restart listening mode
			BluetoothConnectionService.this.start();
		}
	}

	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		public ConnectThread(BluetoothDevice device) {
			mmDevice = device;
			BluetoothSocket tmp = null;

			try {

				tmp = device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);

			} catch (IOException e) {
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
			} catch (IOException e) {
				try {
					mmSocket.close();
				} catch (IOException e2) {
					Log.e(TAG,
							"unable to close() socket during connection failure",
							e2);
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
			} catch (IOException e) {
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
			} catch (IOException e) {
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

					String readedValue = new String(buffer, 0, 1);

					// Log.d(TAG, readedValue);

					mValueChack.valueChack(readedValue);

					// Send the obtained bytes to the UI Activity
					// mHandler.obtainMessage(
					// BluetoothConnectionActivity.MESSAGE_READ, bytes,
					// -1, buffer).sendToTarget();
				} catch (IOException e) {
					Log.e(TAG, "disconnected", e);
					connectionLost();
					break;
				}
			}
		}

		public void write(byte[] buffer) {
			try {

				mmOutStream.write(buffer);

				// Share the sent message back to the UI Activity
				// mHandler.obtainMessage(
				// BluetoothConnectionActivity.MESSAGE_WRITE, -1, -1,
				// buffer).sendToTarget();
			} catch (IOException e) {
				Log.e(TAG, "Exception during write", e);
			}
		}

		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "close() of connect socket failed", e);
			}
		}

	}

}
