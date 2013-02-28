package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.connection.Bluetooth;
import com.gergelydezso.smartlampsdk.connection.CommunicationBridge;

public class RGBLed {

	private int valueAuxiliary;

	public enum LedPin {
		RED_PIN, GREEN_PIN, BLUE_PIN
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

		// CommunicationBridge comm = new Bluetooth();
		// comm.sendData();
		
		System.out.println("Set LED value");
	}

	/**
	 * Get the LED value.
	 * 
	 * @param pin
	 *            - LED pin identifier.
	 */
	public int getLedValue(LedPin pin) {
		return valueAuxiliary;
	}

}
