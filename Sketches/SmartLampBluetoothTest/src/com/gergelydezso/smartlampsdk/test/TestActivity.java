package com.gergelydezso.smartlampsdk.test;

import com.gergelydezso.smartlampsdk.LedPin;
import com.gergelydezso.smartlampsdk.R;
import com.gergelydezso.smartlampsdk.ServoPin;
import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectionHolder;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.app.Activity;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class TestActivity extends Activity implements OnClickListener,
		OnSeekBarChangeListener {

	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";
	private static final boolean D = true;
	private String mConnectedDeviceName = null;

	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	private static final String TAG = "TestActivity";

	private BluetoothConnectionService mConnectionService;
	private ConnectionHolder mConnectionHolder = new ConnectionHolder();

	private SmartLampAPI mSmartLampAPI = new SmartLampAPI();

	private Button mSendButtonServo1;
	private Button mSendButtonServo2;
	private Button mSendButtonLedRed;
	private Button mSendButtonLedGreen;
	private Button mSendButtonLedBlue;

	private EditText mEditTextServo1;
	private EditText mEditTextServo2;
	private EditText mEditTextLedRed;
	private EditText mEditTextLedGreen;
	private EditText mEditTextLedBlue;

	private SeekBar mSeekBarServo1;
	private SeekBar mSeekBarServo2;
	private SeekBar mSeekBarLedRed;
	private SeekBar mSeekBarLedGreen;
	private SeekBar mSeekBarLedBlue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mConnectionService = mConnectionHolder.getConnection();

		Log.v("TestActivity", "++onCreate++");
		setContentView(R.layout.test_activity);

		mSendButtonServo1 = (Button) findViewById(R.id.btn_servo1);
		mSendButtonServo2 = (Button) findViewById(R.id.btn_servo2);
		mSendButtonLedRed = (Button) findViewById(R.id.btn_red);
		mSendButtonLedGreen = (Button) findViewById(R.id.btn_green);
		mSendButtonLedBlue = (Button) findViewById(R.id.btn_blue);

		mEditTextServo1 = (EditText) findViewById(R.id.edit_servo1);
		mEditTextServo2 = (EditText) findViewById(R.id.edit_servo2);
		mEditTextLedRed = (EditText) findViewById(R.id.edit_red);
		mEditTextLedGreen = (EditText) findViewById(R.id.edit_green);
		mEditTextLedBlue = (EditText) findViewById(R.id.edit_blue);

		mSeekBarServo1 = (SeekBar) findViewById(R.id.seekbar_servo1);
		mSeekBarServo2 = (SeekBar) findViewById(R.id.seekbar_servo2);
		mSeekBarLedRed = (SeekBar) findViewById(R.id.seekbar_ledred);
		mSeekBarLedGreen = (SeekBar) findViewById(R.id.seekbar_ledgreen);
		mSeekBarLedBlue = (SeekBar) findViewById(R.id.seekbar_ledblue);

		mSendButtonServo1.setOnClickListener(this);
		mSendButtonServo2.setOnClickListener(this);
		mSendButtonLedRed.setOnClickListener(this);
		mSendButtonLedGreen.setOnClickListener(this);
		mSendButtonLedBlue.setOnClickListener(this);

		mSeekBarServo1.setOnSeekBarChangeListener(this);
		mSeekBarServo2.setOnSeekBarChangeListener(this);
		mSeekBarLedRed.setOnSeekBarChangeListener(this);
		mSeekBarLedGreen.setOnSeekBarChangeListener(this);
		mSeekBarLedBlue.setOnSeekBarChangeListener(this);

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
			createServoCommand(ServoPin.SERVO1, value);
			break;

		case R.id.btn_servo2:
			if (!mEditTextServo2.getText().toString().equals("")) {
				value = Integer.parseInt(mEditTextServo2.getText().toString());
			}
			createServoCommand(ServoPin.SERVO2, value);
			break;

		case R.id.btn_red:
			if (!mEditTextLedRed.getText().toString().equals("")) {
				value = Integer.parseInt(mEditTextLedRed.getText().toString());
			}
			createLedCommand(LedPin.RED_PIN, value);
			break;

		case R.id.btn_green:
			if (!mEditTextLedGreen.getText().toString().equals("")) {
				value = Integer
						.parseInt(mEditTextLedGreen.getText().toString());
			}
			createLedCommand(LedPin.GREEN_PIN, value);
			break;

		case R.id.btn_blue:
			if (!mEditTextLedBlue.getText().toString().equals("")) {
				value = Integer.parseInt(mEditTextLedBlue.getText().toString());
			}
			createLedCommand(LedPin.BLUE_PIN, value);
			break;

		}

	}

	private void createServoCommand(ServoPin servoPin, int value) {

		mSmartLampAPI.setServoPosition(servoPin, value, new CommandCallback() {

			@Override
			public void onSuccess() {
				Log.d(TAG, "onSuccess - servoCommand");
			}

			@Override
			public void onError() {
				Log.d(TAG, "onError - servoCommand");
			}
		});

	}

	private void createLedCommand(LedPin ledPin, int value) {

		mSmartLampAPI.setLedValue(ledPin, value, new CommandCallback() {

			@Override
			public void onSuccess() {
				Log.d(TAG, "onSuccess - ledCommand");
			}

			@Override
			public void onError() {
				Log.d(TAG, "onError - ledCommand");
			}
		});
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		switch (seekBar.getId()) {
		case R.id.seekbar_servo1:
			createServoCommand(ServoPin.SERVO1, progress);

			break;
		case R.id.seekbar_servo2:
			createServoCommand(ServoPin.SERVO2, progress);

			break;
		case R.id.seekbar_ledred:
			createLedCommand(LedPin.RED_PIN, progress);
			break;
		case R.id.seekbar_ledgreen:
			createLedCommand(LedPin.GREEN_PIN, progress);
			break;
		case R.id.seekbar_ledblue:
			createLedCommand(LedPin.BLUE_PIN, progress);
			break;

		}

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
