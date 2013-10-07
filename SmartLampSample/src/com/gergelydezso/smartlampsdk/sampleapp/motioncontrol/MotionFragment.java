package com.gergelydezso.smartlampsdk.sampleapp.motioncontrol;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import java.util.List;

public class MotionFragment extends Fragment implements OnClickListener, OnSeekBarChangeListener, View.OnTouchListener,
    View.OnLongClickListener {

  private final static String TAG = "MotionFragment";
  private LampMotion mLampMotion;
  //  private ImageView mImageFirst;
  public ImageView mImageCoordonate;
  //  private SeekBar mSeekBarPart0;
  //  private SeekBar mSeekBarPart4;
  private SmartLampAPI mApi;
  private LinearLayout lLayout;
  private List<LampModel> positionList;
  private HorizontalScrollView hScroll;
  private int objectNameIndicator;
  private RelativeLayout selectedElem;
  //  it is ok
  private AbsoluteLayout.LayoutParams layoutParams;
  private RelativeLayout rLayout;
  private ImageView lampHead;
  private ImageView lampBase;
  private TextView lampHeadText;

  private List<RelativeLayout> elements;

  public MotionFragment() {

    positionList = new ArrayList<LampModel>();
    elements = new ArrayList<RelativeLayout>();

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    mApi = SmartLampAPIHolder.getApi();

    View rootView = inflater.inflate(R.layout.fragment_motion_control, container, false);

    lampHead = (ImageView) rootView.findViewById(R.id.lamp_head);
    lampHead.setOnTouchListener(this);

    lampBase = (ImageView) rootView.findViewById(R.id.base);
    lampBase.setOnTouchListener(this);

    lampHeadText = (TextView) rootView.findViewById(R.id.lamp_head_text);

    rLayout = (RelativeLayout) rootView.findViewById(R.id.custom_view_container);
//    rLayout.setDrawingCacheEnabled(true);
    hScroll = (HorizontalScrollView) rootView.findViewById(R.id.horizontalScrollView);

    lLayout = (LinearLayout) rootView.findViewById(R.id.horizontal_layout);

//    mImageFirst = (ImageView) rootView.findViewById(R.id.parttt1);
//    mImageFirst.setPivotX(100);
//    mImageFirst.setPivotY(100);

    layoutParams = (AbsoluteLayout.LayoutParams) lampHeadText.getLayoutParams();

    mImageCoordonate = (ImageView) rootView.findViewById(R.id.imageView_coordinate);
    mLampMotion = (LampMotion) rootView.findViewById(R.id.lamp_motion);
//    mSeekBarPart0 = (SeekBar) rootView.findViewById(R.id.seekBar_part0);
//    mSeekBarPart4 = (SeekBar) rootView.findViewById(R.id.seekBar_part4);
//    mSeekBarPart0.setOnSeekBarChangeListener(this);
//    mSeekBarPart4.setOnSeekBarChangeListener(this);

    mLampMotion.setOwnerObejct(this);

    return rootView;
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.remove_icon) {
      lLayout.removeView(selectedElem);
    }
  }


  public void updateLampModel() {

  }

  public void setAngle(int r) {

    lampHead.setRotation(r);

//    mImageFirst.setRotation(r);

  }

  public void setPositions(int x, int y) {

    layoutParams.x = x;
    layoutParams.y = y;
    lampHeadText.setLayoutParams(layoutParams);

  }

  public void play() {

    for (LampModel lamp : positionList) {
      Log.d("MotionFragment", "Angle value: " + lamp.getPart0());
    }

//    for (RelativeLayout element : elements) {
//      element.setAlpha(0.2f);
//    }

    hScroll.post(new Runnable() {
      public void run() {
        hScroll.fullScroll(ScrollView.FOCUS_LEFT);
      }
    });


  }

  public void addListElement() {

    Bitmap resize = Bitmap.createScaledBitmap(mLampMotion.getDrawingCache(), 200, 166, true);
//    Bitmap resize = Bitmap.createScaledBitmap(rLayout.getDrawingCache(), 200, 166, true);

    RelativeLayout rLayout = (RelativeLayout) View.inflate(getActivity(), R.layout.list_elem, null);
    rLayout.setOnLongClickListener(this);
    ImageView removeIcon = (ImageView) rLayout.findViewById(R.id.remove_icon);
    removeIcon.setOnClickListener(this);
    ImageView image = (ImageView) rLayout.findViewById(R.id.list_image);
    image.setImageBitmap(resize);


    LampModel model = new LampModel();
    model.setPart0(mLampMotion.globalDegree);

    positionList.add(model);

    lLayout.addView(rLayout);
    elements.add(rLayout);


    hScroll.post(new Runnable() {
      public void run() {
        hScroll.fullScroll(ScrollView.FOCUS_RIGHT);
      }
    });

    objectNameIndicator++;

  }

//  public void saveViewToBitmap() {
//
//    // Bitmap b = Bitmap.createBitmap(mRelativLayoutContainer.getWidth(),
//    // mRelativLayoutContainer.getHeight(),
//    // Bitmap.Config.ARGB_8888);
//    // Canvas c = new Canvas(b);
//    // mRelativLayoutContainer.draw(c);
//    // BitmapDrawable d = new BitmapDrawable(getResources(), b);
//    // canvasView.setBackgroundDrawable(d);
//
//    Bitmap resize = Bitmap.createScaledBitmap(mLampMotion.getDrawingCache(), 400, 400, true);
//    mImageBitmap.setImageBitmap(resize);
//  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//    switch (seekBar.getId()) {
//      case R.id.seekBar_part0:
//        mLampMotion.setActivePart(0);
//        mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_y);
//        // mTextPart0.setText("lamp rotate by Y (" + progress + ")");
//
////        mApi.setServoPosition(ServoMotorEntity.SERVO1, progress, new CommandCallback() {
////
////          @Override
////          public void onSuccess() {
////
////          }
////
////          @Override
////          public void onResult(String state) {
////
////          }
////
////          @Override
////          public void onError() {
////
////          }
////        });
//
//        break;
//      case R.id.seekBar_part4:
//        mLampMotion.setActivePart(4);
//        mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_x);
//        // mTextPart4.setText("lamp rotate by X (" + progress + ")");
//
////        mApi.setServoPosition(ServoMotorEntity.SERVO2, progress, new CommandCallback() {
////
////        @Override
////        public void onSuccess() {
////
////        }
////
////        @Override
////        public void onResult(String state) {
////
////        }
////
////        @Override
////        public void onError() {
////
////        }
////      });
//
//        break;
//    }
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {

    int x = (int) event.getX();
    int y = (int) event.getY();
    double radian = Math.atan2(x - v.getWidth() / 2, v.getHeight() / 2 - y);
    double lDegree = Math.toDegrees(radian);

    if (event.getAction() == MotionEvent.ACTION_MOVE) {

      switch (v.getId()) {
        case R.id.lamp_head:
//        setAngle((int) lDegree);
          lampHead.setRotation((int) lDegree);
          lampHeadText.setText("" + (int) lDegree);
          break;
        case R.id.base:
          lampBase.setRotation((int) lDegree);
          break;
      }
    }
    return true;
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
