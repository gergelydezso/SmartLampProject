package com.gergelydezso.smartlampsdk.sampleapp.motioncontrol;

import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;
import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MotionFragment extends Fragment implements OnClickListener, View.OnTouchListener,
        View.OnLongClickListener {

    private final static String TAG = "MotionFragment";
    public static final int MIN_SERVO1 = 35;
    public static final int MAX_SERVO1 = 179;
    public static final int MIN_SERVO2 = 1;
    public static final int MAX_SERVO2 = 165;
    public static final int MIN_SERVO5 = 15;
    public static final int MAX_SERVO5 = 172;
    public ImageView mImageCoordonate;
    private SmartLamp mApi;
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
    private ImageView imageControl;

    private AbsoluteLayout.LayoutParams paramsImageLampArm1;
    private AbsoluteLayout.LayoutParams paramsImageLampArm2;
    private AbsoluteLayout.LayoutParams paramsImageLampHead;

    private double RADIAN;

    private int[] mFixPoint;
    private int[] mMovingPointArm1;
    private int[] mMovingPointArm2;
    private int mRampDistance1;
    private int mDistanceArm1;
    private double startAngle;

    private AbsoluteLayout mainLayout;

    private int servo1Angle = 90;
    private int servo2Angle = MIN_SERVO2;
    private int servo5Angle = 180;
    private VerticalSeekBarView seekBar;
    private LinearLayout textVerticalLayout;
    private TextView text0;
    private TextView text15;
    private TextView text30;
    private TextView text45;
    private TextView text60;
    private TextView text75;
    private TextView text90;
    private TextView text105;
    private TextView text120;
    private TextView text135;
    private TextView text150;
    private TextView text165;
    private TextView text180;
    private TextView[] textArray;
    private int textColor = 0xFF05A5E1;
    ArrayList<TextView> textList;
    int[] resourceList = new int[]{R.drawable.head0, R.drawable.head15, R.drawable.head30,
            R.drawable.head45, R.drawable.head60, R.drawable.head75, R.drawable.head90,
            R.drawable.head105, R.drawable.head120, R.drawable.head135, R.drawable.head150,
            R.drawable.head165, R.drawable.head180};
    private LinearLayout playLayout;
    private Button playButton;
    private int indexList = 0;
    private int indexToRemove;
    private boolean readyToRemove = false;
    private int[] controlImageResourceList = new int[]{R.drawable.circle180, R.drawable.circle165,
            R.drawable.circle150, R.drawable.circle135, R.drawable.circle120, R.drawable.circle105,
            R.drawable.circle90, R.drawable.circle75, R.drawable.circle60, R.drawable.circle45,
            R.drawable.circle30, R.drawable.circle15, R.drawable.circle0};
    private Thread sendCommandThread;

    public MotionFragment() {
        positionList = new ArrayList<LampModel>();
        elements = new ArrayList<RelativeLayout>();
        overImages = new ArrayList<Bitmap>();

        mFixPoint = new int[2];
        mMovingPointArm1 = new int[2];
        mMovingPointArm2 = new int[2];

        startAngle = Math.toRadians(45);
        mDistanceArm1 = 200;
        mFixPoint[0] = 364;
        mFixPoint[1] = 505;

        mRampDistance1 = (int) (mDistanceArm1 * 0.7071);

        mMovingPointArm1[0] = mFixPoint[0] - mRampDistance1;
        mMovingPointArm1[1] = mFixPoint[1] - mRampDistance1;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_motion_control, container, false);
        mApi = SmartLampHolder.getSmartLamp();

        mainLayout = (AbsoluteLayout) rootView.findViewById(R.id.absolute_layout);
        mainLayout.setDrawingCacheEnabled(true);

        alphaIn = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_in);
        alphaOut = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_out);
        alphaOutLong = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha_out_long);

        overLayer = (ImageView) rootView.findViewById(R.id.over_layer);

        imageLampArm1 = (ImageView) rootView.findViewById(R.id.image_lamp_arm1);
        imageLampArm2 = (ImageView) rootView.findViewById(R.id.image_lamp_arm2);
        imageLampHead = (ImageView) rootView.findViewById(R.id.image_lamp_head);
        imageControl = (ImageView) rootView.findViewById(R.id.image_control);

        imageLampArm1.setOnTouchListener(this);
        imageLampArm2.setOnTouchListener(this);
        imageLampHead.setOnTouchListener(this);
        imageControl.setOnTouchListener(this);

        paramsImageLampArm1 = (AbsoluteLayout.LayoutParams) imageLampArm1.getLayoutParams();
        paramsImageLampArm2 = (AbsoluteLayout.LayoutParams) imageLampArm2.getLayoutParams();
        paramsImageLampHead = (AbsoluteLayout.LayoutParams) imageLampHead.getLayoutParams();

        hScroll = (HorizontalScrollView) rootView.findViewById(R.id.horizontalScrollView);
        lLayout = (LinearLayout) rootView.findViewById(R.id.horizontal_layout);

        mImageCoordonate = (ImageView) rootView.findViewById(R.id.imageView_coordinate);

        seekBar = (VerticalSeekBarView) rootView.findViewById(R.id.vertical_seekbar);
        seekBar.setProgress(90);
        seekBar.setOnSeekBarChangeListener(verticalSeekBarListener());

        textVerticalLayout = (LinearLayout) rootView.findViewById(R.id.text_vertical_layout);
        text0 = (TextView) rootView.findViewById(R.id.text0);
        text15 = (TextView) rootView.findViewById(R.id.text15);
        text30 = (TextView) rootView.findViewById(R.id.text30);
        text45 = (TextView) rootView.findViewById(R.id.text45);
        text60 = (TextView) rootView.findViewById(R.id.text60);
        text75 = (TextView) rootView.findViewById(R.id.text75);
        text90 = (TextView) rootView.findViewById(R.id.text90);
        text105 = (TextView) rootView.findViewById(R.id.text105);
        text120 = (TextView) rootView.findViewById(R.id.text120);
        text135 = (TextView) rootView.findViewById(R.id.text135);
        text150 = (TextView) rootView.findViewById(R.id.text150);
        text165 = (TextView) rootView.findViewById(R.id.text165);
        text180 = (TextView) rootView.findViewById(R.id.text180);
        textArray = new TextView[]{text0, text15, text30, text45, text60, text75, text90, text105, text120, text135, text150, text165, text180};
        textList = new ArrayList<TextView>(Arrays.asList(textArray));

        playLayout = (LinearLayout) rootView.findViewById(R.id.play_layout);
        playButton = (Button) rootView.findViewById(R.id.play_button);
        playButton.setOnClickListener(this);

        return rootView;
    }

    private SeekBar.OnSeekBarChangeListener verticalSeekBarListener() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                motionControl(progress);
                //seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                servo1Angle = seekBar.getProgress();
            }
        };
    }

    private void controlImage(int x) {
        int step = 50;
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setTextColor(Color.WHITE);
        }
        for (int index = 0; index < controlImageResourceList.length; index++) {
            if (x < 60) {
                imageControl.setImageResource(controlImageResourceList[0]);
                servo5Angle = MIN_SERVO5;
                break;
            }
            if (x > 660) {
                imageControl.setImageResource(controlImageResourceList[12]);
                servo5Angle = MAX_SERVO5;
                break;
            } else {
                int lowerLimit = index * step + 60;
                int upperLimit = (index + 1) * step + 60;
                if (x > lowerLimit && x <= upperLimit) {
                    imageControl.setImageResource(controlImageResourceList[index]);
                    setServo5Anglee(index);
                    break;
                }
            }
        }
    }

    private void setServo5Anglee(int index) {
        switch (index) {
            case 0:
                servo5Angle = MAX_SERVO5;
                break;
            case 1:
                servo5Angle = MAX_SERVO5;
                break;
            case 2:
                servo5Angle = 150;
                break;
            case 3:
                servo5Angle = 135;
                break;
            case 4:
                servo5Angle = 120;
                break;
            case 5:
                servo5Angle = 105;
                break;
            case 6:
                servo5Angle = 90;
                break;
            case 7:
                servo5Angle = 75;
                break;
            case 8:
                servo5Angle = 60;
                break;
            case 9:
                servo5Angle = 45;
                break;
            case 10:
                servo5Angle = 30;
                break;
            case 11:
                servo5Angle = MIN_SERVO5;
                break;
        }
    }

    private void motionControl(int progress) {
        int step = 15;
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setTextColor(Color.WHITE);
        }
        for (int index = 0; index < textList.size(); index++) {
            if (progress == 0) {
                textList.get(0).setTextColor(textColor);
                imageLampHead.setImageResource(resourceList[0]);
                break;
            } else {
                int lowerLimit = index * step;
                int upperLimit = (index + 1) * step;
                if (progress > lowerLimit && progress <= upperLimit) {
                    textList.get(index + 1).setTextColor(textColor);
                    imageLampHead.setImageResource(resourceList[index + 1]);
                    break;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.remove_icon) {
            lLayout.removeView(selectedElem);
            overImages.remove(indexToRemove);
            positionList.remove(indexToRemove);
            indexList--;
            if (overImages.isEmpty()) {
                playLayout.setVisibility(View.INVISIBLE);
                positionList.clear();
                elements.clear();
            }
            readyToRemove = false;
        }
        if (v.getId() == R.id.play_button) {
            play();
        }
    }

    public void play() {
//        if (positionList.size()>0){
//            for (int i=0;i<positionList.size();i++){
//                Log.d(TAG ,String.valueOf(positionList.get(i).getServo1Angle()));
//                Log.d(TAG ,String.valueOf(positionList.get(i).getServo2Angle()));
//                Log.d(TAG ,String.valueOf(positionList.get(i).getServo5Angle()));
//            }
//        }
        if (!overImages.isEmpty()) {
            playLayout.setVisibility(View.INVISIBLE);
            mainLayout.setVisibility(View.INVISIBLE);
            overLayer.setVisibility(View.VISIBLE);

            hScroll.post(new Runnable() {
                public void run() {
                    hScroll.fullScroll(ScrollView.FOCUS_LEFT);
                }
            });

            final Handler handler = new Handler();

            sendCommandThread = new Thread() {
                int i = 0;

                @Override
                public void run() {
                    overLayer.setImageBitmap(overImages.get(i));
                    overLayer.startAnimation(alphaIn);
                    lLayout.removeView(elements.get(i));
                    handler.postDelayed(this, 2000);
                    sendCommand(i);
                    i++;

                    if (i == overImages.size()) {
                        handler.removeCallbacks(this);
                        overImages.clear();
                        overLayer.setVisibility(View.INVISIBLE);
                        lLayout.removeAllViews();
                        elements.clear();
                        positionList.clear();
                        indexList = 0;

                        mainLayout.setVisibility(View.VISIBLE);
                    }

                }
            };

            handler.post(sendCommandThread);
        }

    }

    public void sendCommand(int i) {

        if (mApi != null) {
            Log.d("MotionFragment", "command value 1: " + positionList.get(i).getServo1Angle());
            mApi.adjustServoComponent(ServoMotorEntity.SERVO1, positionList.get(i).getServo1Angle(), new CommandCallback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                }

                @Override
                public void onResult(String state) {
                }
            });

            try {
                sendCommandThread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d("MotionFragment", "command value 2: " + positionList.get(i).getServo2Angle());
            mApi.adjustServoComponent(ServoMotorEntity.SERVO2, positionList.get(i).getServo2Angle(), new CommandCallback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                }

                @Override
                public void onResult(String state) {
                }
            });


            try {
                sendCommandThread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d("MotionFragment", "command value 5: " + positionList.get(i).getServo5Angle());

            mApi.adjustServoComponent(ServoMotorEntity.SERVO5, positionList.get(i).getServo5Angle(), new CommandCallback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                }

                @Override
                public void onResult(String state) {
                }
            });
        }
    }


    public void addListElement() {

        seekBar.setVisibility(View.INVISIBLE);
        textVerticalLayout.setVisibility(View.INVISIBLE);
        mainLayout.setDrawingCacheEnabled(true);

        Bitmap resize = Bitmap.createScaledBitmap(mainLayout.getDrawingCache(), 200, 230, true);

        Bitmap fullSize = Bitmap.createBitmap(mainLayout.getDrawingCache());
        overImages.add(fullSize);
        RelativeLayout newLayout = (RelativeLayout) View.inflate(getActivity(), R.layout.list_elem, null);
        newLayout.setOnLongClickListener(this);
        ImageView removeIcon = (ImageView) newLayout.findViewById(R.id.remove_icon);
        removeIcon.setOnClickListener(this);
        ImageView image = (ImageView) newLayout.findViewById(R.id.list_image);
        image.setImageBitmap(resize);

        if (playLayout.getVisibility() == View.INVISIBLE) {
            playLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        lLayout.addView(newLayout, indexList, layoutParams);
        elements.add(newLayout);

        hScroll.post(new Runnable() {
            public void run() {
                hScroll.fullScroll(ScrollView.FOCUS_RIGHT);
            }
        });

        mainLayout.setDrawingCacheEnabled(false);
        seekBar.setVisibility(View.VISIBLE);
        textVerticalLayout.setVisibility(View.VISIBLE);

        LampModel model = new LampModel();
        servo1Angle = seekBar.getProgress();
        if (servo1Angle < MIN_SERVO1) {
            servo1Angle = MIN_SERVO1;
        } else if (servo1Angle > MAX_SERVO1) {
            servo1Angle = MAX_SERVO1;
        }
        model.setServo1Angle(servo1Angle);
        model.setServo2Angle(servo2Angle);
        model.setServo5Angle(servo5Angle);
        positionList.add(indexList, model);
        indexList++;

    }

    public void setPositions(View view) {


        switch (view.getId()) {

            case R.id.image_lamp_arm1:

                paramsImageLampArm2.x = mMovingPointArm1[0] - imageLampArm2.getWidth() / 2;
                paramsImageLampArm2.y = mMovingPointArm1[1] - imageLampArm2.getHeight();
                imageLampArm2.setLayoutParams(paramsImageLampArm2);

                paramsImageLampHead.x = mMovingPointArm2[0];
                paramsImageLampHead.y = mMovingPointArm2[1] - 70;
                imageLampHead.setLayoutParams(paramsImageLampHead);

                break;
            case R.id.image_lamp_arm2:

                paramsImageLampHead.x = mMovingPointArm2[0];
                paramsImageLampHead.y = mMovingPointArm2[1] - 80;
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
                    Log.d(TAG + "degree", String.valueOf(degree));
                    if ((degree > 0) && (degree < 135)) {
                        imageLampHead.setRotation((int) (degree - 90));
                        servo2Angle = (int) (115 - degree);
                        if (servo2Angle < MIN_SERVO2) {
                            servo2Angle = MIN_SERVO2;
                        } else if (servo2Angle > MAX_SERVO2) {
                            servo2Angle = MAX_SERVO2;
                        }
                    }
                    break;
                case R.id.image_control:
                    controlImage(x);
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
        } else {
            return (degree + 360);
        }
    }

    @Override
    public boolean onLongClick(View v) {

        if (!readyToRemove) {
            readyToRemove = true;
            ImageView removeIcon = (ImageView) v.findViewById(R.id.remove_icon);
            removeIcon.setVisibility(View.VISIBLE);
            ImageView image = (ImageView) v.findViewById(R.id.list_image);
            image.setAlpha(0.2f);
            selectedElem = (RelativeLayout) v;
            indexToRemove = lLayout.indexOfChild(v);
        } else {
            ImageView oldRemoveIcon = (ImageView) lLayout.getChildAt(indexToRemove).findViewById(R.id.remove_icon);
            oldRemoveIcon.setVisibility(View.INVISIBLE);
            ImageView oldImage = (ImageView) lLayout.getChildAt(indexToRemove).findViewById(R.id.list_image);
            oldImage.setAlpha(1.0f);

            ImageView removeIcon = (ImageView) v.findViewById(R.id.remove_icon);
            ImageView image = (ImageView) v.findViewById(R.id.list_image);
            image.setAlpha(0.2f);
            removeIcon.setVisibility(View.VISIBLE);
            selectedElem = (RelativeLayout) v;
            indexToRemove = lLayout.indexOfChild(v);
        }
        return true;
    }
}
