package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver {

  private static final String TAG = CallReceiver.class.getSimpleName();
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
    String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
    Log.d(TAG, "state: "+state);
    if (state.equals("RINGING")) {
      for (NotificationListener notificationListener : notificationListeners) {
        notificationListener.onNewNotification(Notification.CALL);
      }
    }
  }
}
