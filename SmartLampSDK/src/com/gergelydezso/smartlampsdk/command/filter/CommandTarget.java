package com.gergelydezso.smartlampsdk.command.filter;

import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandEngine;

public class CommandTarget {

  private CommandEngine mEngine = new CommandEngine();

  public void execute(Command request) {

    mEngine.executeCommand(request);
    System.out.println("Executing request: " + request);
  }

}
