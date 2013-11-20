package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer;

import android.graphics.Rect;

/**
 * Creates points which represents a circle.
 *
 * The calculations aretaken from Felix Palmer's project shared on GitHUB.
 * Copyright 2011, Felix Palmer
 * Licensed under the MIT license:
 * http://creativecommons.org/licenses/MIT/
 *
 * @author robert.fejer
 */
public class CirclePatternRenderer implements PatternRenderer {

    private float modulation = 0;

    @Override
    public float[] createPatternPoints(byte[] bytes, Rect rect) {
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

        // Controls the pulsing rate
        modulation += 0.04;

        return points;
    }

    private float[] toPolar(float[] cartesian, Rect rect) {
        double cX = rect.width() / 2;
        double cY = rect.height() / 2;
        double angle = (cartesian[0]) * 2 * Math.PI;
        float aggressive = 0.33f;
        double radius =
                ((rect.width() / 2) * (1 - aggressive) + aggressive * cartesian[1] / 2) * (1.2 + Math.sin(modulation))
                        / 2.2;
        float[] out = {
                (float) (cX + radius * Math.sin(angle)),
                (float) (cY + radius * Math.cos(angle))
        };
        return out;
    }
}
