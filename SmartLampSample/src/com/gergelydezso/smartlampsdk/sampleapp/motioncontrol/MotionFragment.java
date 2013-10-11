package com.gergelydezso.smartlampsdk.sampleapp.motioncontrol;

import android.graphics.Bitmap;
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
import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampAPIHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MotionFragment extends Fragment implements OnClickListener, OnSeekBarChangeListener, View.OnTouchListener,
    View.OnLongClickListener {

  private final static String TAG = "MotionFragment";
  private LampMotion mLampMotion;
  public ImageView mImageCoordonate;
  private SmartLampAPI mApi;
  private LinearLayout lLayout;
  private List<LampModel> positionList;
  private HorizontalScrollView hScroll;
  private int objectNameIndicator;
  private RelativeLayout selectedElem;
  //  it is oke
  private AbsoluteLayout.LayoutParams layoutParams1;
  private AbsoluteLayout.LayoutParams layoutParams2;
  private AbsoluteLayout.LayoutParams layoutParams3;
  private AbsoluteLayout.LayoutParams layoutParams4;
  //  private RelativeLayout rLayout;
  private ImageView lampHeadControl;
  private ImageView lampBase;
  private TextView lampHeadText;
  private List<RelativeLayout> elements;
  private ImageView mImageFirst;
  private ImageView testImage;
  private ImageView testImage2;
  private ImageView overLayer;
  private List<Bitmap> overImages;
  private ImageView lampHead;
  private Iterator it;
  private Animation alphaIn;
  private Animation alphaOut;
  private Animation alphaOutLong;

  public MotionFragment() {
    positionList = new ArrayList<LampModel>();
    elements = new ArrayList<RelativeLayout>();
    overImages = new ArrayList<Bitmap>();
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    mApi = SmartLampAPIHolder.getApi();

    View rootView = inflater.inflate(R.layout.fragment_motion_control, container, false);

    alphaIn = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_in);
    alphaOut = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_out);
    alphaOutLong = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_out_long);

    overLayer = (ImageView) rootView.findViewById(R.id.over_layer);

    lampHead = (ImageView) rootView.findViewById(R.id.lamp_head_image);

    lampHeadControl = (ImageView) rootView.findViewById(R.id.lamp_head_control);
    lampHeadControl.setOnTouchListener(this);

    testImage = (ImageView) rootView.findViewById(R.id.parttt0);
    testImage.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

          case MotionEvent.ACTION_MOVE:
            int x = (int) event.getX();
            int y = (int) event.getY();

            double radian = Math.atan2(1280 / 2 - y, x - 720 / 2);
            int globalDegree = (int) Math.toDegrees(radian);


            if (globalDegree >= 0) {
              v.setRotation(globalDegree);
              Log.d("M", "---------------------------------------: " + globalDegree);
            }
            else {
              v.setRotation(360 + globalDegree);
              Log.d("M", "---------------------------------------: " + (360 + globalDegree));
            }

//            v.setRotation(globalDegree);
            break;
        }
        return true;
      }
    });

    layoutParams3 = (AbsoluteLayout.LayoutParams) testImage.getLayoutParams();

    testImage2 = (ImageView) rootView.findViewById(R.id.parttt00);
    layoutParams4 = (AbsoluteLayout.LayoutParams) testImage2.getLayoutParams();

    lampBase = (ImageView) rootView.findViewById(R.id.base);
    lampBase.setOnTouchListener(this);

    lampHeadText = (TextView) rootView.findViewById(R.id.lamp_head_text);

//    rLayout = (RelativeLayout) rootView.findViewById(R.id.custom_view_container);
//    rLayout.setDrawingCacheEnabled(true);
    hScroll = (HorizontalScrollView) rootView.findViewById(R.id.horizontalScrollView);

    lLayout = (LinearLayout) rootView.findViewById(R.id.horizontal_layout);

    mImageFirst = (ImageView) rootView.findViewById(R.id.lamp_head_image);
    mImageFirst.setPivotX(0);
    mImageFirst.setPivotY(100);

    testImage.setPivotX(0);
    testImage.setPivotY(141);

//    layoutParams = (AbsoluteLayout.LayoutParams) lampHeadText.getLayoutParams();
    layoutParams1 = (AbsoluteLayout.LayoutParams) mImageFirst.getLayoutParams();
    layoutParams2 = (AbsoluteLayout.LayoutParams) lampHeadText.getLayoutParams();


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

//    lampHead.setRotation(r);

//    mImageFirst.setRotation(r);

    testImage.setRotation(r);

  }

  public void setPositions(int x, int y) {

    layoutParams2.x = x;
    layoutParams2.y = y - 100;
    lampHeadText.setLayoutParams(layoutParams2);

    layoutParams1.x = x ;
    layoutParams1.y = y - 30;
    mImageFirst.setLayoutParams(layoutParams1);

    layoutParams3.x = x - 141;
    layoutParams3.y = y;
    testImage.setLayoutParams(layoutParams3);

  }

  public void play() {

    overLayer.setVisibility(View.VISIBLE);
//    overLayer.setImageBitmap(overImages.get(overImages.size()));

    hScroll.post(new Runnable() {
      public void run() {
        hScroll.fullScroll(ScrollView.FOCUS_LEFT);
      }
    });

    it = overImages.iterator();

    mLampMotion.setVisibility(View.INVISIBLE);
    final Handler handler = new Handler();

    Runnable r = new Runnable() {
      int i = 0;

      @Override
      public void run() {


        overLayer.setImageBitmap(overImages.get(i));
        overLayer.startAnimation(alphaIn);

        lLayout.removeView(elements.get(i));
        handler.postDelayed(this, 1000);
        i++;


        if (i == overImages.size()) {
          mLampMotion.setVisibility(View.VISIBLE);
          handler.removeCallbacks(this);
          overImages.clear();
          overLayer.setVisibility(View.INVISIBLE);
          lLayout.removeAllViews();
          elements.clear();
        }
      }
    };

    handler.post(r);


  }

  public void setImage(int i) {
    overLayer.setImageBitmap(overImages.get(i));
  }


  public void addListElement() {

    Bitmap resize = Bitmap.createScaledBitmap(mLampMotion.getDrawingCache(), 200, 166, true);

    Bitmap fullSize = Bitmap.createBitmap(mLampMotion.getDrawingCache());
    overImages.add(fullSize);

//    Bitmap resize = Bitmap.createScaledBitmap(rLayout.getDrawingCache(), 200, 166, true);

//    RelativeLayout layout = new RelativeLayout(getActivity());

    RelativeLayout newLayout = (RelativeLayout) View.inflate(getActivity(), R.layout.list_elem, null);

//    View newLayout = getActivity().getLayoutInflater().inflate(R.layout.list_elem, null);


//    RelativeLayout rLayout = (RelativeLayout) View.inflate(getActivity(), R.layout.list_elem, null);

    newLayout.setOnLongClickListener(this);
    ImageView removeIcon = (ImageView) newLayout.findViewById(R.id.remove_icon);
    removeIcon.setOnClickListener(this);

    ImageView image = (ImageView) newLayout.findViewById(R.id.list_image);
    image.setImageBitmap(resize);


    LampModel model = new LampModel();
    model.setPart0(mLampMotion.globalDegree);

    positionList.add(model);

    lLayout.addView(newLayout);
    elements.add(newLayout);


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
    double radian = Math.atan2( -(141 / 2 - y), -(x -141 / 2));
    double lDegree = Math.toDegrees(radian) + 180;

    if (event.getAction() == MotionEvent.ACTION_MOVE) {

      switch (v.getId()) {
        case R.id.lamp_head_control:
//        setAngle((int) lDegree);
          lampHeadControl.setRotation((int) lDegree);
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
