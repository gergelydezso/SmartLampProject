package com.gergelydezso.smartlampsdk.api;

import com.gergelydezso.smartlampsdk.ServoMotorEntities;
import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.SmartLampGestures;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.LedSetCommand;
import com.gergelydezso.smartlampsdk.command.LedStateCommand;
import com.gergelydezso.smartlampsdk.command.ServoSetCommand;
import com.gergelydezso.smartlampsdk.command.ServoStateCommand;
import com.gergelydezso.smartlampsdk.command.filter.TimeCommandFilter;
import com.gergelydezso.smartlampsdk.command.filter.CommandFilterManager;
import com.gergelydezso.smartlampsdk.command.filter.CommandTarget;
import com.gergelydezso.smartlampsdk.command.filter.BlankCommandFilter;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothCommunicationBridge;

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

  @SuppressWarnings("unused")
  private static final String TAG = "SmartLampAPI";
  // private SmartLamp mLamp = new SmartLamp();
  private SmartLamp mLamp = null;
  private CommandFilterManager filterManager = new CommandFilterManager(new CommandTarget());

  // - last code review modifications
  // public SmartLampAPI(){
  // //TODO check configuration received in params
  // mLamp = new SmartLamp(new BluetoothCommunicationBridge());
  // }

  public SmartLampAPI(BluetoothCommunicationBridge bridge) {
    mLamp = new SmartLamp(bridge);
    filterManager.setFilter(new TimeCommandFilter());
    filterManager.setFilter(new BlankCommandFilter());
  }

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
    servoSet.setTimeTicket(System.currentTimeMillis());
    filterManager.sendCommand(servoSet);
  }

  public void setLampGesture(SmartLampGestures gesture) {

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
    ledSet.setTimeTicket(System.currentTimeMillis());
    filterManager.sendCommand(ledSet);

  }

  /**
   * Get LedRGB state.
   * 
   * @param callback - onResult()
   */
  public void getLedState(CommandCallback callback) {
    Command ledState = new LedStateCommand(mLamp, callback);
    ledState.setTimeTicket(System.currentTimeMillis());
    filterManager.sendCommand(ledState);
  }

  /**
   * Get ServoMotor state.
   * 
   * @param callback - onResult()
   */
  public void getServoState(CommandCallback callback) {
    Command servoState = new ServoStateCommand(mLamp, callback);
    servoState.setTimeTicket(System.currentTimeMillis());
    filterManager.sendCommand(servoState);

  }
}
