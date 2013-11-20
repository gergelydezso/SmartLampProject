package com.gergelydezso.smartlampsdk.connection;

import android.app.Activity;

public class UserAppContextHolder {

  private static Activity mContext;

  public void setContext(Activity context) {
    UserAppContextHolder.mContext = context;
  }

  public static Activity getContext() {
    return UserAppContextHolder.mContext;
  }

}
