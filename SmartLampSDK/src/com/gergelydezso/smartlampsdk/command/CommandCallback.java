package com.gergelydezso.smartlampsdk.command;

/**
 * Callback for commands.
 */
public abstract class CommandCallback {

  /**
   * Called when the command has been executed correctly.
   */
  public abstract void onSuccess();

  /**
   * Called if an error has occurred.
   */
  public abstract void onError();

  /**
   * Called when the state command has been executed correctly.
   * 
   * @param state - state of the SmartLamp.
   */
  public void onResult(String state) {

  }

}
