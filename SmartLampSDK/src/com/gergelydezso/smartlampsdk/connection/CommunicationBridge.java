package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

/**
 * Interface for specific communications.
 */
public interface CommunicationBridge {

  /**
   * Send a command.
   * 
   * @param id - identifier of the servo entitie.
   * @param value - control value of the servo.
   * @param callback - retrieve the result of command (onSuccess/onError).
   * 
   */
  public void sendSetServoCommand(String id, int value, CommandCallback callback);

  public void sendSetLedCommand(int reg, int green, int blue, CommandCallback callback);

  public void sendRequestCommand(String id, CommandCallback callback);

}
