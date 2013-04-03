package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectionHolder;

import android.os.Bundle;

import android.app.Activity;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestActivity extends Activity implements OnClickListener {

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

		mSendButtonServo1.setOnClickListener(this);
		mSendButtonServo2.setOnClickListener(this);
		mSendButtonLedRed.setOnClickListener(this);
		mSendButtonLedGreen.setOnClickListener(this);
		mSendButtonLedBlue.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.v("TestActivity", "++onResume++");
	}

	@Override
	public void onClick(View v) {

		int servoID = 0;
		int value = 0;
		int ledPin = 0;

		switch (v.getId()) {

		case R.id.btn_servo1:
			servoID = 1;
			if (!mEditTextServo1.getText().toString().equals("")) {
				value = Integer.parseInt(mEditTextServo1.getText().toString());
			}

			break;

		case R.id.btn_servo2:
			servoID = 2;
			if (!mEditTextServo2.getText().toString().equals("")) {
				value = Integer.parseInt(mEditTextServo2.getText().toString());
			}

			break;

		case R.id.btn_red:
			ledPin = 1;
			if (!mEditTextLedRed.getText().toString().equals("")) {
				ledPin = Integer.parseInt(mEditTextLedRed.getText().toString());
			}

			break;

		case R.id.btn_green:
			ledPin = 2;
			if (!mEditTextLedGreen.getText().toString().equals("")) {
				ledPin = Integer.parseInt(mEditTextLedGreen.getText()
						.toString());
			}

			break;

		case R.id.btn_blue:
			ledPin = 3;
			if (!mEditTextLedBlue.getText().toString().equals("")) {
				ledPin = Integer
						.parseInt(mEditTextLedBlue.getText().toString());
			}

			break;

		}

		mSmartLampAPI.createAddServoCommand(servoID, value,
				new CommandCallback() {

					@Override
					public void onSuccess() {
						System.out.println("onSuccess");

					}

					@Override
					public void onError() {
						System.out.println("onError");

					}
				});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mConnectionService.stop();
		Log.v("TestActivity", "++onDestroy++");

	}

}
