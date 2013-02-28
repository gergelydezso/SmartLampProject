package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;

public class CommandConsumer implements Runnable {

	private final BlockingQueue<Command> sharedQueue;

	public CommandConsumer(BlockingQueue<Command> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {

				(sharedQueue.take()).execute();

			} catch (Exception e) {

			}
		}
	}

}
