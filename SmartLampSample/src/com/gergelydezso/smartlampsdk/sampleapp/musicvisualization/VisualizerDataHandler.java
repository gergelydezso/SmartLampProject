package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

/**
 *
 *
 * @author robert.fejer
 */
public interface VisualizerDataHandler {

    void handleWaveFormData(byte[] bytes);

    void handleFFTData(byte[] bytes);
}
