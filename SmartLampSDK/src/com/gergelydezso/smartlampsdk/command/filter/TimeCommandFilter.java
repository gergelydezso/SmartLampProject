package com.gergelydezso.smartlampsdk.command.filter;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.ServoSetCommand;

public class TimeCommandFilter implements CommandFilter {

  private static final String TAG = "TimeCommandFilter";
  // private long mTime = System.currentTimeMillis();
  private long mPreviousTime;

  @Override
  public boolean execute(Command commandToBeFiltered) {

    if(commandToBeFiltered instanceof ServoSetCommand){
      ServoSetCommand cmd = (ServoSetCommand)commandToBeFiltered;
      
    }
    
    if ((commandToBeFiltered.getTimeTicket() - mPreviousTime) > 800) {
      mPreviousTime = commandToBeFiltered.getTimeTicket();
      Log.d(TAG, "ok");
      return true;
    }
    else
      return false;
  }
}

// Log.d(TAG, "try");

// if ((System.currentTimeMillis() - mTime) > 1000) {
// Log.d(TAG, "ok");
// mTime = System.currentTimeMillis();
// return true;
// }
// else
// return false;