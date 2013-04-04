package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.BluetoothConnection;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class ServoMotor {

	private int angleAuxiliary;

	/**
	 * Set servo motor position.
	 * 
	 * @param id
	 *            - servo motor identifier.
	 * @param degree
	 *            - angle of the servo motor.
	 */
	public void setServoPosition(ServoPin id, int degree,
			CommandCallback callback) {

		CommunicationBridge comm = new BluetoothConnection();
		comm.sendData(id.name(), degree, callback);

		Log.v("ServoMotor", "ServoID: " + id + " Degree: " + degree);

	}

	/**
	 * Get servo motor position.
	 * 
	 * @param id
	 *            - servo motor identifier.
	 */
	public int getServoPosition(ServoPin id) {
		return angleAuxiliary;
	}

}
