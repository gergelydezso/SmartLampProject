package com.gergelydezso.smartlampsdk.connection.bluetooth;

public class ConnectionHolder {

	public static BluetoothConnectionService con;

	public void setConnection(BluetoothConnectionService con) {
		this.con = con;
	}

	public BluetoothConnectionService getConnection() {
		return this.con;
	}

}
