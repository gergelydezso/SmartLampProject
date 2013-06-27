package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import android.content.Context;
import android.util.Log;

public class DeviceNotifications {

  private static DeviceNotifications INSTANCE;

  private final NotificationSettings notificationSettings;

  public DeviceNotifications(Context context) {
    notificationSettings = new NotificationSettings(context);
    AccesibilityNotificationListenerService.registerListener(notificationListener);
    SMSBroadcastReceiver.registerListener(notificationListener);
    CallReceiver.registerListener(notificationListener);
  }

  public static final void init(Context context) {
    if (INSTANCE == null) {
      INSTANCE = new DeviceNotifications(context);
    }
  }

  public static final void destroy() {
    AccesibilityNotificationListenerService.removeListener(INSTANCE.notificationListener);
    SMSBroadcastReceiver.removeListener(INSTANCE.notificationListener);
    CallReceiver.removeListener(INSTANCE.notificationListener);
    INSTANCE = null;
  }

  public NotificationSettings getNotificationSettings() {
    return notificationSettings;
  }

  public static DeviceNotifications getInstance() {
    if (INSTANCE == null) {
      throw new IllegalStateException("You must call init(context) first!");
    }
    return INSTANCE;
  }

  private NotificationListener notificationListener = new NotificationListener() {

    @Override
    public void onNewNotification(Notification notification) {
      if (notificationSettings.isNotificationEnabled(notification)) {
        // TODO - send color to lamp's led.
        Log.d(DeviceNotifications.class.getSimpleName(), "New Notification: " + notification.name());
      }
      else {
        // notification has been ignored
      }
    }
  };

}
