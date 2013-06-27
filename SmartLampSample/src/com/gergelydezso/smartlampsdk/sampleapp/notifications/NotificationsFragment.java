package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.notifications.NotificationItem.NotificationItemCallback;
import com.larswerkman.colorpicker.ColorPicker;
import com.larswerkman.colorpicker.ColorPicker.OnColorChangedListener;

public class NotificationsFragment extends Fragment {

  private NotificationItem gmailItem;
  private NotificationItem facebookItem;
  private NotificationItem smsItem;
  private NotificationItem phoneCallItem;

  private int getNotificationColor(Notification notification) {
    return DeviceNotifications.getInstance().getNotificationSettings().getNotificationColor(notification);
  }

  private boolean isNotificationEnabled(Notification notification) {
    return DeviceNotifications.getInstance().getNotificationSettings().isNotificationEnabled(notification);
  }
  
  private void setNotificationColor(Notification notification, int color){
    DeviceNotifications.getInstance().getNotificationSettings().setNotificationColor(notification, color);
  }
  
  private void enableNotification(Notification notification, boolean enabled){
    DeviceNotifications.getInstance().getNotificationSettings().enableNotification(notification, enabled);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
    LinearLayout notificationItemsLayout = (LinearLayout) rootView.findViewById(R.id.notifications_layout);
    gmailItem = new NotificationItem(inflater.inflate(R.layout.notification_item, null));
    gmailItem.setNotificationImageId(R.drawable.gmail_logo);
    gmailItem.setNotificationItemText("Mail Notifications");
    gmailItem.setColor(getNotificationColor(Notification.GMAIL));
    gmailItem.setEnabled(isNotificationEnabled(Notification.GMAIL));
    gmailItem.setTag(Notification.GMAIL);
    notificationItemsLayout.addView(gmailItem.getRoot());

    facebookItem = new NotificationItem(inflater.inflate(R.layout.notification_item, null));
    facebookItem.setNotificationImageId(R.drawable.facebook_logo);
    facebookItem.setNotificationItemText("Facebook Notifications");
    facebookItem.setColor(getNotificationColor(Notification.FACEBOOK));
    facebookItem.setEnabled(isNotificationEnabled(Notification.FACEBOOK));
    facebookItem.setTag(Notification.FACEBOOK);
    notificationItemsLayout.addView(facebookItem.getRoot());

    smsItem = new NotificationItem(inflater.inflate(R.layout.notification_item, null));
    smsItem.setNotificationImageId(R.drawable.sms_logo);
    smsItem.setNotificationItemText("SMS Notifications");
    smsItem.setColor(getNotificationColor(Notification.SMS));
    smsItem.setEnabled(isNotificationEnabled(Notification.SMS));
    smsItem.setTag(Notification.SMS);
    notificationItemsLayout.addView(smsItem.getRoot());

    phoneCallItem = new NotificationItem(inflater.inflate(R.layout.notification_item, null));
    phoneCallItem.setNotificationImageId(R.drawable.call_logo);
    phoneCallItem.setNotificationItemText("Call Notifications");
    phoneCallItem.setColor(getNotificationColor(Notification.CALL));
    phoneCallItem.setEnabled(isNotificationEnabled(Notification.CALL));
    phoneCallItem.setTag(Notification.CALL);
    notificationItemsLayout.addView(phoneCallItem.getRoot());

    gmailItem.setCallback(notificationItemCallback);
    facebookItem.setCallback(notificationItemCallback);
    phoneCallItem.setCallback(notificationItemCallback);
    smsItem.setCallback(notificationItemCallback);

    return rootView;

  }

  private NotificationItemCallback notificationItemCallback = new NotificationItemCallback() {

    private int currentColor;

    @Override
    public void onNotificationColorRequest(final NotificationItem item) {
      currentColor = item.getColor();
      final FrameLayout contentView = (FrameLayout) item.getRoot().getRootView().findViewById(android.R.id.content);
      final RelativeLayout pickerLayout = new RelativeLayout(getActivity());
      pickerLayout.setBackgroundColor(getResources().getColor(R.color.semi_trans_black));
      pickerLayout.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
          // ignore
        }
      });
      final ColorPicker picker = new ColorPicker(getActivity());
      picker.setOnColorChangedListener(new OnColorChangedListener() {

        @Override
        public void onColorChanged(int color) {
          currentColor = color;
        }
      });
      FrameLayout.LayoutParams plLp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
      contentView.addView(pickerLayout, plLp);

      RelativeLayout.LayoutParams pLp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
          LayoutParams.WRAP_CONTENT);
      pLp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
      pickerLayout.addView(picker, pLp);

      Button okButton = new Button(getActivity());
      RelativeLayout.LayoutParams bLp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
          LayoutParams.WRAP_CONTENT);
      bLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
      bLp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
      bLp.bottomMargin = 20;
      okButton.setText("Pick color");
      okButton.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
          getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
              item.setColor(currentColor);
              Notification notification = (Notification) item.getTag();
              setNotificationColor(notification, currentColor);
              contentView.removeView(pickerLayout);
            }
          });
        }
      });
      pickerLayout.addView(okButton, bLp);
    }

    @Override
    public void onEnabledChanged(NotificationItem item, boolean enabled) {
      Notification notification = (Notification) item.getTag();
      enableNotification(notification, enabled);
    }
  };
  
 

}
