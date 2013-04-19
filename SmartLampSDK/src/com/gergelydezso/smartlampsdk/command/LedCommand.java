package com.gergelydezso.smartlampsdk.command;

import com.gergelydezso.smartlampsdk.LedPin;
import com.gergelydezso.smartlampsdk.SmartLamp;

public class LedCommand implements Command {

	private SmartLamp theLamp;
	private LedPin pin;
	private int intensity;
	private CommandCallback callback;

	public LedCommand(SmartLamp lamp, LedPin pin, int intensity,
			CommandCallback callback) {
		this.theLamp = lamp;
		this.pin = pin;
		this.intensity = intensity;
		this.callback = callback;

	}

	@Override
	public void execute() {
// TODO - CODE_REVIEW - andrei.hegedus|Apr 17, 2013 - this is not java-like coding. Please use methods unless you are restricted by some design decision.
		theLamp.led.setLedValue(pin, intensity, callback);
	}

}
