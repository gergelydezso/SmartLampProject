package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.BluetoothConnection;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class ServoMotor {

	private int angleAuxiliary;

	public enum ServoPin {
		SERVO1, SERVO2, SERVO3, SERVO4, SERVO5

	}

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

		// System.out.println("ServoID: " + id + " Degree: " + degree);

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
