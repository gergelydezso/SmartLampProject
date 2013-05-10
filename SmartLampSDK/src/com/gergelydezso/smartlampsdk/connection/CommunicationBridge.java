package com.gergelydezso.smartlampsdk.connection;

import com.gergelydezso.smartlampsdk.command.CommandCallback;

/**
 * Interface for specific communications.
 */
public interface CommunicationBridge {

  /**
   * Send a command to the .
   * 
   * @param id - identifier of the servo/led pin.
   * @param value - control value of the servo/led pin.
   * @param callback - retrieve the result of command (onSuccess/onError).
   * 
   */
  public void sendSetCommand(String id, int value, CommandCallback callback);

  public void sendRequestCommand(String id, CommandCallback callback);

}
