package com.gergelydezso.smartlampsdk.command.filter;

import com.gergelydezso.smartlampsdk.ServoMotorEntities;
import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.CommandEngine;
import com.gergelydezso.smartlampsdk.command.ServoSetCommand;

public class CommandTarget {

  private SmartLamp mLamp = new SmartLamp();
  private CommandEngine mEngine = new CommandEngine();

  public void execute(String request) {

    Command servoSet = new ServoSetCommand(mLamp, ServoMotorEntities.SERVO1, 100, new CommandCallback() {

      @Override
      public void onSuccess() {
      }

      @Override
      public void onResult(String state) {
      }

      @Override
      public void onError() {
      }
    });
    mEngine.executeCommand(servoSet);
    System.out.println("Executing request: " + request);
  }

}
