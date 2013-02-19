package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.ServoMotor.servoID;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class ServoCommand implements Command {

	private SmartLamp thelamp;
	private servoID id;
	private int degree;
	
	public ServoCommand(SmartLamp lamp, servoID id, int degree){
		this.thelamp = lamp;
		this.id = id;
		this.degree = degree;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		thelamp.servo[1].setServoPosition(id, degree);
	}
	
	

}
