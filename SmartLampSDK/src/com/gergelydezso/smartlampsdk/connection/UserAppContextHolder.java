package com.gergelydezso.smartlampsdk.connection;

import android.content.Context;

public class UserAppContextHolder {

  private static Context mContext;

  public void setContext(Context context) {
    UserAppContextHolder.mContext = context;
  }

  public static Context getContext() {
    return UserAppContextHolder.mContext;
  }

}
