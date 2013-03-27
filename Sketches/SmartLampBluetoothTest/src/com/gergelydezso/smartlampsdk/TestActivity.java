package com.gergelydezso.smartlampsdk;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionActivity;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectionHolder;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends Activity implements OnClickListener {

	private BluetoothConnectionService con;
	private ConnectionHolder conHolder = new ConnectionHolder();

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	private SmartLampAPI mSmartLamp = new SmartLampAPI();
	private Button mSendButtonServo1;
	private Button mSendButtonServo2;
	private Button mConnectionButton;
	private EditText mEditTextServo1;
	private EditText mEditTextServo2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		con = conHolder.getConnection();

		Log.v("TestActivity", "++onCreate++");
		setContentView(R.layout.test_activity);

		mSendButtonServo1 = (Button) findViewById(R.id.btn_servo1);
		mSendButtonServo2 = (Button) findViewById(R.id.btn_servo2);
		mConnectionButton = (Button) findViewById(R.id.btn_connection);
		mEditTextServo1 = (EditText) findViewById(R.id.edit_servo1);
		mEditTextServo2 = (EditText) findViewById(R.id.edit_servo2);

		mSendButtonServo1.setOnClickListener(this);
		mSendButtonServo2.setOnClickListener(this);
		mConnectionButton.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.v("TestActivity", "++onResume++");

		// this.mConnectionService = mTransporter.getObject();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		con.stop();
		Log.v("TestActivity", "++onDestroy++");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		int servoID = 0;
		int value = 0;

		switch (v.getId()) {

		case R.id.btn_servo1:
			// servoID = 1;
			// if (!mEditTextServo1.getText().toString().equals("")) {
			// value = Integer.parseInt(mEditTextServo1.getText().toString());
			// }

			String str = mEditTextServo1.getText().toString();
			byte[] send = str.getBytes();
			con.write(send);

			break;

		case R.id.btn_servo2:
			// servoID = 2;
			// if (!mEditTextServo2.getText().toString().equals("")) {
			// value = Integer.parseInt(mEditTextServo2.getText().toString());
			// }

			break;

		case R.id.btn_connection:

			break;
		}

		// mSmartLamp.createAddServoCommand(servoID, value);

	}

	// The Handler that gets information back from the BluetoothChatService
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			Log.v("Hangler", "Message recived");

		}
	};

}
