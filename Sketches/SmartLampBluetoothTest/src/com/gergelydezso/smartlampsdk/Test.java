package com.gergelydezso.smartlampsdk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.gergelydezso.smartlampsdk.RGBLed.LedPin;
import com.gergelydezso.smartlampsdk.ServoMotor.ServoPin;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.CommandEngine;
import com.gergelydezso.smartlampsdk.command.LedCommand;
import com.gergelydezso.smartlampsdk.command.ServoCommand;

public class Test {

	boolean success = false;

	public static void main(String[] args) {

		SmartLamp lamp = new SmartLamp();
		CommandEngine engine = new CommandEngine();

		final CountDownLatch cdl = new CountDownLatch(1);

		Command servo = new ServoCommand(lamp, ServoPin.SERVO1, 120,
				new CommandCallback() {

					@Override
					public void onSuccess() {

						cdl.countDown();
						System.out.println("onSuccess");

					}

					@Override
					public void onError() {

						cdl.countDown();
						System.out.println("onError");
					}
				});

		Command led = new LedCommand(lamp, LedPin.BLUE_PIN, 100,
				new CommandCallback() {

					@Override
					public void onSuccess() {
						System.out.println("onSuccess");
					}

					@Override
					public void onError() {
						System.out.println("onError");
					}
				});
		// Command led2 = new LedCommand(lamp, LedPin.GREEN_PIN, 200);
		// Command led3 = new LedCommand(lamp, LedPin.RED_PIN, 150);

		engine.executeCommand(servo);
		engine.executeCommand(led);
		// engine.executeCommand(led2);
		// engine.executeCommand(led3);

		try {
			cdl.await(5000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		System.out.println("Finished");

	}

}
