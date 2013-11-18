package com.gergelydezso.smartlampsdk.command.filter;

import java.util.HashMap;

import android.util.Log;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.ServoCommand;

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

    if (commandToBeFiltered instanceof ServoCommand) {
      ServoCommand servoSet = (ServoCommand) commandToBeFiltered;

      ServoMotorEntity servoID = servoSet.getServoID();
      long previousTime = mMap.get(servoID);
      long actualTime = servoSet.getTimeStamp();
      long diffTime = actualTime - previousTime;

      // TODO - CODE REVIEW - andrei|Nov 18, 2013 - extract time units into constants
      if (diffTime > 1000) {
        mMap.put(servoID, actualTime);
        return true;
      }

    }
    else if ((commandToBeFiltered.getTimeStamp() - mPreviousTime) > 200) {
      mPreviousTime = commandToBeFiltered.getTimeStamp();
      Log.d(TAG, "Led Time filter ok");
      return true;
    }

    return false;
  }
}
