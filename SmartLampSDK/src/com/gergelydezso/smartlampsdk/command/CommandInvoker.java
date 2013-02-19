package com.gergelydezso.smartlampsdk.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

	private List<Command> history = new ArrayList<Command>();

	public CommandInvoker() {
	}

	public void storeAndExecute(Command cmd) {
		this.history.add(cmd); // optional
		cmd.execute();
	}

}
