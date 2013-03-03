package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

public class BluetoothConnection implements CommunicationBridge {

	@Override
	public void sendData(int value, CommandCallback callback) {

		// System.out.println("Value: " + value);
		// callback.onSuccess();

		int i = (int) (Math.random() * 10);
		if (i < 5) {
			callback.onSuccess();
			System.out.println("Value: " + value);
		} else {
			callback.onError();
		}

	}
}
