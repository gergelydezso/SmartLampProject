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

import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.connection.ConnectionListener;
import com.gergelydezso.smartlampsdk.connection.ConnectionType;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampHolder;
import com.gergelydezso.smartlampsdk.sampleapp.menu.BaseMenuActivity;

public class Intro extends Activity {

    private ImageView mImageStart;
    private ImageButton mButtonPlay;
    private VideoView mVideoIntro;
    private SmartLampHolder mApiHolder = new SmartLampHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Make the SmartLamp connection and get the SmartLamp API
        SmartLamp.connect(this, ConnectionType.BLUETOOTH, new ConnectionListener() {
            @Override
            public void onConnectionReady(SmartLamp lamp) {
                mApiHolder.setSmartLamp(lamp);
            }

            @Override
            public void onConnectionFailed() {

            }

            @Override
            public void onConnectionLost() {

            }
        });


        mImageStart = (ImageView) findViewById(R.id.image_start);

        mVideoIntro = (VideoView) findViewById(R.id.video_intro);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro_video);
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
                startBaseActivity();
            }
        });
    }

    public void startBaseActivity() {
        Intent intent = new Intent(Intro.this, BaseMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mImageStart.setVisibility(View.INVISIBLE);
            mVideoIntro.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
