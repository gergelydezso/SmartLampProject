package com.gergelydezso.smartlampsdk.command;

/**
 * Interface for specific commands.
 */
public interface Command {

  /**
   * Execute the command.
   */

  public void execute();

  public void setTimeStamp(long time);

  public long getTimeStamp();
  
}
