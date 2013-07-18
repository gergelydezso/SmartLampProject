package com.gergelydezso.smartlampsdk.command.filter;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.Command;

public class SplitterCommandFilter implements CommandFilter {

  private static final String TAG = "SplitterCommandFilter";

  @Override
  public boolean execute(Command commandToBeFiltered) {
    Log.d(TAG, "ok");

    return true;
  }

}
