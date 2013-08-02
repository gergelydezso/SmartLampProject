package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectedListener;

/**
 * Interface for specific communications.
 */
public interface SmartLampCommunicationBridge {

  public void connect(ConnectedListener listener);

  /**
   * Send servo command.
   * 
   * @param id - identifier of the servo entities.
   * @param value - control value of the servo.
   * @param callback - retrieve the result of command (onSuccess/onError).
   * 
   */
  public void sendSetServoCommand(ServoMotorEntity id, int value, CommandCallback callback);

  /**
   * Send led command.
   * 
   * @param red - red value of an RGB color.
   * @param green - green value of an RGB color.
   * @param bule - blue value of an RGB color.
   * @param callback - retrieve the result of command (onSuccess/onError).
   * 
   */
  public void sendSetLedCommand(int reg, int green, int blue, CommandCallback callback);

  public void sendSetAllCommand(String command, CommandCallback callback);

  /**
   * State request command.
   * 
   * @param id - servo or led identifier.
   * @param callback - retrieve the result of command.
   */
  public void sendRequestCommand(String id, CommandCallback callback);

}
