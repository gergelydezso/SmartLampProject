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
import com.gergelydezso.smartlampsdk.sampleapp.colorcontrol.ColorPickerView;
import com.gergelydezso.smartlampsdk.sampleapp.notifications.NotificationItem.NotificationItemCallback;

public class NotificationsFragment extends Fragment {

    private NotificationItem gmailItem;
    private NotificationItem facebookItem;
    private NotificationItem smsItem;
    private NotificationItem phoneCallItem;
    private View rootView;

    private int getNotificationColor(Notification notification) {
        return DeviceNotifications.getInstance().getNotificationSettings().getNotificationColor(notification);
    }

    private boolean isNotificationEnabled(Notification notification) {
        return DeviceNotifications.getInstance().getNotificationSettings().isNotificationEnabled(notification);
    }

    private void setNotificationColor(Notification notification, int color) {
        DeviceNotifications.getInstance().getNotificationSettings().setNotificationColor(notification, color);
    }

    private void enableNotification(Notification notification, boolean enabled) {
        DeviceNotifications.getInstance().getNotificationSettings().enableNotification(notification, enabled);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
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
        phoneCallItem.setNotificationImageId(R.drawable.call_new);
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
            LayoutInflater inflater =
                    (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.notification_color, null);
            ColorPickerView picker = (ColorPickerView) view.findViewById(R.id.color_picker);
            picker.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
                @Override
                public void onColorChanged(int color) {
                    currentColor = color;
                }
            });
            Button pickColorButton = (Button) view.findViewById(R.id.pick_color);
            pickColorButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            item.setColor(currentColor);
                            Notification notification = (Notification) item.getTag();
                            setNotificationColor(notification, currentColor);
                            contentView.removeView(view);
                        }
                    });
                }
            });
            FrameLayout.LayoutParams plLp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            contentView.addView(view, plLp);

            contentView.invalidate();
        }

        @Override
        public void onEnabledChanged(NotificationItem item, boolean enabled) {
            Notification notification = (Notification) item.getTag();
            enableNotification(notification, enabled);
        }
    };

}
