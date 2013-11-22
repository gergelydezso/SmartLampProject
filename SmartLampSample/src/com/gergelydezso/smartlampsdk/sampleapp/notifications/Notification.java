package com.gergelydezso.smartlampsdk.sampleapp.notifications;

import com.gergelydezso.smartlampsdk.sampleapp.R;

public enum Notification {
  GMAIL("GMAIL_ENABLED", "GMAIL_COLOR", R.color.red), FACEBOOK("FACEBOOK_ENABLED", "FACEBOOK_COLOR", R.color.blue), SMS(
      "SMS_ENABLED", "SMS_COLOR", R.color.green), CALL("CALL_ENABLED", "CALL_COLOR", android.R.color.white), CALENDAR("CALENDAR_ENABLED", "CALENDAR_COLOR", R.color.purple);

  private final String enabledKey;
  private final String colorKey;
  private final int defaultColorId;

  private Notification(String enabledKey, String colorKey, int defaultColorId) {
    this.enabledKey = enabledKey;
    this.colorKey = colorKey;
    this.defaultColorId = defaultColorId;
  }

  public String getEnabledKey() {
    return enabledKey;
  }

  public String getColorKey() {
    return colorKey;
  }

  public int getDefaultColorId() {
    return defaultColorId;
  }

}
