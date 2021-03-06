package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;

import android.util.Log;

public class CommandConsumer implements Runnable {

  private static final String TAG = "CommanConsumer";
  private final BlockingQueue<Command> mSharedQueue;
  @SuppressWarnings("unused")
  private ConsumerCallback mCallback;

  public CommandConsumer(BlockingQueue<Command> sharedQueue, ConsumerCallback callback) {
    this.mSharedQueue = sharedQueue;
    this.mCallback = callback;
  }

  @Override
  public void run() {
    Log.v(TAG, "CommandConsumer runing");

    while (true) {

      try {

        Command cmd = mSharedQueue.take();
        cmd.execute();
        // Thread.sleep(100);

      }
      catch (Exception e) {
        // Log.e(TAG, "CommandConsumer error", e);
        // mCallback.onConsumerThreadError();
      }

    }
  }

}
