package com.gergelydezso.smartlampsdk;

import android.util.Log;

import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.SmartLampCommunicationBridge;

public class ServoMotor {

  private SmartLampCommunicationBridge mBridge;
  private static final String mServoId = "servo";

  public ServoMotor(SmartLampCommunicationBridge bridge) {
    mBridge = bridge;
  }

  public void setServoPosition(ServoMotorEntity id, int degree, CommandCallback callback) {
    // TODO - CODE REVIEW - andrei|Oct 29, 2013 - Proposal: mBridge.executeCommand(command). Internally
    // command.translate() is called which prvides a byte[] of bytes that can be sent to the Arduino module.
    mBridge.sendSetServoCommand(id, degree, callback);
    Log.v("ServoMotor", "ServoID: " + id + " Degree: " + degree);
  }

  public void getServoState(CommandCallback callback) {
    mBridge.sendRequestCommand(mServoId, callback);

  }

}
