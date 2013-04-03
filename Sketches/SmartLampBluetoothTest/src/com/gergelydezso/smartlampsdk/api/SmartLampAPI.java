package com.gergelydezso.smartlampsdk.api;

import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.RGBLed.LedPin;
import com.gergelydezso.smartlampsdk.ServoMotor.ServoPin;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.CommandEngine;
import com.gergelydezso.smartlampsdk.command.LedCommand;
import com.gergelydezso.smartlampsdk.command.ServoCommand;

public class SmartLampAPI {

	private SmartLamp lamp = new SmartLamp();
	private CommandEngine engine = new CommandEngine();

	public void createAddServoCommand(int servoID, int value, CommandCallback callback) {

		Command servo = new ServoCommand(lamp, ServoPin.SERVO1, value, callback);

		engine.executeCommand(servo);

	}

	// public void createAddLedCommand() {
	//
	// Command led = new LedCommand(lamp, LedPin.BLUE_PIN, 100,
	// new CommandCallback() {
	//
	// @Override
	// public void onSuccess() {
	// System.out.println("onSuccess");
	// }
	//
	// @Override
	// public void onError() {
	// System.out.println("onError");
	// }
	// });
	//
	// engine.executeCommand(led);
	//
	// }

}
