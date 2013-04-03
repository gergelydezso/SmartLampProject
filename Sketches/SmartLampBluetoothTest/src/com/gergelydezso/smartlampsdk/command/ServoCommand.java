package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotor.ServoPin;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class ServoCommand implements Command {

	private SmartLamp theLamp;
	private ServoPin id;
	private int degree;
	private CommandCallback callback;

	public ServoCommand(SmartLamp lamp, ServoPin id, int degree, CommandCallback callback) {
		this.theLamp = lamp;
		this.id = id;
		this.degree = degree;
		this.callback = callback;

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		theLamp.servo.setServoPosition(id, degree, callback);
	}

}
