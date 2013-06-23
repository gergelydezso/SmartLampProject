package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

public class ServoMotor {

  // TODO- change name to ..bridge
  private SmartLampCommunicationBridge mPipe;
  private String mServoId = "servo";
  // TODO - change to static final

  public ServoMotor(SmartLampCommunicationBridge pipe) {
    mPipe = pipe;
  }

  public void setServoPosition(ServoMotorEntities id, int degree, CommandCallback callback) {
    mPipe.sendSetServoCommand(id.name(), degree, callback);
    Log.v("ServoMotor", "ServoID: " + id + " Degree: " + degree);
  }

  public void getServoState(CommandCallback callback) {
    mPipe.sendRequestCommand(mServoId, callback);

  }

}
