package com.gergelydezso.smartlampsdk.sample.connection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothConnectionActivity;
import com.gergelydezso.smartlampsdk.sample.R;
import com.gergelydezso.smartlampsdk.sample.R.id;
import com.gergelydezso.smartlampsdk.sample.control.TestActivity;

public class ConnectionConfiguration extends Activity {

  private Button mConnectBtton;
  private Button mControlButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.connection_activity);

    mConnectBtton = (Button) findViewById(id.btn_connection_config);
    mConnectBtton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ConnectionConfiguration.this, BluetoothConnectionActivity.class);
        startActivity(intent);
      }
    });

    mControlButton = (Button) findViewById(R.id.btn_control);
    mControlButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent intent = new Intent(ConnectionConfiguration.this, TestActivity.class);
        startActivity(intent);

      }
    });

  }

}
