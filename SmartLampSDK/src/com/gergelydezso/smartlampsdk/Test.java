package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.RGBLed.LedPin;
import com.gergelydezso.smartlampsdk.ServoMotor.ServoID;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandEngine;
import com.gergelydezso.smartlampsdk.command.LedCommand;
import com.gergelydezso.smartlampsdk.command.ServoCommand;

public class Test {

	public static void main(String[] args) {

		SmartLamp lamp = new SmartLamp();
		Command servo = new ServoCommand(lamp, ServoID.SERVO1, 120);
		Command led = new LedCommand(lamp, LedPin.BLUE_PIN, 100);

		CommandEngine engine = new CommandEngine();
		engine.putCommant(led);
		engine.putCommant(servo);

	}

}
