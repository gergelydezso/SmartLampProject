package com.gergelydezso.smartlampsdk.command.filter;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.Command;

public class CapacityCommandFilter implements CommandFilter {

  private static final String TAG = "CapacityCommandFilter";

  @Override
  public void execute(Command CommandToBeFiltered) {
    Log.d(TAG, "filtered");
  }
}
