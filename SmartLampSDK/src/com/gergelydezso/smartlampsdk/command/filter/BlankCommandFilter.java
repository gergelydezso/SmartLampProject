package com.gergelydezso.smartlampsdk.command.filter;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.Command;

public class BlankCommandFilter implements CommandFilter {

  private static final String TAG = "BlankCommandFilter";

  @Override
  public boolean execute(Command commandToBeFiltered) {
    Log.d(TAG, "Just a blank filter");

    return true;
  }

}
