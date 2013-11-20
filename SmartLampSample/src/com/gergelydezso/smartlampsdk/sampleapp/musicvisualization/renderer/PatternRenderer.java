package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer;

import android.graphics.Rect;

/**
 * Concrete renderers should implement this interface.
 *
 * @author robert.fejer
 */
public interface PatternRenderer {

    /**
     * Creates a series of points dependent on the array of bytes and the rect.
     * These can be used to display a form.
     *
     * @param bytes the bytes captured by the visualizer
     * @param rect holds the size of the canvas on which points will be drawn
     * @return Returns the calculated points
     */
    float[] createPatternPoints(byte[] bytes, Rect rect);
}
