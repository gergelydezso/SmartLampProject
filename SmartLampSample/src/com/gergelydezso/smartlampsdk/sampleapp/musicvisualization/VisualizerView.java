package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

import android.content.Context;
import android.media.audiofx.Visualizer;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author robert.fejer
 */
public class VisualizerView extends View {
    private static final String TAG = VisualizerView.class.getSimpleName();

    private Visualizer visualizer;

    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Initialize the visualizer.
     * @param audioSessionId required by the {@link android.media.audiofx.Visualizer}. Specifies the MediaPlayer / audio session to attach to.
     */
    public void init(int audioSessionId) {
        visualizer = new Visualizer(audioSessionId);
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        createAndSetCaptureListener();
    }

    private void createAndSetCaptureListener() {
        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                handleWaveformDataCapture(bytes);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                handleFFTDataCapture(bytes);
            }
        };

        visualizer.setDataCaptureListener(captureListener, Visualizer.getMaxCaptureRate() / 2, true, true);
    }

    private void handleWaveformDataCapture(byte[] bytes) {

    }

    private void handleFFTDataCapture(byte[] bytes) {

    }
}
