package com.gergelydezso.smartlampsdk.command;

/**
 * Callback for commands.
 */
public interface CommandCallback {

  /**
   * Called when the command has been executed correctly.
   */
  public void onSuccess();

  /**
   * Called if an error has occurred.
   */
  public void onError();

  /**
   * Called when the state command has been executed correctly.
   * 
   * @param state - state of the SmartLamp.
   */
  public void onResult(String state);
}