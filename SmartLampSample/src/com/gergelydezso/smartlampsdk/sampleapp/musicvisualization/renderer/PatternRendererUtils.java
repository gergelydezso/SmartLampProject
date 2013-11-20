package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer;

import java.util.Random;

/**
 *
 *
 * @author robert.fejer
 */
public final class PatternRendererUtils {

    /**
     * Calcutaes RED/GREEN/BLUE color codes, based on the colorCounter parameter.
     *
     * @param colorCounter the color counter
     * @return an array which represents the color: first element has the value of red, the second of the green, the third of the blue.
     */
    public static int[] calculateColorCodes(final float colorCounter) {
        int[] ret = new int[3];

        int red = (int) Math.floor(128 * (Math.sin(colorCounter) + 1));
        int green = (int) Math.floor(128 * (Math.sin(colorCounter + 2) + 1));
        int blue = (int) Math.floor(128 * (Math.sin(colorCounter + 4) + 1));

        ret[0] = red;
        ret[1] = green;
        ret[2] = blue;

        return ret;
    }

    public static int[] generateRandomColor() {
        int[] ret = new int[3];

        Random rand = new Random();
        ret[0] = rand.nextInt(255);
        ret[1] = rand.nextInt(255);
        ret[2] = rand.nextInt(255);

        return ret;
    }
}
