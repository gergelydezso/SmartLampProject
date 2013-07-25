package com.gergelydezso.smartlampsdk.sampleapp.intro;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.connection.ConnectionStatusListener;
import com.gergelydezso.smartlampsdk.connection.SmartLampConnectionManager;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampAPIHolder;
import com.gergelydezso.smartlampsdk.sampleapp.menu.BaseMenuActivity;

public class Intro extends Activity {

  private ImageView mImageStart;
  private ImageButton mButtonPlay;
  private VideoView mVideoIntro;
  private SmartLampAPIHolder mApiHolder = new SmartLampAPIHolder();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);

    // *** Make the SmartLamp connection and get the SmartLamp API ***

    SmartLampConnectionManager manager = new SmartLampConnectionManager(Intro.this);
    manager.makeConnection(new ConnectionStatusListener() {

      @Override
      public void onConnectionReady(SmartLampAPI api) {
        mApiHolder.setAPI(api);
        Intent intent = new Intent(Intro.this, BaseMenuActivity.class);
        startActivity(intent);

      }
    });

    mImageStart = (ImageView) findViewById(R.id.image_start);
    mVideoIntro = (VideoView) findViewById(R.id.video_intro);
    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.huh_1);
    mVideoIntro.setVideoURI(uri);
    mVideoIntro.setOnCompletionListener(new OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mp) {

      }
    });

    mButtonPlay = (ImageButton) findViewById(R.id.ImageButton_paly);
    mButtonPlay.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        // mImageStart.setVisibility(View.INVISIBLE);
        // mVideoIntro.start();

        // Intent intent = new Intent(Intro.this,
        // BaseMenuActivity.class);
        // startActivity(intent);

        // Intent intent = new Intent(Intro.this, BaseMenuActivity.class);
        // startActivity(intent);

      }
    });

  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    mImageStart.setVisibility(View.INVISIBLE);
    mVideoIntro.start();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

}
