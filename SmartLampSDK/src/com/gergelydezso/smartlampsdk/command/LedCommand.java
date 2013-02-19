package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.RGBLed.LedPin;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class LedCommand implements Command {

	private SmartLamp thelamp;
	private LedPin pin;
	private int value;

	public LedCommand(SmartLamp lamp, LedPin pin, int value) {
		this.thelamp = lamp;
		this.pin = pin;
		this.value = value;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		thelamp.led.setLedValue(pin, value);
	}

}
