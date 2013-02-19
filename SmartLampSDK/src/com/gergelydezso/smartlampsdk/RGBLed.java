package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.connection.Bluetooth;

public class RGBLed {

	private int valueAuxiliary;
	private Bluetooth bt;

	public enum LedPin {
		redPin, greenPin, bluePin
	}

	/**
	 * Set the LED value.
	 * 
	 * @param pin
	 *            - LED pin identifier.
	 * @param value
	 *            - led pin intensity.
	 */
	public void setLedValue(LedPin pin, int value) {
		bt.sendData();
	}

	/**
	 * Get the LED value.
	 * 
	 * @param pin
	 *            - LED pin identifier.
	 */
	public int getLedValue(LedPin pin) {
		bt.reciveData();
		return valueAuxiliary;
	}

}
