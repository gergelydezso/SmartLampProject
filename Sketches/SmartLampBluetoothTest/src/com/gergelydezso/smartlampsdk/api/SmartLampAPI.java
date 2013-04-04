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

	public void setServoPosition(ServoPin servoPin, int value,
			CommandCallback callback) {

		Command servo = new ServoCommand(lamp, servoPin, value, callback);
		engine.executeCommand(servo);

	}

	public void setLedValue(LedPin ledPin, int value, CommandCallback callback) {

		Command led = new LedCommand(lamp, ledPin, value, callback);
		engine.executeCommand(led);

	}

}
