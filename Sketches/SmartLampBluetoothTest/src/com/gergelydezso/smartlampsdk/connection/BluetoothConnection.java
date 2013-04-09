package com.gergelydezso.smartlampsdk.connection;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionHolder;

public class BluetoothConnection implements CommunicationBridge {

	private int mValue;
	private CommandCallback mCallback;
	private BluetoothConnectionService con;
	private BluetoothConnectionHolder conHolder = new BluetoothConnectionHolder();

	public BluetoothConnection() {

		con = conHolder.getConnection();

	}

	@Override
	public void sendData(String id, int value, CommandCallback callback) {

		this.mValue = value;
		this.mCallback = callback;

		con.write(("" + value).getBytes(), new ValueChackCallback() {

			@Override
			public void valueChack(String readedValue) {

				int comparableValue = Integer.parseInt(readedValue);

				Log.d("BluetoothConnection", "Chacked value: "
						+ comparableValue);

				if (comparableValue == mValue) {

					mCallback.onSuccess();
				} else {
					mCallback.onError();
				}

			}
		});

	}

}
