package com.gergelydezso.smartlampsdk.connection;

import android.os.Message;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectionHolder;

public class BluetoothConnection implements CommunicationBridge {

	private BluetoothConnectionService con;
	private ConnectionHolder conHolder = new ConnectionHolder();

	public BluetoothConnection() {

		con = conHolder.getConnection();
	}

	@Override
	public void sendData(String id, int value, CommandCallback callback) {

		con.write(("" + value).getBytes());
		callback.onSuccess();

	}
}
