package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampHolder;

public class DeviceNotifications {

  private static DeviceNotifications INSTANCE;

  private final NotificationSettings notificationSettings;
  private Context context;

  public DeviceNotifications(Context context) {
    this.context = context;
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
        int redValue = Color.red(getNotificationSettings().getNotificationColor(notification));
        int greenValue = Color.green(getNotificationSettings().getNotificationColor(notification));
        int blueValue = Color.blue(getNotificationSettings().getNotificationColor(notification));

        SmartLampHolder.getSmartLamp().adjustLedComponent(redValue, greenValue, blueValue, new CommandCallback() {
          @Override
          public void onSuccess() {

          }

          @Override
          public void onError() {

          }

          @Override
          public void onResult(String state) {

          }
        });

        Thread command = new Thread() {

          @Override
          public void run() {
            super.run();
            try {
              sleep(2000);
            }
            catch (Exception e) {

            }
            SmartLampHolder.getSmartLamp().adjustLedComponent(0, 0, 0, new CommandCallback() {
              @Override
              public void onSuccess() {

              }

              @Override
              public void onError() {

              }

              @Override
              public void onResult(String state) {

              }
            });
          }
        };
        command.start();


        Log.d(DeviceNotifications.class.getSimpleName(), "New Notification: " + notification.name());
      }
      else {
        // notification has been ignored
      }
    }
  };

}
