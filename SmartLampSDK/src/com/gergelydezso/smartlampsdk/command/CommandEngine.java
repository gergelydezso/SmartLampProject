package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CommandEngine {

	private BlockingQueue<Command> sharedQueue;

	public CommandEngine() {
		sharedQueue = new LinkedBlockingQueue<Command>();
		Thread consThread = new Thread(new CommandConsumer(sharedQueue));
		consThread.start();
	}

	public void executeCommand(Command cmd) {

		try {
			System.out.println("Command added");
			sharedQueue.put(cmd);

		} catch (Exception e) {
		}

	}

}
