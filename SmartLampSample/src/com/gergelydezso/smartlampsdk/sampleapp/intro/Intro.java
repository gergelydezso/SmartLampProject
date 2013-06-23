package com.gergelydezso.smartlampsdk.sampleapp.intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.menu.BaseMenuActivity;

public class Intro extends Activity {

  // private ImageView mAnimImage;
  // private AnimationDrawable frameAnimation;
  @SuppressWarnings("unused")
  private RelativeLayout mParentLayout;
  private ImageButton mButtonPlay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);

    // mAnimImage = (ImageView) findViewById(R.id.image_frame_animation);
    // mAnimImage.setBackgroundResource(R.drawable.lamp_animation_list);
    // frameAnimation = (AnimationDrawable) mAnimImage.getBackground();
    // frameAnimation.setOneShot(true);

    mButtonPlay = (ImageButton) findViewById(R.id.ImageButton_paly);
    mButtonPlay.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent intent = new Intent(Intro.this, BaseMenuActivity.class);
        startActivity(intent);

        // ImageView animatedImage = new ImageView(Intro.this);
        // animatedImage.setBackgroundResource(R.drawable.lamp_animation_list);
        // frameAnimation = (AnimationDrawable) animatedImage.getBackground();
        // mParentLayout.addView(animatedImage);

      }
    });
    mParentLayout = (RelativeLayout) findViewById(R.id.relativeLayout_intro);

  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    // frameAnimation.start();
  }

}
