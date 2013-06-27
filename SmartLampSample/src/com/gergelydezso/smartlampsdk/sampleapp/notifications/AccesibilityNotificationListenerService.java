package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccesibilityNotificationListenerService extends AccessibilityService {

  private static final String TAG = "NOT";
  private boolean isInit;

  private static final String GOOGLE_MAIL_PACKAGE = "com.google.android.gm";
  private static final String FACEBOOK_PACKAGE = "com.facebook.katana";
  private static final String FACEBOOK_MESSENGER = "com.facebook.orca";

  private static Set<NotificationListener> notificationListeners = new CopyOnWriteArraySet<NotificationListener>();

  @Override
  public void onAccessibilityEvent(AccessibilityEvent event) {
    if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
      final String packagename = String.valueOf(event.getPackageName());
      Log.d(TAG, "onAccessibilityEvent: " + packagename);
      Notification notification = null;
      if (GOOGLE_MAIL_PACKAGE.equals(packagename)) {
        notification = Notification.GMAIL;
      }
      else if (FACEBOOK_MESSENGER.equals(packagename) || FACEBOOK_PACKAGE.equals(packagename)) {
        notification = Notification.FACEBOOK;
      }
      if (notification != null) {
        for (NotificationListener notificationListener : notificationListeners) {
          notificationListener.onNewNotification(notification);
        }
      }
    }
  }

  @Override
  public void onInterrupt() {
    isInit = false;

  }

  @Override
  protected void onServiceConnected() {
    Log.d(TAG, "service connected");
    if (isInit) {
      return;
    }
    AccessibilityServiceInfo info = new AccessibilityServiceInfo();
    info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
    info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
    info.notificationTimeout = 100;
    setServiceInfo(info);
    isInit = true;
  }

  public static void registerListener(NotificationListener notificationListener) {
    notificationListeners.add(notificationListener);
  }

  public static void removeListener(NotificationListener notificationListener) {
    notificationListeners.remove(notificationListener);
  }

}
