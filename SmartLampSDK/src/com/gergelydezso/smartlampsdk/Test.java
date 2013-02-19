package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.RGBLed.LedPin;
import com.gergelydezso.smartlampsdk.ServoMotor.servoID;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandInvoker;
import com.gergelydezso.smartlampsdk.command.LedCommand;
import com.gergelydezso.smartlampsdk.command.ServoCommand;

public class Test {

	private static servoID id;
	private static int degree;
	private static LedPin pin;
	private static int value;

	public static void main(String[] args) {

		SmartLamp lamp = new SmartLamp();
		Command servo = new ServoCommand(lamp, id, degree);
		Command led = new LedCommand(lamp, pin, value);

		CommandInvoker cmd = new CommandInvoker();

		cmd.storeAndExecute(servo);

	}

}
