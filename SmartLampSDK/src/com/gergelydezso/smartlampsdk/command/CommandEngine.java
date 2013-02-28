package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CommandEngine {

	BlockingQueue<Command> sharedQueue = new LinkedBlockingQueue<Command>();
	Thread consThread = new Thread(new CommandConsumer(sharedQueue));

	public void putCommant(Command cmd) {

		try {
			sharedQueue.put(cmd);

		} catch (Exception e) {

		}

	}
}
