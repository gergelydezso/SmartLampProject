package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

import android.media.audiofx.Visualizer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author robert.fejer
 */
public class VisualizerCapturedDataHandler implements Visualizer.OnDataCaptureListener {

    private static VisualizerCapturedDataHandler instance = null;
    private List<VisualizerDataHandler> visualizerDataHandlers;

    private VisualizerCapturedDataHandler() {
        visualizerDataHandlers = new ArrayList<VisualizerDataHandler>();
    }

    public static VisualizerCapturedDataHandler getInstance() {
        if (instance == null) {
            instance = new VisualizerCapturedDataHandler();
        }

        return instance;
    }

    public void registerDataHandler(VisualizerDataHandler visualizerDataHandler) {
        if (visualizerDataHandlers != null) {
            visualizerDataHandlers.add(visualizerDataHandler);
        }
        else {
            throw new IllegalArgumentException("Parameter cannot be null!");
        }
    }

    @Override
    public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int i) {
        notifyDataHandlers(bytes, CapturedDataType.WAVE_FORM_DATA);
    }

    @Override
    public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int i) {
        notifyDataHandlers(bytes, CapturedDataType.FFT_DATA);
    }

    private void notifyDataHandlers(byte[] bytes, CapturedDataType capturedDataType) {
        for (VisualizerDataHandler visualizerDataHandler : visualizerDataHandlers) {
            if (CapturedDataType.WAVE_FORM_DATA.equals(capturedDataType)) {
                visualizerDataHandler.handleWaveFormData(bytes);
            }
            else {
                visualizerDataHandler.handleFFTData(bytes);
            }
        }
    }
}
