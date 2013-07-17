package com.gergelydezso.smartlampsdk.command.filter;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandEngine;

public class CommandTarget {

  private CommandEngine mEngine = new CommandEngine();
  private static final String TAG = "CommandTarget";

  public void execute(Command request) {
    mEngine.executeCommand(request);
    Log.d(TAG, "Executing request: " + request);
  }

}
