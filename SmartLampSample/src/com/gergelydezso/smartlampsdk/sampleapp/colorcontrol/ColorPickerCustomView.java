package com.gergelydezso.smartlampsdk.sampleapp.colorcontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ColorPickerCustomView extends View {

  private int mX;
  private int mY;
  private int mColor;
  private Paint mPaintStroke = new Paint();
  private Paint mPaintCircle = new Paint();

  public ColorPickerCustomView(Context context, AttributeSet attrs) {
    super(context, attrs);

    mPaintStroke.setStyle(Paint.Style.STROKE);
    mPaintStroke.setStrokeWidth(10);
    mPaintStroke.setColor(Color.GRAY);
    mPaintStroke.setAntiAlias(true);

    mPaintCircle.setAntiAlias(true);

    setLayerType(View.LAYER_TYPE_HARDWARE, null);

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(720, 407);
  }

  public void setValues(int x, int y, int color) {
    this.mX = x;
    this.mY = y;
    this.mColor = color;
    this.invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    mPaintCircle.setColor(mColor);

    canvas.drawCircle(mX, mY, 50, mPaintStroke);
    canvas.drawCircle(mX, mY, 50, mPaintCircle);

    // Log.d("ColorPicker", "circleeeee");
  }

}
