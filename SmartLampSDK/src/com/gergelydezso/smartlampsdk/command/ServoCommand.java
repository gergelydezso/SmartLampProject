package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotor.ServoID;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class ServoCommand implements Command {

	private SmartLamp theLamp;
	private ServoID id;
	private int degree;

	public ServoCommand(SmartLamp lamp, ServoID id, int degree) {
		this.theLamp = lamp;
		this.id = id;
		this.degree = degree;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		theLamp.servo[1].setServoPosition(id, degree);
	}

}
