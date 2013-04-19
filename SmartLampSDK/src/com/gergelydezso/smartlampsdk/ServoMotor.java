package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.BluetoothConnection;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class ServoMotor {

	private int angleAuxiliary;

	public void setServoPosition(ServoPin id, int degree,
			CommandCallback callback) {

		CommunicationBridge comm = new BluetoothConnection();
		comm.sendData(id.name(), degree, callback);

		Log.v("ServoMotor", "ServoID: " + id + " Degree: " + degree);

	}

	// TODO - CODE_REVIEW - andrei.hegedus|Apr 19, 2013 - this value is never set. where should it be used?
	public int getServoPosition(ServoPin id) {
		return angleAuxiliary;
	}

}
