package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.audiofx.Visualizer;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author robert.fejer
 */
public class VisualizerView extends View {

    private static final String TAG = VisualizerView.class.getSimpleName();
    private Visualizer visualizer;
    private Canvas     canvas;
    private Bitmap     bitmap;
    private Paint      paint;
    private float modulation = 0;
    private float aggresive  = 0.33f;
    private byte[] waveFormBytes;
    private Rect rect = new Rect();

    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Initialize the visualizer.
     *
     * @param audioSessionId required by the {@link android.media.audiofx.Visualizer}. Specifies the MediaPlayer / audio session to attach to.
     */
    public void init(int audioSessionId) {
        visualizer = new Visualizer(audioSessionId);
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        createAndSetCaptureListener();

        paint = new Paint();
        paint.setStrokeWidth(3f);
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(255, 222, 92, 143));
    }

    public void startVisualization() {
        visualizer.setEnabled(true);
    }

    public void stopVisualization() {
        visualizer.setEnabled(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rect.set(0, 0, getWidth(), getHeight());

        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        }

        if (this.canvas == null) {
            this.canvas = new Canvas(bitmap);
        }

        if (waveFormBytes != null) {
            render(waveFormBytes, rect);
        }

        canvas.drawBitmap(bitmap, new Matrix(), null);
    }

    private void createAndSetCaptureListener() {
        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                handleWaveFormDataCapture(bytes);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                handleFFTDataCapture(bytes);
            }
        };

        visualizer.setDataCaptureListener(captureListener, Visualizer.getMaxCaptureRate() / 2, true, true);
    }

    private void handleWaveFormDataCapture(byte[] bytes) {
        waveFormBytes = bytes;
        int half = bytes.length / 2;

        int rectWidth = 300;
        int rectHeight = 300;

        if (canvas != null) {
            float[] points = new float[bytes.length * 4];
            for (int currentIndex = 0; currentIndex < bytes.length - 1; ++currentIndex) {
                points[currentIndex * 4] = rectWidth * currentIndex / (bytes.length - 1);
                points[currentIndex * 4 + 1] =
                        rectHeight / 2 + ((byte) (bytes[currentIndex] + 128)) * (rectHeight / 3) / 128;
                points[currentIndex * 4 + 2] = rectWidth * (currentIndex + 1) / (bytes.length - 1);
                points[currentIndex * 4 + 3] =
                        rectHeight / 2 + ((byte) (bytes[currentIndex + 1] + 128)) * (rectHeight / 3) / 128;
                /*points[currentIndex] += 10;
                points[currentIndex + 1] = convertPCMValueToDP(bytes[currentIndex]);
                points[currentIndex] += 10;
                points[currentIndex + 1] = convertPCMValueToDP(bytes[currentIndex]);*/
            }

            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            canvas.drawPoints(points, paint);
            invalidate();
        }
    }

    private void handleFFTDataCapture(byte[] bytes) {

    }

    private float convertPCMValueToDP(byte pcmValue) {
        float ret = 0f;

        float pcmPercent = 100 * pcmValue / Byte.MAX_VALUE;
        if (pcmValue >= 0) {
            ret = (300 / 2) * (pcmPercent / 100);
        }
        else {
            ret = (-300 / 2) * (pcmPercent / 100);
        }

        return ret;
    }

    private void render(byte[] bytes, Rect rect) {
        float[] points = new float[bytes.length * 4];
        for (int i = 0; i < bytes.length - 1; i++) {
            float[] cartPoint = {
                    (float) i / (bytes.length - 1),
                    rect.height() / 2 + ((byte) (bytes[i] + 128)) * (rect.height() / 2) / 128
            };

            float[] polarPoint = toPolar(cartPoint, rect);
            points[i * 4] = polarPoint[0];
            points[i * 4 + 1] = polarPoint[1];

            float[] cartPoint2 = {
                    (float) (i + 1) / (bytes.length - 1),
                    rect.height() / 2 + ((byte) (bytes[i + 1] + 128)) * (rect.height() / 2) / 128
            };

            float[] polarPoint2 = toPolar(cartPoint2, rect);
            points[i * 4 + 2] = polarPoint2[0];
            points[i * 4 + 3] = polarPoint2[1];
        }

        canvas.drawLines(points, paint);

        // Controls the pulsing rate
        modulation += 0.04;
    }

    private float[] toPolar(float[] cartesian, Rect rect) {
        double cX = rect.width() / 2;
        double cY = rect.height() / 2;
        double angle = (cartesian[0]) * 2 * Math.PI;
        double radius =
                ((rect.width() / 2) * (1 - aggresive) + aggresive * cartesian[1] / 2) * (1.2 + Math.sin(modulation))
                        / 2.2;
        float[] out = {
                (float) (cX + radius * Math.sin(angle)),
                (float) (cY + radius * Math.cos(angle))
        };
        return out;
    }
}