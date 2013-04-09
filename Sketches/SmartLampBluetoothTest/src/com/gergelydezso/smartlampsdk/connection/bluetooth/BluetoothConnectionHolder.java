package com.gergelydezso.smartlampsdk.connection.bluetooth;

public class BluetoothConnectionHolder {

	public static BluetoothConnectionService con;

	public void setConnection(BluetoothConnectionService con) {
		BluetoothConnectionHolder.con = con;
	}

	public BluetoothConnectionService getConnection() {
		return BluetoothConnectionHolder.con;
	}

}
