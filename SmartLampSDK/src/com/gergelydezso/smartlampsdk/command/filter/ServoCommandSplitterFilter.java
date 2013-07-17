package com.gergelydezso.smartlampsdk.command.filter;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.Command;

public class ServoCommandSplitterFilter implements CommandFilter {

  private static final String TAG = "ServoCommandSplitterFilter";

  @Override
  public boolean execute(Command CommandToBeFiltered) {
    Log.d(TAG, "ok");
    return true;
  }

}
