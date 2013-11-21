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
  private static final int SERVO_TIME_FILTER = 400;
  private static final int LED_TIME_FILTER = 150;

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

      if (diffTime > SERVO_TIME_FILTER) {
        mMap.put(servoID, actualTime);
        return true;
      }
    }
    else if ((commandToBeFiltered.getTimeStamp() - mPreviousTime) > LED_TIME_FILTER) {
      mPreviousTime = commandToBeFiltered.getTimeStamp();
      Log.d(TAG, "Led Time filter ok");
      return true;
    }

    return false;
  }
}
