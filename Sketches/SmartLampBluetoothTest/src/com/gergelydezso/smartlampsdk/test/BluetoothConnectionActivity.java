package com.gergelydezso.smartlampsdk.test;

import com.gergelydezso.smartlampsdk.R;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionService;
import com.gergelydezso.smartlampsdk.connection.bluetooth.ConnectionHolder;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BluetoothConnectionActivity extends Activity {

	private static final String TAG = "BluetoothConnectionActivity";
	private static final boolean D = true;

	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
	private static final int REQUEST_ENABLE_BT = 3;

	private Button mControlButton;
	private Button mConnectButton;

	private String mConnectedDeviceName = null;
	public BluetoothAdapter mBluetoothAdapter = null;
	public BluetoothConnectionService mChatService = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (D)
			Log.e(TAG, "+++ ON CREATE +++");

		setContentView(R.layout.connection);

		mConnectButton = (Button) findViewById(R.id.btn_smartlamp);
		mControlButton = (Button) findViewById(R.id.btn_control);
		mControlButton.setEnabled(false);

		mConnectButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent serverIntent = null;
				serverIntent = new Intent(BluetoothConnectionActivity.this,
						BluetoothDeviceListActivity.class);
				startActivityForResult(serverIntent,
						REQUEST_CONNECT_DEVICE_SECURE);
			}
		});

		mControlButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ConnectionHolder con = new ConnectionHolder();
				con.setConnection(mChatService);
				Intent intent = new Intent(BluetoothConnectionActivity.this,
						TestActivity.class);
				startActivity(intent);
			}
		});

		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available",
					Toast.LENGTH_LONG).show();
			finish();
			return;
		}

	}

	@Override
	public void onStart() {
		super.onStart();
		if (D)
			Log.e(TAG, "++ ON START ++");

		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
		} else {
			if (mChatService == null)
				setupConnection();
		}
	}

	@Override
	public synchronized void onResume() {
		super.onResume();
		if (D)
			Log.e(TAG, "+ ON RESUME +");

		if (mChatService != null) {

			if (mChatService.getState() == BluetoothConnectionService.STATE_NONE) {

				mChatService.start();
			}
		}
	}

	private void setupConnection() {
		Log.d(TAG, "setupConnection()");

		mChatService = new BluetoothConnectionService(mHandler);

	}

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_STATE_CHANGE:
				if (D)
					Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
				switch (msg.arg1) {
				case BluetoothConnectionService.STATE_CONNECTED:
					break;
				case BluetoothConnectionService.STATE_CONNECTING:
					break;
				case BluetoothConnectionService.STATE_LISTEN:
					break;
				case BluetoothConnectionService.STATE_NONE:
					break;
				}
				break;
			case MESSAGE_WRITE:
				break;
			case MESSAGE_READ:
				break;
			case MESSAGE_DEVICE_NAME:

				mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				Toast.makeText(getApplicationContext(),
						"Connected to " + mConnectedDeviceName,
						Toast.LENGTH_SHORT).show();
				mControlButton.setEnabled(true);

				break;
			case MESSAGE_TOAST:
				Toast.makeText(getApplicationContext(),
						msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}
	};

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (D)
			Log.d(TAG, "onActivityResult " + resultCode);
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE_SECURE:

			if (resultCode == Activity.RESULT_OK) {
				connectDevice(data);
			}
			break;

		case REQUEST_ENABLE_BT:

			if (resultCode == Activity.RESULT_OK) {
				setupConnection();
			} else {

				Log.d(TAG, "BT not enabled");
				Toast.makeText(this, R.string.bt_not_enabled_leaving,
						Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}

	private void connectDevice(Intent data) {

		String address = data.getExtras().getString(
				BluetoothDeviceListActivity.EXTRA_DEVICE_ADDRESS);

		BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);

		mChatService.connect(device);
	}

	@Override
	public synchronized void onPause() {
		super.onPause();
		if (D)
			Log.e(TAG, "- ON PAUSE -");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (D)
			Log.e(TAG, "-- ON STOP --");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "--- ON DESTROY ---");
	}

}
