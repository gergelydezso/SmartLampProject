package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.util.Log;

public class CommandEngine {

	private BlockingQueue<Command> sharedQueue = new LinkedBlockingQueue<Command>();
	Thread consThread = new Thread(new CommandConsumer(sharedQueue));

	public CommandEngine() {
		consThread.start();
	}

	public void executeCommand(Command cmd) {

		try {
			Log.v("CommandEngine", "Command added");
			sharedQueue.put(cmd);

		} catch (Exception e) {
		}

	}

}
