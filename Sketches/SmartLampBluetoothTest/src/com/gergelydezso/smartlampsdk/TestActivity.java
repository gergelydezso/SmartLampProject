package com.gergelydezso.smartlampsdk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.gergelydezso.smartlampsdk.ServoMotor.ServoPin;
import com.gergelydezso.smartlampsdk.command.Command;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.command.CommandEngine;
import com.gergelydezso.smartlampsdk.command.ServoCommand;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestActivity extends Activity {

	// private boolean success = false;
	private Button mSendButton;
	private EditText mServoEdit;

	private SmartLamp lamp = new SmartLamp();
	private CommandEngine engine = new CommandEngine();

	private Command servo;

	private final CountDownLatch cdl = new CountDownLatch(1);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSendButton = (Button) findViewById(R.id.btn_servo1);
		mServoEdit = (EditText) findViewById(R.id.edit_servo1);

		mSendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				servo = new ServoCommand(lamp, ServoPin.SERVO1, 120,
						new CommandCallback() {

							@Override
							public void onSuccess() {

								cdl.countDown();
								System.out.println("onSuccess");

							}

							@Override
							public void onError() {

								cdl.countDown();
								System.out.println("onError");
							}
						});

				engine.executeCommand(servo);

				try {
					cdl.await(5000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

				System.out.println("Finished");

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
