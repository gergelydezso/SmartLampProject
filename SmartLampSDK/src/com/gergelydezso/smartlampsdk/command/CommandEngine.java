package com.gergelydezso.smartlampsdk.command;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.util.Log;

public class CommandEngine {

  private static final String TAG = "CommanEngine";
  private BlockingQueue<Command> sharedQueue = new LinkedBlockingQueue<Command>();

  public CommandEngine() {

    initConsumerThread();
  }

  public void initConsumerThread() {

    Thread mConsumerThread = new Thread(new CommandConsumer(sharedQueue, new ConsumerCallback() {

      @Override
      public void onConsumerThreadError() {
        CommandEngine.this.initConsumerThread();
      }
    }));
    mConsumerThread.start();
  }

  public void executeCommand(Command cmd) {

    try {
      Log.v(TAG, "Command added");
      sharedQueue.put(cmd);
    }
    catch (Exception e) {
      Log.e(TAG, e.getMessage());
    }

  }

}
