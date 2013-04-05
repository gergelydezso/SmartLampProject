package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;

import android.util.Log;

public class CommandConsumer implements Runnable {

	public boolean mIsEmpty = false;

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
				mIsEmpty = sharedQueue.isEmpty();
				cmd.execute();

				if (mIsEmpty) {
					Log.v("mIsEmpty", "true");

				}

				Thread.sleep(500);

			} catch (Exception e) {
				System.out.println("CommandConsumer error");

			}

		}
	}

}
