package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SMSBroadcastReceiver extends BroadcastReceiver {

  private static final String TAG = SMSBroadcastReceiver.class.getSimpleName();
  private static Set<NotificationListener> notificationListeners = new CopyOnWriteArraySet<NotificationListener>();

  public static void registerListener(NotificationListener notificationListener) {
    Log.d(TAG, "listener registered");
    notificationListeners.add(notificationListener);
  }

  public static void removeListener(NotificationListener notificationListener) {
    Log.d(TAG, "listener removed");
    notificationListeners.remove(notificationListener);
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "Intent recieved: " + intent.getAction());
    Log.d(TAG, "listeners: " + notificationListeners.size());
    for (NotificationListener notificationListener : notificationListeners) {
      notificationListener.onNewNotification(Notification.SMS);
    }
  }

}
