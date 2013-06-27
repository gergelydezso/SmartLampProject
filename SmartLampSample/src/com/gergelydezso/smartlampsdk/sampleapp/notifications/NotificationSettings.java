package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Contains the notifications that are currently supported by the device and the configs related to the notifications.
 * 
 * @author andrei
 * 
 */
public class NotificationSettings {

  private static final String NOTIFICATIONS_PREFERENCES = "NOTIFICATIONS_PREFERENCES";
  private SharedPreferences notificationsPreferences;
  private Map<Notification, SimpleEntry<Boolean, Integer>> notificationsAndColors = new HashMap<Notification, SimpleEntry<Boolean, Integer>>();

  public NotificationSettings(Context context) {
    notificationsPreferences = context.getSharedPreferences(NOTIFICATIONS_PREFERENCES, Context.MODE_PRIVATE);
    for (Notification notification : Notification.values()) {
      int color = notificationsPreferences.getInt(notification.getColorKey(), -2);
      if (color == -2) {
        color = context.getResources().getColor(notification.getDefaultColorId());
        notificationsPreferences.edit().putInt(notification.getColorKey(), color).commit();
      }
      notificationsAndColors.put(notification,
          new SimpleEntry<Boolean, Integer>(notificationsPreferences.getBoolean(notification.getEnabledKey(), false),
              color));
    }
  }

  public synchronized void setNotificationColor(Notification notification, int color) {
    SimpleEntry<Boolean, Integer> entry = notificationsAndColors.get(notification);
    entry.setValue(color);
    notificationsAndColors.put(notification, entry);
    notificationsPreferences.edit().putInt(notification.getColorKey(), color).apply();
  }

  public synchronized void enableNotification(Notification notification, boolean enabled) {
    notificationsPreferences.edit().putBoolean(notification.getEnabledKey(), enabled).commit();
    SimpleEntry<Boolean, Integer> entry = notificationsAndColors.get(notification);
    notificationsAndColors.put(notification, new SimpleEntry<Boolean, Integer>(enabled, entry.getValue()));
  }


  public synchronized int getNotificationColor(Notification notification) {
    SimpleEntry<Boolean, Integer> entry = notificationsAndColors.get(notification);
    return entry.getValue();
  }

  public synchronized boolean isNotificationEnabled(Notification notification) {
    SimpleEntry<Boolean, Integer> entry = notificationsAndColors.get(notification);
    return entry.getKey();
  }

}
