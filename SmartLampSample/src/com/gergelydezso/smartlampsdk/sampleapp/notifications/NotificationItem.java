package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.gergelydezso.smartlampsdk.sampleapp.R;

public class NotificationItem {

  private View root;
  private ImageView notificationimage;
  private TextView notificationText;
  private Switch notificationSwitch;
  private LinearLayout colorLayout;
  private View colorView;
  private int color;
  private NotificationItemCallback callback;
  private Object tag;

  public NotificationItem(View view) {
    this.root = view;
    notificationimage = (ImageView) root.findViewById(R.id.img);
    notificationText = (TextView) root.findViewById(R.id.notification_item_text);
    notificationSwitch = (Switch) root.findViewById(R.id.notify_switch);
    notificationSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        colorLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        if (callback != null) {
          callback.onEnabledChanged(NotificationItem.this, isChecked);
        }
      }
    });
    colorLayout = (LinearLayout) root.findViewById(R.id.notification_color_layout);
    colorLayout.setVisibility(View.GONE);
    colorView = root.findViewById(R.id.notification_color);
    colorView.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        if (callback != null) {
          callback.onNotificationColorRequest(NotificationItem.this);
        }
      }
    });
  }

  public Object getTag() {
    return tag;
  }

  public void setTag(Object tag) {
    this.tag = tag;
  }

  public View getRoot() {
    return root;
  }

  public void setNotificationImageId(int notificationImageId) {
    notificationimage.setBackgroundResource(notificationImageId);
  }

  public void setNotificationItemText(String notificationItemText) {
    notificationText.setText(notificationItemText);
  }

  public void setEnabled(boolean enabled) {
    notificationSwitch.setChecked(enabled);
  }

  public void setColor(int color) {
    this.color = color;
    colorView.setBackgroundColor(color);
  }

  public int getColor() {
    return color;
  }

  public void setCallback(NotificationItemCallback callback) {
    this.callback = callback;
  }

  public interface NotificationItemCallback {
    void onNotificationColorRequest(NotificationItem item);

    void onEnabledChanged(NotificationItem item, boolean enabled);
  }

}
