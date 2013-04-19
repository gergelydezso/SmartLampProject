package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.util.Log;

public class CommandEngine {

	private BlockingQueue<Command> sharedQueue = new LinkedBlockingQueue<Command>();
	// TODO - CODE_REVIEW - andrei.hegedus|Apr 17, 2013 - why does the thread have default visibility? Also the name is very ambiguous. Try naming it with something that describes its role.
	Thread consThread = new Thread(new CommandConsumer(sharedQueue));

	public CommandEngine() {
		consThread.start();
	}

	public void executeCommand(Command cmd) {

		try {
			Log.v("CommandEngine", "Command added");
			sharedQueue.put(cmd);

		} catch (Exception e) {
		  // TODO - CODE_REVIEW - andrei.hegedus|Apr 17, 2013 - if some exception occurs here, you will not be able to identify it, unless you do debugging. Don't let exceptions slip!
		}

	}

}
