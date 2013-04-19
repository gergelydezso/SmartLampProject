package com.gergelydezso.smartlampsdk.connection;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionHolder;

public class BluetoothConnection implements CommunicationBridge {

  // TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - please use better element names. mValue, con and conHolder are not very readable.
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

		  // TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - is this supposed to be valueCheck and ValueCheckCallback?
			@Override
			public void valueChack(String readedValue) {
// TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - readValue. Not readedValue. I read a book. I read a book. I have/had read a book.
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
