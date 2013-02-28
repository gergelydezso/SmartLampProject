package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.connection.Bluetooth;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class ServoMotor {

	private int angleAuxiliary;

	public enum ServoID {
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
	public void setServoPosition(ServoID id, int degree) {

		// CommunicationBridge comm = new Bluetooth();
		// comm.sendData();
		
		System.out.println("Set SERVO value");

	}

	/**
	 * Get servo motor position.
	 * 
	 * @param id
	 *            - servo motor identifier.
	 */
	public int getServoPosition(ServoID id) {
		return angleAuxiliary;
	}

}
