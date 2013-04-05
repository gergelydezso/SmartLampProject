package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.BluetoothConnection;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class LedRGB {

	private int valueAuxiliary;

	public void setLedValue(LedPin pin, int intensity, CommandCallback callback) {

		CommunicationBridge comm = new BluetoothConnection();
		comm.sendData(pin.name(), intensity, callback);

		Log.v("LedRGB", "LedPin: " + pin + " Value: " + intensity);
	}

	public int getLedValue(LedPin pin) {
		return valueAuxiliary;
	}

}
