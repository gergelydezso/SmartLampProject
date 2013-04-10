package com.gergelydezso.smartlampsdk.api;

import com.gergelydezso.smartlampsdk.LedPin;
import com.gergelydezso.smartlampsdk.ServoPin;
import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.CommandEngine;
import com.gergelydezso.smartlampsdk.command.LedCommand;
import com.gergelydezso.smartlampsdk.command.ServoCommand;

public class SmartLampAPI {

	private SmartLamp lamp = new SmartLamp();
	private CommandEngine engine = new CommandEngine();

	/**
	 * Set servo motor position.
	 * 
	 * @param servoPin
	 *            - servo motor identifier.
	 * @param value
	 *            - angle of the servo motor.
	 * @param callback
	 * 
	 */
	public void setServoPosition(ServoPin servoPin, int value,
			CommandCallback callback) {

		Command servo = new ServoCommand(lamp, servoPin, value, callback);
		engine.executeCommand(servo);

	}

	/**
	 * Set the LED value.
	 * 
	 * @param ledPin
	 *            - LED pin identifier.
	 * @param value
	 *            - led pin intensity.
	 * @param callback
	 */
	public void setLedValue(LedPin ledPin, int value, CommandCallback callback) {

		Command led = new LedCommand(lamp, ledPin, value, callback);
		engine.executeCommand(led);

	}

}
