package com.gergelydezso.smartlampsdk.sampleapp.motioncontrol;

import com.gergelydezso.smartlampsdk.sampleapp.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LampMotion extends View {

  enum MovingArm {
    ARM1, ARM2, ARM3, NONE
  }

  @SuppressWarnings("unused")
  private final static String TAG = "LampMotion";
  private MovingArm mActiveArm;
  private int mDistanceArm1;
  private int mDistanceArm2;
  private int mDistanceArm3;
  private int mRampDistance1;
  private int mRampDistance2;
  @SuppressWarnings("unused")
  private int mRampDistance3;
  private int[] mFixPoint;
  private int[] mMovingPointArm1;
  private int[] mMovingPointArm2;
  private int[] mMovingPointArm3;
  private int[] mMovingPointArm3_1;
  private int[] mMovingPointArm3_2_1;
  private int[] mMovingPointArm3_2_2;
  private Paint mLinePaint;
  private Paint mLinePaint0;
  private Paint mLinePaint1;
  private Paint mLinePaint2;
  private Paint mLinePaint3;
  private Paint mLinePaint4;
  private Paint mLinePaintBold;
  private Paint mLinePaintBoldPlus;
  private Paint mCireclePaint;
  private Paint mRectanglePaint;
  private Paint mRectangleBalck;
  private Paint mCirclePathPaint;
  private Paint mBaseLinePaint;
  private Rect mRect1;
  private Rect mRect2;
  private Rect mRect3;
  @SuppressWarnings("unused")
  private boolean mTest;
  private MotionFragment mMotionFragment;

  public LampMotion(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public void setOwnerObejct(MotionFragment owner) {
    this.mMotionFragment = owner;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(720, 600);
  }

  public void init() {

    mBaseLinePaint = new Paint();
    mBaseLinePaint.setAntiAlias(true);
    mBaseLinePaint.setColor(Color.DKGRAY);
    mBaseLinePaint.setStrokeWidth(5);
    mBaseLinePaint.setStyle(Style.STROKE);
    mBaseLinePaint.setStrokeJoin(Paint.Join.ROUND);
    mBaseLinePaint.setStrokeCap(Paint.Cap.ROUND);
    mBaseLinePaint.setPathEffect(new DashPathEffect(new float[] { 10, 10 }, 0));

    mCirclePathPaint = new Paint();
    mCirclePathPaint.setAntiAlias(true);
    mCirclePathPaint.setColor(Color.BLUE);
    mCirclePathPaint.setStrokeWidth(1);
    mCirclePathPaint.setStyle(Style.STROKE);
    mCirclePathPaint.setStrokeJoin(Paint.Join.ROUND);
    mCirclePathPaint.setStrokeCap(Paint.Cap.ROUND);
    mCirclePathPaint.setPathEffect(new DashPathEffect(new float[] { 10, 10 }, 0));

    mRectangleBalck = new Paint();
    mRectangleBalck.setColor(Color.DKGRAY);
    mRectangleBalck.setStyle(Style.FILL);

    mRectanglePaint = new Paint();
    mRectanglePaint.setColor(Color.BLUE);
    mRectanglePaint.setAlpha(40);
    mRectanglePaint.setStyle(Style.FILL);

    mLinePaintBold = new Paint();
    mLinePaintBold.setColor(Color.DKGRAY);
    mLinePaintBold.setStrokeWidth(40);
    mLinePaintBold.setStyle(Style.FILL);

    mLinePaintBoldPlus = new Paint();
    mLinePaintBoldPlus.setColor(Color.DKGRAY);
    mLinePaintBoldPlus.setStrokeWidth(60);
    mLinePaintBoldPlus.setStyle(Style.FILL);

    mLinePaint0 = new Paint();
    mLinePaint0.setColor(Color.DKGRAY);
    mLinePaint0.setStrokeWidth(20);
    mLinePaint0.setStyle(Style.FILL);
    mLinePaint0.setAntiAlias(true);

    mLinePaint1 = new Paint();
    mLinePaint1.setColor(Color.DKGRAY);
    mLinePaint1.setStrokeWidth(20);
    mLinePaint1.setStyle(Style.FILL);
    mLinePaint1.setAntiAlias(true);

    mLinePaint2 = new Paint();
    mLinePaint2.setColor(Color.DKGRAY);
    mLinePaint2.setStrokeWidth(20);
    mLinePaint2.setStyle(Style.FILL);
    mLinePaint2.setAntiAlias(true);

    mLinePaint = new Paint();
    mLinePaint.setColor(Color.DKGRAY);
    mLinePaint.setStrokeWidth(20);
    mLinePaint.setStyle(Style.FILL);
    mLinePaint.setAntiAlias(true);

    mLinePaint3 = new Paint();
    mLinePaint3.setColor(Color.DKGRAY);
    mLinePaint3.setStrokeWidth(20);
    mLinePaint3.setStyle(Style.FILL);
    mLinePaint3.setAntiAlias(true);

    mLinePaint4 = new Paint();
    mLinePaint4.setColor(Color.DKGRAY);
    mLinePaint4.setStrokeWidth(60);
    mLinePaint4.setStyle(Style.FILL);
    mLinePaint4.setAntiAlias(true);

    mCireclePaint = new Paint();
    mCireclePaint.setColor(Color.rgb(228, 162, 21));
    // mCireclePaint.setColor(Color.BLUE);
    mCireclePaint.setAntiAlias(true);

    mActiveArm = MovingArm.NONE;

    mDistanceArm1 = 200;
    mDistanceArm2 = 200;
    mDistanceArm3 = 50;
    mRampDistance1 = (int) (mDistanceArm1 * 0.7071);
    mRampDistance2 = (int) (mDistanceArm2 * 0.7071);
    mRampDistance3 = (int) (mDistanceArm3 * 0.7071);

    mFixPoint = new int[2];
    mMovingPointArm1 = new int[2];
    mMovingPointArm2 = new int[2];
    mMovingPointArm3 = new int[2];
    mMovingPointArm3_1 = new int[2];
    mMovingPointArm3_2_1 = new int[2];
    mMovingPointArm3_2_2 = new int[2];

    mFixPoint[0] = 350;
    mFixPoint[1] = 500;
    mMovingPointArm1[0] = mFixPoint[0] - mRampDistance1;
    mMovingPointArm1[1] = mFixPoint[1] - mRampDistance1;
    mMovingPointArm2[0] = mMovingPointArm1[0] + mRampDistance2;
    mMovingPointArm2[1] = mMovingPointArm1[1] - mRampDistance2;
    mMovingPointArm3[0] = mMovingPointArm2[0] + 60;
    mMovingPointArm3[1] = mMovingPointArm2[1];
    mMovingPointArm3_1[0] = mMovingPointArm3[0] + 50;
    mMovingPointArm3_1[1] = mMovingPointArm3[1];
    mMovingPointArm3_2_1[0] = mMovingPointArm3[0] - 30;
    mMovingPointArm3_2_1[1] = mMovingPointArm3[1] + 50;
    mMovingPointArm3_2_2[0] = mMovingPointArm3_2_1[0] + 110;
    mMovingPointArm3_2_2[1] = mMovingPointArm3_2_1[1];

    mRect1 = new Rect(mMovingPointArm1[0], mMovingPointArm1[1], mFixPoint[0], mFixPoint[1]);
    mRect2 = new Rect(mMovingPointArm1[0], mMovingPointArm1[1] - mRampDistance2, mMovingPointArm2[0],
        mMovingPointArm2[1] + mRampDistance2);
    mRect3 = new Rect(mMovingPointArm2[0], mMovingPointArm2[1] - 30, mMovingPointArm2[0] + 150,
        mMovingPointArm2[1] + 80);

  }

  public void setActivePart(int part) {
    switch (part) {
    case 0:
      mLinePaint0.setColor(Color.rgb(118, 155, 47));
      mLinePaint1.setColor(Color.DKGRAY);
      mLinePaint2.setColor(Color.DKGRAY);
      mLinePaint3.setColor(Color.DKGRAY);
      mLinePaint4.setColor(Color.DKGRAY);
      invalidate();
      break;
    case 4:
      mLinePaint4.setColor(Color.rgb(118, 155, 47));
      mLinePaint0.setColor(Color.DKGRAY);
      mLinePaint1.setColor(Color.DKGRAY);
      mLinePaint2.setColor(Color.DKGRAY);
      mLinePaint3.setColor(Color.DKGRAY);
      invalidate();
      break;

    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    int x = (int) event.getX();
    int y = (int) event.getY();

    double radian = Math.atan2(x - 720 / 2, 1280 / 2 - y);

    switch (event.getAction()) {

    case MotionEvent.ACTION_DOWN:
      if (mRect1.contains(x, y)) {
        mMotionFragment.mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_z);
        mActiveArm = MovingArm.ARM1;
        mLinePaint1.setColor(Color.rgb(118, 155, 47));
        mLinePaint0.setColor(Color.DKGRAY);
        mLinePaint2.setColor(Color.DKGRAY);
        mLinePaint3.setColor(Color.DKGRAY);
        mLinePaint4.setColor(Color.DKGRAY);
      }
      else if (mRect2.contains(x, y)) {
        mMotionFragment.mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_z);
        mActiveArm = MovingArm.ARM2;
        mLinePaint2.setColor(Color.rgb(118, 155, 47));
        mLinePaint0.setColor(Color.DKGRAY);
        mLinePaint1.setColor(Color.DKGRAY);
        mLinePaint3.setColor(Color.DKGRAY);
        mLinePaint4.setColor(Color.DKGRAY);
      }
      else if (mRect3.contains(x, y)) {
        mMotionFragment.mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_z);
        mActiveArm = MovingArm.ARM3;
        mLinePaint3.setColor(Color.rgb(118, 155, 47));
        mLinePaint0.setColor(Color.DKGRAY);
        mLinePaint1.setColor(Color.DKGRAY);
        mLinePaint2.setColor(Color.DKGRAY);
        mLinePaint4.setColor(Color.DKGRAY);
      }
      else
        mActiveArm = MovingArm.NONE;
      break;

    case MotionEvent.ACTION_MOVE:

      switch (mActiveArm) {
      case ARM1:
        mMovingPointArm1 = calculatePoint(mFixPoint, 200, radian);
        mRect1.set(mMovingPointArm1[0], mMovingPointArm1[1], mFixPoint[0], mFixPoint[1]);
        mRect2.set(mMovingPointArm1[0], mMovingPointArm1[1] - mRampDistance2, mMovingPointArm2[0], mMovingPointArm2[1]
            + mRampDistance2);
        mMovingPointArm2 = calculatePoint(mMovingPointArm1, 200, Math.toRadians(45));
        mRect3.set(mMovingPointArm2[0], mMovingPointArm2[1] - 30, mMovingPointArm2[0] + 150, mMovingPointArm2[1] + 80);
        mMovingPointArm3[0] = mMovingPointArm2[0] + 60;
        mMovingPointArm3[1] = mMovingPointArm2[1];
        mMovingPointArm3_1[0] = mMovingPointArm3[0] + 50;
        mMovingPointArm3_1[1] = mMovingPointArm3[1];

        mMovingPointArm3_2_1[0] = mMovingPointArm3[0] - 30;
        mMovingPointArm3_2_1[1] = mMovingPointArm3[1] + 50;
        mMovingPointArm3_2_2[0] = mMovingPointArm3_2_1[0] + 110;
        mMovingPointArm3_2_2[1] = mMovingPointArm3_2_1[1];

        break;
      case ARM2:
        mMovingPointArm2 = calculatePoint(mMovingPointArm1, 200, Math.toRadians(45) + radian);
        mRect2.set(mMovingPointArm1[0], mMovingPointArm1[1], mMovingPointArm2[0], mMovingPointArm2[1]);
        mRect3.set(mMovingPointArm2[0], mMovingPointArm2[1] - 30, mMovingPointArm2[0] + 150, mMovingPointArm2[1] + 80);
        mMovingPointArm3[0] = mMovingPointArm2[0] + 60;
        mMovingPointArm3[1] = mMovingPointArm2[1];
        mMovingPointArm3_1[0] = mMovingPointArm3[0] + 50;
        mMovingPointArm3_1[1] = mMovingPointArm3[1];

        mMovingPointArm3_2_1[0] = mMovingPointArm3[0] - 30;
        mMovingPointArm3_2_1[1] = mMovingPointArm3[1] + 50;
        mMovingPointArm3_2_2[0] = mMovingPointArm3_2_1[0] + 110;
        mMovingPointArm3_2_2[1] = mMovingPointArm3_2_1[1];

        break;
      case ARM3:
        mMovingPointArm3 = calculatePoint(mMovingPointArm2, 60, Math.toRadians(90) + radian);
        mMovingPointArm3_1 = calculatePoint(mMovingPointArm3, 50, Math.toRadians(90) + radian);
        mRect3.set(mMovingPointArm2[0], mMovingPointArm2[1] - 30, mMovingPointArm2[0] + 150, mMovingPointArm2[1] + 80);
        mMovingPointArm3_2_1[0] = mMovingPointArm3[0] - 30;
        mMovingPointArm3_2_1[1] = mMovingPointArm3[1] + 50;
        mMovingPointArm3_2_2 = calculatePoint(mMovingPointArm3_2_1, 110, Math.toRadians(90) + radian);
        break;
      case NONE:
        break;

      }
      break;
    }
    invalidate();
    // setDrawingCacheEnabled(false);

    return true;
  }

  public int[] calculatePoint(int[] fixPoint, int distance, double radian) {

    int[] Point = new int[2];

    Point[0] = fixPoint[0] + (int) (Math.sin(radian) * distance);
    Point[1] = fixPoint[1] - (int) (Math.cos(radian) * distance);

    return Point;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawLine(350, 500, 350, 570, mLinePaint0);
    canvas.drawLine(280, 580, 420, 580, mLinePaintBold);

    canvas.drawCircle(mFixPoint[0], mFixPoint[1], 200, mCirclePathPaint);
    canvas.drawCircle(mMovingPointArm1[0], mMovingPointArm1[1], 200, mCirclePathPaint);
    canvas.drawCircle(mMovingPointArm3[0], mMovingPointArm3[1], 60, mCirclePathPaint);

    // canvas.drawRect(mRect1, mRectanglePaint);
    // canvas.drawRect(mRect2, mRectanglePaint);
    // canvas.drawRect(mRect3, mRectanglePaint);

    canvas.drawLine(mFixPoint[0], mFixPoint[1], mMovingPointArm1[0], mMovingPointArm1[1], mLinePaint1);
    canvas.drawLine(mMovingPointArm1[0], mMovingPointArm1[1], mMovingPointArm2[0], mMovingPointArm2[1], mLinePaint2);
    canvas.drawLine(mMovingPointArm2[0], mMovingPointArm2[1], mMovingPointArm3[0], mMovingPointArm3[1], mLinePaint3);
    canvas.drawLine(mMovingPointArm3[0], mMovingPointArm3[1], mMovingPointArm3_1[0], mMovingPointArm3_1[1], mLinePaint4);
    canvas.drawLine(mMovingPointArm3_2_1[0], mMovingPointArm3_2_1[1], mMovingPointArm3_2_2[0], mMovingPointArm3_2_2[1],
        mLinePaintBold);

    // canvas.drawLine(mMovingPointArm2[0] + 60, mMovingPointArm2[1], mMovingPointArm3[0] + 110, mMovingPointArm3[1],
    // mLinePaintBold);
    // canvas.drawLine(mMovingPointArm2[0] + 10, mMovingPointArm2[1] + 30, mMovingPointArm3[0] + 160,
    // mMovingPointArm3[1],
    // mLinePaintBold);

    canvas.drawLine(0, 600, 720, 600, mBaseLinePaint);

    canvas.drawCircle(mMovingPointArm1[0], mMovingPointArm1[1], 20, mCireclePaint);
    canvas.drawCircle(mMovingPointArm2[0], mMovingPointArm2[1], 20, mCireclePaint);
    canvas.drawCircle(mFixPoint[0], mFixPoint[1], 20, mCireclePaint);

    // canvas.drawRect(mMovingPointArm3[0], mMovingPointArm3[1] - 30, mMovingPointArm3[0] + 50, mMovingPointArm3[1] +
    // 30,
    // mRectangleBalck);
    // canvas.drawRect(mMovingPointArm3[0] - 40, mMovingPointArm3[1] + 30, mMovingPointArm3[0] + 50 + 40,
    // mMovingPointArm3[1] + 80, mRectangleBalck);

    // setDrawingCacheEnabled(true);

  }
}
