package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;

public class CommandConsumer implements Runnable {

	private final BlockingQueue<Command> sharedQueue;

	public CommandConsumer(BlockingQueue<Command> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
// TODO - CODE_REVIEW - andrei.hegedus|Apr 17, 2013 - use Log from Android instead of writing to the console.
		System.out.println("CommandConsumer runing");

		while (true) {

			try {

				Command cmd = sharedQueue.take();
				cmd.execute();
				Thread.sleep(500);

			} catch (Exception e) {
			  // TODO - CODE_REVIEW - andrei.hegedus|Apr 17, 2013 - shouldn't the consumer wake up again here? If not please document.
				System.out.println("CommandConsumer error");

			}

		}
	}

}
