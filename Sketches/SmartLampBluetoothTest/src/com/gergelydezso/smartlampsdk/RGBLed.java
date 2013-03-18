package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.BluetoothConnection;
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
	 * @param intensity
	 *            - led pin intensity.
	 */
	public void setLedValue(LedPin pin, int intensity, CommandCallback callback) {

		CommunicationBridge comm = new BluetoothConnection();
		comm.sendData(pin.name(), intensity, callback);

		// System.out.println("LedPin: "+pin+" Value: " +intensity);
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
