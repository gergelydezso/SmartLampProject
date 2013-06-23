package com.gergelydezso.smartlampsdk.sample.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionControl;
import com.gergelydezso.smartlampsdk.sample.R;
import com.gergelydezso.smartlampsdk.sample.alarmclock.AlarmClockActivity;
import com.gergelydezso.smartlampsdk.sample.colorcontrol.ColorControlPickerActivity;
import com.gergelydezso.smartlampsdk.sample.motioncontrol.MotionControlActivity;
import com.gergelydezso.smartlampsdk.sample.musicvisualization.MusicVisualizationActivity;
import com.gergelydezso.smartlampsdk.sample.notifications.NotificationsActivity;
import com.gergelydezso.smartlampsdk.sample.test.TestActivity;

public class MenuActivity extends Activity implements OnClickListener {

  private Button mButtonAlarmClock;
  private Button mButtonColorControl;
  private Button mButtonMotionControl;
  private Button mButtonMusicVisualization;
  private Button mButtonNotifications;
  private Button mButtonConnection;
  private Button mButtonTest;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);

    mButtonAlarmClock = (Button) findViewById(R.id.btn_alarm_clock);
    mButtonColorControl = (Button) findViewById(R.id.btn_color_control);
    mButtonMotionControl = (Button) findViewById(R.id.btn_motion_control);
    mButtonMusicVisualization = (Button) findViewById(R.id.btn_music_visualization);
    mButtonNotifications = (Button) findViewById(R.id.btn_notifications);
    mButtonConnection = (Button) findViewById(R.id.btn_make_connection);
    mButtonTest = (Button) findViewById(R.id.btn_test);

    mButtonAlarmClock.setOnClickListener(this);
    mButtonColorControl.setOnClickListener(this);
    mButtonMotionControl.setOnClickListener(this);
    mButtonMusicVisualization.setOnClickListener(this);
    mButtonNotifications.setOnClickListener(this);
    mButtonConnection.setOnClickListener(this);
    mButtonTest.setOnClickListener(this);

  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {

    case R.id.btn_alarm_clock:
      Intent intent1 = new Intent(MenuActivity.this, AlarmClockActivity.class);
      startActivity(intent1);
      break;

    case R.id.btn_color_control:
      Intent intent2 = new Intent(MenuActivity.this, ColorControlPickerActivity.class);
      startActivity(intent2);
      break;

    case R.id.btn_motion_control:
      Intent intent3 = new Intent(MenuActivity.this, MotionControlActivity.class);
      startActivity(intent3);
      break;

    case R.id.btn_music_visualization:
      Intent intent4 = new Intent(MenuActivity.this, MusicVisualizationActivity.class);
      startActivity(intent4);
      break;

    case R.id.btn_notifications:
      Intent intent5 = new Intent(MenuActivity.this, NotificationsActivity.class);
      startActivity(intent5);
      break;

    case R.id.btn_make_connection:
      BluetoothConnectionControl b = new BluetoothConnectionControl(MenuActivity.this);
      b.makeConnection();
      break;

    case R.id.btn_test:
      Intent intent6 = new Intent(MenuActivity.this, TestActivity.class);
      startActivity(intent6);
      break;

    }

  }

}
