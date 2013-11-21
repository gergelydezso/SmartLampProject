package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.util.AttributeSet;
import android.view.View;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.lamp.LampMusicVisualizer;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer.CirclePatternRenderer;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer.PatternRenderer;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer.PatternRendererUtils;

/**
 *
 *
 * @author robert.fejer
 */
public class VisualizerView extends View implements VisualizerDataHandler {

    private static final String TAG = VisualizerView.class.getSimpleName();

    private Visualizer visualizer;
    private Canvas     canvas;
    private Bitmap     bitmap;
    private Paint      paint;
    private float colorCounter = 0;
    private byte[] waveFormBytes;
    private Rect            rect            = new Rect();
    private Paint           fadePaint       = new Paint();
    private PatternRenderer patternRenderer = new CirclePatternRenderer();
    private LampMusicVisualizer lampMusicVisualizer;

    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Initialize the visualizer.
     *
     * @param audioSessionId required by the {@link android.media.audiofx.Visualizer}. Specifies the MediaPlayer / audio session to attach to.
     */
    public void init(MediaPlayer mediaPlayer) {
        visualizer = new Visualizer(mediaPlayer.getAudioSessionId());
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        createAndSetCaptureListener();

        paint = new Paint();
        paint.setStrokeWidth(3f);
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(255, 222, 92, 143));

        fadePaint.setColor(Color.argb(238, 255, 255, 255));
        fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));

        lampMusicVisualizer = new LampMusicVisualizer(mediaPlayer);
    }

    public void startVisualization() {
        visualizer.setEnabled(true);
    }

    public void stopVisualization() {
        visualizer.setEnabled(false);
    }

    @Override
    public void handleWaveFormData(byte[] bytes) {
        waveFormBytes = bytes;
        invalidate();
    }

    @Override
    public void handleFFTData(byte[] bytes) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        changeColorAndSendToLamp();

        rect.set(0, 0, getWidth(), getHeight());

        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        }

        if (this.canvas == null) {
            this.canvas = new Canvas(bitmap);
        }

        if (waveFormBytes != null) {
            float[] points = patternRenderer.createPatternPoints(waveFormBytes, rect);
            canvas.drawLines(points, paint);
        }

        this.canvas.drawPaint(fadePaint);

        canvas.drawBitmap(bitmap, new Matrix(), null);
    }

    private void createAndSetCaptureListener() {
        VisualizerCapturedDataHandler visualizerCapturedDataHandler = VisualizerCapturedDataHandler.getInstance();
        visualizer.setDataCaptureListener(visualizerCapturedDataHandler, Visualizer.getMaxCaptureRate() / 2, true, true);
        visualizerCapturedDataHandler.registerDataHandler(this);
    }

    private void changeColorAndSendToLamp() {
        if (paint != null) {
            int[] colorValues = PatternRendererUtils.generateRandomColor(); //PatternRendererUtils.calculateColorCodes(colorCounter);
            paint.setColor(Color.argb(128, colorValues[0], colorValues[1], colorValues[2]));
            colorCounter += 0.03;
            lampMusicVisualizer.setActualColor(colorValues);
        }
    }

    private void sendMotionCommandsToLamp(byte[] bytes) {
        //
    }
}
