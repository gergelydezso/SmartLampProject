package com.gergelydezso.smartlampsdk.api;

import com.gergelydezso.smartlampsdk.ServoMotorEntities;
import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.CommandEngine;
import com.gergelydezso.smartlampsdk.command.LedSetCommand;
import com.gergelydezso.smartlampsdk.command.LedStateCommand;
import com.gergelydezso.smartlampsdk.command.ServoSetCommand;
import com.gergelydezso.smartlampsdk.command.ServoStateCommand;

/**
 * This is the public API
 * <p>
 * <ul>
 * <li>Set SmartLamp - LedRGB color with {@link SmartLampAPI#setLedValue(LedPinTypes, int, CommandCallback)}. Set
 * <li>Set SmartLamp - ServoMotor position with
 * {@link SmartLampAPI#setServoPosition(ServoMotorEntities, int, CommandCallback)}.
 * <li>Get SmartLamp - LedRGB state with {@link SmartLampAPI#getLedState(CommandCallback)}.
 * <li>Get SmartLamp - ServoMotor state with {@link SmartLampAPI#getServoState(CommandCallback)}.
 */

public class SmartLampAPI {

  private SmartLamp mLamp = new SmartLamp();
  private CommandEngine mEngine = new CommandEngine();

  /**
   * Set servo motor position.
   * 
   * @param servoPin - ServoMotor identifier.
   * @param value - angle of the ServoMotor.
   * @param callback - onSuccess()/onError()
   * 
   */
  public void setServoPosition(ServoMotorEntities servoPin, int value, CommandCallback callback) {

    Command servoSet = new ServoSetCommand(mLamp, servoPin, value, callback);
    mEngine.executeCommand(servoSet);

  }

  /**
   * Set the LED value.
   * 
   * @param red - LedRGB red pin value
   * @param green - LedRGB green pin value
   * @param blue - LedRGB blue pin value
   * @param callback - onSuccess()/onError()
   */
  public void setLedValue(int red, int green, int blue, CommandCallback callback) {

    Command ledSet = new LedSetCommand(mLamp, red, green, blue, callback);
    mEngine.executeCommand(ledSet);

  }

  /**
   * Get LedRGB state.
   * 
   * @param callback - onResult()
   */
  public void getLedState(CommandCallback callback) {

    Command ledState = new LedStateCommand(mLamp, callback);

    mEngine.executeCommand(ledState);
  }

  /**
   * Get ServoMotor state.
   * 
   * @param callback - onResult()
   */
  public void getServoState(CommandCallback callback) {

    Command servoState = new ServoStateCommand(mLamp, callback);
    mEngine.executeCommand(servoState);

  }
}
