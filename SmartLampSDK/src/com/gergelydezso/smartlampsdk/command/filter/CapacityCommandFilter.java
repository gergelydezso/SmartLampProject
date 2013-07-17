package com.gergelydezso.smartlampsdk.command.filter;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.Command;

public class CapacityCommandFilter implements CommandFilter {

  private static final String TAG = "CapacityCommandFilter";
  private long mTime = System.currentTimeMillis();

  @Override
  public boolean execute(Command CommandToBeFiltered) {

    Log.d(TAG, "try");

    if ((System.currentTimeMillis() - mTime) > 1000) {
      Log.d(TAG, "ok");
      mTime = System.currentTimeMillis();
      return true;
    }
    else
      return false;

  }
}
