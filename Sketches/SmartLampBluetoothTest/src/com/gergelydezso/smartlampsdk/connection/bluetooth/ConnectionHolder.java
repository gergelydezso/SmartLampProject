package com.gergelydezso.smartlampsdk.connection.bluetooth;

public class ConnectionHolder {

	public static BluetoothConnectionService con;

	public void setConnection(BluetoothConnectionService con) {
		ConnectionHolder.con = con;
	}

	public BluetoothConnectionService getConnection() {
		return ConnectionHolder.con;
	}

}
