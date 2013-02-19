package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.Bluetooth;

public class ServoMotor {

	private int angleAuxiliary;
	private Bluetooth bt;

	public enum servoID {
		servo1, servo2, seervo3, servo4, servo5
	}

	/**
	 * Set servo motor position.
	 * 
	 * @param id
	 *            - servo motor identifier.
	 * @param degree
	 *            - angle of the servo motor.
	 */
	public void setServoPosition(servoID id, int degree) {
		bt.sendData();
	}

	/**
	 * Get servo motor position.
	 * 
	 * @param id
	 *            - servo motor identifier.
	 */
	public int getServoPosition(servoID id) {
		bt.reciveData();
		return angleAuxiliary;
	}

}
