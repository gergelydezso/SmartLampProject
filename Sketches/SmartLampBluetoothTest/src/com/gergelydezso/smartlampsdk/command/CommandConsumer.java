package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;

import android.util.Log;

public class CommandConsumer implements Runnable {

	private final BlockingQueue<Command> sharedQueue;
	public boolean mIsEmpty;

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

			mIsEmpty = sharedQueue.isEmpty();
			if (mIsEmpty) {
				Log.v("mIsEmpty", "true");
			}

		}
	}

}
