package com.gergelydezso.smartlampsdk.command.filter;

import java.util.HashMap;

import android.util.Log;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.ServoSetCommand;

public class TimeCommandFilter implements CommandFilter {

  private static final String TAG = "TimeCommandFilter";
  private HashMap<ServoMotorEntity, Long> mMap;
  private long mPreviousTime;

  public TimeCommandFilter() {
    mMap = new HashMap<ServoMotorEntity, Long>();
    for (ServoMotorEntity servoID : ServoMotorEntity.values()) {
      mMap.put(servoID, 0l);
    }
  }

  @Override
  public boolean execute(Command commandToBeFiltered) {

    if (commandToBeFiltered instanceof ServoSetCommand) {
      ServoSetCommand servoSet = (ServoSetCommand) commandToBeFiltered;

      ServoMotorEntity servoID = servoSet.getServoID();
      long previousTime = mMap.get(servoID);
      long actualTime = servoSet.getTimeTicket();
      long diffTime = actualTime - previousTime;

      if (diffTime > 1000) {
        mMap.put(servoID, actualTime);
        return true;
      }

    }
    else if ((commandToBeFiltered.getTimeTicket() - mPreviousTime) > 500) {
      mPreviousTime = commandToBeFiltered.getTimeTicket();
      Log.d(TAG, "Led Time filter ok");
      return true;
    }

    return false;
  }
}
