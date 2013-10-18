package com.gergelydezso.smartlampsdk.sampleapp.motioncontrol;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampAPIHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MotionFragment extends Fragment implements OnClickListener, View.OnTouchListener,
    View.OnLongClickListener {

  private final static String TAG = "MotionFragment";
  public ImageView mImageCoordonate;
  private SmartLampAPI mApi;
  private LinearLayout lLayout;
  private List<LampModel> positionList;
  private HorizontalScrollView hScroll;
  private RelativeLayout selectedElem;
  private List<RelativeLayout> elements;
  private ImageView overLayer;
  private List<Bitmap> overImages;
  private Animation alphaIn;
  private Animation alphaOut;
  private Animation alphaOutLong;


  private ImageView imageLampArm1;
  private ImageView imageLampArm2;
  private ImageView imageLampHead;
  private ImageView imageLampHeadContol;
  private ImageView imageLampBaseContol;

  private AbsoluteLayout.LayoutParams paramsImageLampArm1;
  private AbsoluteLayout.LayoutParams paramsImageLampArm2;
  private AbsoluteLayout.LayoutParams paramsImageLampHead;
  private AbsoluteLayout.LayoutParams paramsImageLampBaseControl;
  private AbsoluteLayout.LayoutParams paramsImageLampHeadControl;


  private double RADIAN;

  private int[] mFixPoint;
  private int[] mMovingPointArm1;
  private int[] mMovingPointArm2;
  private int mRampDistance1;
  private int mDistanceArm1;
  private double startAngle;

  private AbsoluteLayout mainLayout;

  private int servo1Angle;
  private int servo2Angle;
  private int servo3Angle;
  private int servo4Angle;
  private int servo5Angle;


  public MotionFragment() {
    positionList = new ArrayList<LampModel>();
    elements = new ArrayList<RelativeLayout>();
    overImages = new ArrayList<Bitmap>();


    mFixPoint = new int[2];
    mMovingPointArm1 = new int[2];
    mMovingPointArm2 = new int[2];

    startAngle = Math.toRadians(45);
    mDistanceArm1 = 200;
    mFixPoint[0] = 350;
    mFixPoint[1] = 500;

    mRampDistance1 = (int) (mDistanceArm1 * 0.7071);

    mMovingPointArm1[0] = mFixPoint[0] - mRampDistance1;
    mMovingPointArm1[1] = mFixPoint[1] - mRampDistance1;

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


    View rootView = inflater.inflate(R.layout.fragment_motion_control, container, false);
    mApi = SmartLampAPIHolder.getApi();

    mainLayout = (AbsoluteLayout) rootView.findViewById(R.id.absolute_layout);
    mainLayout.setDrawingCacheEnabled(true);

    alphaIn = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_in);
    alphaOut = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_out);
    alphaOutLong = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_out_long);

    overLayer = (ImageView) rootView.findViewById(R.id.over_layer);


    imageLampArm1 = (ImageView) rootView.findViewById(R.id.image_lamp_arm1);
    imageLampArm2 = (ImageView) rootView.findViewById(R.id.image_lamp_arm2);
    imageLampHead = (ImageView) rootView.findViewById(R.id.image_lamp_head);
    imageLampBaseContol = (ImageView) rootView.findViewById(R.id.image_lamp_base_control);
    imageLampHeadContol = (ImageView) rootView.findViewById(R.id.image_lamp_head_control);

    imageLampArm1.setOnTouchListener(this);
    imageLampArm2.setOnTouchListener(this);
    imageLampHead.setOnTouchListener(this);
    imageLampBaseContol.setOnTouchListener(this);
    imageLampHeadContol.setOnTouchListener(this);

    paramsImageLampArm1 = (AbsoluteLayout.LayoutParams) imageLampArm1.getLayoutParams();
    paramsImageLampArm2 = (AbsoluteLayout.LayoutParams) imageLampArm2.getLayoutParams();
    paramsImageLampHead = (AbsoluteLayout.LayoutParams) imageLampHead.getLayoutParams();
    paramsImageLampBaseControl = (AbsoluteLayout.LayoutParams) imageLampBaseContol.getLayoutParams();
    paramsImageLampHeadControl = (AbsoluteLayout.LayoutParams) imageLampHeadContol.getLayoutParams();

    hScroll = (HorizontalScrollView) rootView.findViewById(R.id.horizontalScrollView);
    lLayout = (LinearLayout) rootView.findViewById(R.id.horizontal_layout);


    mImageCoordonate = (ImageView) rootView.findViewById(R.id.imageView_coordinate);
    return rootView;
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.remove_icon) {
      lLayout.removeView(selectedElem);
    }
  }

  public void play() {

    mainLayout.setVisibility(View.INVISIBLE);
    overLayer.setVisibility(View.VISIBLE);

    hScroll.post(new Runnable() {
      public void run() {
        hScroll.fullScroll(ScrollView.FOCUS_LEFT);
      }
    });


    final Handler handler = new Handler();

    Runnable r = new Runnable() {
      int i = 0;

      @Override
      public void run() {


        overLayer.setImageBitmap(overImages.get(i));
        overLayer.startAnimation(alphaIn);
        lLayout.removeView(elements.get(i));
        handler.postDelayed(this, 1000);

        sendCommand(i);

        i++;

        if (i == overImages.size()) {
          handler.removeCallbacks(this);
          overImages.clear();
          overLayer.setVisibility(View.INVISIBLE);
          lLayout.removeAllViews();
          elements.clear();
          positionList.clear();

          mainLayout.setVisibility(View.VISIBLE);
        }
      }
    };

    handler.post(r);
  }

  public void sendCommand(int i) {


    Log.d("MotionFragment", "command value: " + positionList.get(i).getServo1Angle());


//    mApi.setServoPosition(ServoMotorEntity.SERVO1, positionList.get(i).getServo1Angle(), new CommandCallback() {
//      @Override
//      public void onSuccess() {
//      }
//
//      @Override
//      public void onError() {
//      }
//
//      @Override
//      public void onResult(String state) {
//      }
//    });

  }


  public void addListElement() {

    mainLayout.setDrawingCacheEnabled(true);

    Bitmap resize = Bitmap.createScaledBitmap(mainLayout.getDrawingCache(), 144, 256, true);

    Bitmap fullSize = Bitmap.createBitmap(mainLayout.getDrawingCache());
    overImages.add(fullSize);
    RelativeLayout newLayout = (RelativeLayout) View.inflate(getActivity(), R.layout.list_elem, null);
    newLayout.setOnLongClickListener(this);
    ImageView removeIcon = (ImageView) newLayout.findViewById(R.id.remove_icon);
    removeIcon.setOnClickListener(this);
    ImageView image = (ImageView) newLayout.findViewById(R.id.list_image);
    image.setImageBitmap(resize);


    lLayout.addView(newLayout);
    elements.add(newLayout);


    hScroll.post(new Runnable() {
      public void run() {
        hScroll.fullScroll(ScrollView.FOCUS_RIGHT);
      }
    });

    mainLayout.setDrawingCacheEnabled(false);


    LampModel model = new LampModel();
    model.setServo1Angle(servo1Angle);
    positionList.add(model);


  }

  public void setPositions(View view) {


    switch (view.getId()) {

      case R.id.image_lamp_arm1:

        paramsImageLampArm2.x = mMovingPointArm1[0] - imageLampArm2.getWidth() / 2;
        paramsImageLampArm2.y = mMovingPointArm1[1] - imageLampArm2.getHeight();
        imageLampArm2.setLayoutParams(paramsImageLampArm2);

        paramsImageLampHead.x = mMovingPointArm2[0];
        paramsImageLampHead.y = mMovingPointArm2[1] - 30;
        imageLampHead.setLayoutParams(paramsImageLampHead);

        break;
      case R.id.image_lamp_arm2:

        paramsImageLampHead.x = mMovingPointArm2[0];
        paramsImageLampHead.y = mMovingPointArm2[1] - 30;
        imageLampHead.setLayoutParams(paramsImageLampHead);

        break;
      case R.id.image_lamp_head:
        break;
    }
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {

    int x = (int) event.getRawX();
    int y = (int) event.getRawY();

    double degree = 0;
    int centerX = 0;
    int centerY = 0;


    if (event.getAction() == MotionEvent.ACTION_MOVE) {

      switch (v.getId()) {
        case R.id.image_lamp_arm1:

          centerX = paramsImageLampArm1.x + v.getWidth() / 2;
          centerY = paramsImageLampArm1.y + 400;
          degree = calculateAngle(x, y, centerX, centerY, false);

          if ((degree > 315) && (degree < 360)) {
            imageLampArm1.setRotation((int) degree);


            mMovingPointArm1 = calculatePoint(mFixPoint, 200, RADIAN);
            mMovingPointArm2 = calculatePoint(mMovingPointArm1, 200, startAngle);
            setPositions(v);


          }

          break;
        case R.id.image_lamp_arm2:
          centerX = paramsImageLampArm2.x + v.getWidth() / 2;
          centerY = paramsImageLampArm2.y + 400;
          degree = calculateAngle(x, y, centerX, centerY, false);

          if ((degree > 45) && (degree < 110)) {

            imageLampArm2.setRotation((int) degree);
            startAngle = RADIAN;
            mMovingPointArm2 = calculatePoint(mMovingPointArm1, 200, RADIAN);
            setPositions(v);
          }
          break;
        case R.id.image_lamp_head:
          centerX = paramsImageLampArm1.x;
          centerY = paramsImageLampArm1.y + 200;
          degree = calculateAngle(x, y, centerX, centerY, false);

          if ((degree > 0) && (degree < 135)) {
            imageLampHead.setRotation((int) (degree - 90));
          }
          break;

        case R.id.image_lamp_base_control:

          centerX = paramsImageLampBaseControl.x + v.getWidth() / 2;
          centerY = paramsImageLampBaseControl.y + 200;
          degree = calculateAngle(x, y, centerX, centerY, false);

          if ((degree > 0) && (degree < 180)) {
            imageLampBaseContol.setRotation((int) degree);
          }
          break;
        case R.id.image_lamp_head_control:

          centerX = paramsImageLampHeadControl.x + v.getWidth() / 2;
          centerY = paramsImageLampBaseControl.y + v.getHeight() / 2 + 50;
          degree = calculateAngle(x, y, centerX, centerY, false);

          if ((degree > 90) && (degree < 270)) {
            imageLampHeadContol.setRotation((int) degree - 180);

            servo1Angle = (int) degree - 90;

            Log.d("MotionFragment", "degree: " + degree);
          }
          break;


      }
    }
    return true;
  }

  public int[] calculatePoint(int[] fixPoint, int distance, double radian) {

    int[] Point = new int[2];

    Point[0] = fixPoint[0] + (int) (Math.sin(radian) * distance);
    Point[1] = fixPoint[1] - (int) (Math.cos(radian) * distance);

    return Point;
  }

  public double calculateAngle(int x, int y, int centerX, int centerY, boolean inRadian) {

    double radian = Math.atan2(x - centerX, centerY - y);
    RADIAN = radian;

    if (inRadian) {
      return radian;
    }

    int degree = (int) Math.toDegrees(radian);

    if (degree >= 0) {
      return degree;
    }
    else {
      return (degree + 360);
    }
  }

  @Override
  public boolean onLongClick(View v) {

    ImageView removeIcon = (ImageView) v.findViewById(R.id.remove_icon);
    ImageView settings = (ImageView) v.findViewById(R.id.settings_icon);
    ImageView image = (ImageView) v.findViewById(R.id.list_image);
//    settings.setVisibility(View.VISIBLE);
    image.setAlpha(0.2f);
    removeIcon.setVisibility(View.VISIBLE);
    selectedElem = (RelativeLayout) v;

    return true;
  }
}
