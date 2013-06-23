package com.gergelydezso.smartlampsdk.sample.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.gergelydezso.smartlampsdk.ServoMotorEntities;
import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionHolder;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.sample.R;

public class TestActivity extends Activity implements OnClickListener, OnSeekBarChangeListener {

  private static final String TAG = "TestActivity";
  private BluetoothConnectionService mConnectionService;
  private SmartLampAPI mSmartLampAPI = new SmartLampAPI();

  private Button mSendButtonServo1;
  private Button mSendButtonServo2;
  private Button mSendButtonLedRed;
  private Button mSendButtonLedGreen;
  private Button mButtonState;

  private EditText mEditTextServo1;
  private EditText mEditTextServo2;
  private EditText mEditTextLedRed;
  private EditText mEditTextLedGreen;
  private EditText mEditTextState;

  private SeekBar mSeekBarServo1;
  private SeekBar mSeekBarServo2;
  private SeekBar mSeekBarLedRed;
  private SeekBar mSeekBarLedGreen;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mConnectionService = BluetoothConnectionHolder.getConnection();

    Log.v("TestActivity", "++onCreate++");
    setContentView(R.layout.activity_test);

    mSendButtonServo1 = (Button) findViewById(R.id.btn_servo1);
    mSendButtonServo2 = (Button) findViewById(R.id.btn_servo2);
    mSendButtonLedRed = (Button) findViewById(R.id.btn_red);
    mSendButtonLedGreen = (Button) findViewById(R.id.btn_green);
    mButtonState = (Button) findViewById(R.id.btn_state);

    mEditTextServo1 = (EditText) findViewById(R.id.edit_servo1);
    mEditTextServo2 = (EditText) findViewById(R.id.edit_servo2);
    mEditTextLedRed = (EditText) findViewById(R.id.edit_red);
    mEditTextLedGreen = (EditText) findViewById(R.id.edit_green);
    mEditTextState = (EditText) findViewById(R.id.edit_state);

    mSeekBarServo1 = (SeekBar) findViewById(R.id.seekbar_servo1);
    mSeekBarServo2 = (SeekBar) findViewById(R.id.seekbar_servo2);
    mSeekBarLedRed = (SeekBar) findViewById(R.id.seekbar_ledred);
    mSeekBarLedGreen = (SeekBar) findViewById(R.id.seekbar_ledgreen);

    mSendButtonServo1.setOnClickListener(this);
    mSendButtonServo2.setOnClickListener(this);
    mSendButtonLedRed.setOnClickListener(this);
    mSendButtonLedGreen.setOnClickListener(this);
    mButtonState.setOnClickListener(this);

    mSeekBarServo1.setOnSeekBarChangeListener(this);
    mSeekBarServo2.setOnSeekBarChangeListener(this);
    mSeekBarLedRed.setOnSeekBarChangeListener(this);
    mSeekBarLedGreen.setOnSeekBarChangeListener(this);

  }

  @Override
  protected void onResume() {
    super.onResume();

    Log.v(TAG, "++onResume++");
  }

  @Override
  public void onClick(View v) {

    int value = 0;

    switch (v.getId()) {

    case R.id.btn_servo1:
      if (!mEditTextServo1.getText().toString().equals("")) {
        value = Integer.parseInt(mEditTextServo1.getText().toString());
      }
      createServoSetCommand(ServoMotorEntities.SERVO1, value);
      break;

    case R.id.btn_servo2:
      if (!mEditTextServo2.getText().toString().equals("")) {
        value = Integer.parseInt(mEditTextServo2.getText().toString());
      }
      createServoSetCommand(ServoMotorEntities.SERVO2, value);
      break;

    case R.id.btn_red:
      if (!mEditTextLedRed.getText().toString().equals("")) {
        value = Integer.parseInt(mEditTextLedRed.getText().toString());
      }
      break;

    case R.id.btn_green:
      if (!mEditTextLedGreen.getText().toString().equals("")) {
        value = Integer.parseInt(mEditTextLedGreen.getText().toString());
      }
      break;

    case R.id.btn_state:
      createLedStatCommand();
      break;

    }

  }

  private void createLedStatCommand() {
    mSmartLampAPI.getLedState(new CommandCallback() {

      @Override
      public void onSuccess() {

      }

      @Override
      public void onResult(String state) {
        Log.v(TAG, state);
      }

      @Override
      public void onError() {

      }
    });
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    switch (seekBar.getId()) {
    case R.id.seekbar_servo1:
      createServoSetCommand(ServoMotorEntities.SERVO1, progress);
      break;
    case R.id.seekbar_servo2:
      createServoSetCommand(ServoMotorEntities.SERVO2, progress);
      break;
    case R.id.seekbar_ledred:
      break;
    case R.id.seekbar_ledgreen:
      break;

    }

  }

  private void createServoSetCommand(ServoMotorEntities servoPin, int value) {

    mSmartLampAPI.setServoPosition(servoPin, value, new CommandCallback() {

      @Override
      public void onSuccess() {
        Log.d(TAG, "onSuccess - servoCommand");
      }

      @Override
      public void onError() {
        Log.d(TAG, "onError - servoCommand");
      }

      @Override
      public void onResult(String state) {
        // TODO Auto-generated method stub

      }

    });

  }

  private void createLedCommand(int red, int green, int blue) {

    mSmartLampAPI.setLedValue(red, green, blue, new CommandCallback() {

      @Override
      public void onSuccess() {
        // TODO Auto-generated method stub
      }

      @Override
      public void onError() {
        // TODO Auto-generated method stub

      }

      @Override
      public void onResult(String state) {
        // TODO Auto-generated method stub

      }
    });
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mConnectionService.stop();
    Log.v(TAG, "++onDestroy++");

  }

}
