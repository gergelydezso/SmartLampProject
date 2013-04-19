package com.gergelydezso.smartlampsdk.connection.bluetooth;

public class BluetoothConnectionHolder {

  // TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - this should be private. You already have getters and setters.
	public static BluetoothConnectionService con;

	public void setConnection(BluetoothConnectionService con) {
		BluetoothConnectionHolder.con = con;
	}

	public BluetoothConnectionService getConnection() {
		return BluetoothConnectionHolder.con;
	}

}
