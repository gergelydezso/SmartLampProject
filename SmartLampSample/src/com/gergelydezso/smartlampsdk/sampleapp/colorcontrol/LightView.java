package com.gergelydezso.smartlampsdk.sampleapp.colorcontrol;

/**
 * Created by gabriela.banica on 11/12/13.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gabriela.banica on 11/12/13.
 */
// TODO - CODE REVIEW - andrei|Nov 14, 2013 - Please organize the imports.
public class LightView extends View {

  private Paint lightPaint = new Paint();
  private RadialGradient mGradient;

  public LightView(Context context) {
    super(context);
  }

  public LightView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LightView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawLight(canvas);
  }

  private void drawLight(Canvas canvas) {
    int width = this.getWidth();
    int height = this.getHeight();

    // int[] Colors = {lightPaint.getColor(), 0x99333333};
    // float[] ColorPosition = {0.60f, 0.99f};

    mGradient = new RadialGradient(width / 2, height / 2, 100, lightPaint.getColor(), 0x99333333, Shader.TileMode.CLAMP);

    lightPaint.setDither(true);
    lightPaint.setShader(mGradient);
    canvas.drawCircle(width / 2, height / 2, 60, lightPaint);
  }

  public void setColor(int color) {
    lightPaint.setColor(color);
    invalidate();
  }
}
