package com.gergelydezso.smartlampsdk.command;

/**
 * Callback for CommandConsumer.
 */
public interface ConsumerCallback {

  /**
   * Called if an error has occurred.
   */
  public void onConsumerThreadError();

}
