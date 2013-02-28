package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.RGBLed.LedPin;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class LedCommand implements Command {

	private SmartLamp theLamp;
	private LedPin pin;
	private int value;

	public LedCommand(SmartLamp lamp, LedPin pin, int value) {
		this.theLamp = lamp;
		this.pin = pin;
		this.value = value;
	}

	@Override
	public void execute() {

		theLamp.led.setLedValue(pin, value);
	}

}
