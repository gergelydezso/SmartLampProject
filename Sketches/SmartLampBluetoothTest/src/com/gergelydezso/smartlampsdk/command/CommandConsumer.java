package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;

public class CommandConsumer implements Runnable {

	private final BlockingQueue<Command> sharedQueue;

	public CommandConsumer(BlockingQueue<Command> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {

		System.out.println("CommandConsumer runing");

		while (true) {

			try {

				Command cmd = sharedQueue.take();
				cmd.execute();
				Thread.sleep(500);

			} catch (Exception e) {
				System.out.println("CommandConsumer error");

			}

		}
	}

}
