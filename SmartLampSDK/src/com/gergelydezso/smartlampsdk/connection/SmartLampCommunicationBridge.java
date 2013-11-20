package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectedListener;

/**
 * Interface for specific communications.
 */
public interface SmartLampCommunicationBridge {

  public void connect(ConnectedListener listener);
  public void disconnect();

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
   * @param blue - blue value of an RGB color.
   * @param callback - retrieve the result of command (onSuccess/onError).
   * 
   */
  public void sendSetLedCommand(int red, int green, int blue, CommandCallback callback);

  public void sendSetAllCommand(String command, CommandCallback callback);

  /**
   * State request command.
   * @param callback - retrieve the result of command.
   */
  // TODO - CODE REVIEW - andrei|Nov 18, 2013 - Why not call it sendRequestStateCommand?
  public void sendRequestCommand(CommandCallback callback);

}
