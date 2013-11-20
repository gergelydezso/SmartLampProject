package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.lamp;

import android.graphics.Rect;
import android.util.Log;
import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.connection.bluetooth.BluetoothCommunicationBridge;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampAPIHolder;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.VisualizerCapturedDataHandler;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.VisualizerDataHandler;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer.CirclePatternRenderer;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.renderer.PatternRenderer;

import java.util.Calendar;

/**
 *
 *
 * @author robert.fejer
 */
public class LampMusicVisualizer implements VisualizerDataHandler {

    private static final String TAG                       = "LampMusicVisualizer";//LampMusicVisualizer.class.getSimpleName();
    private static final int    LED_COLOR_CHANGE_INTERVAL = 300;
    private static final int    MOTION_CHANGE_INTERVAL    = 10000;

    private Rect            rect;
    private long            creationTime;
    private PatternRenderer patternRenderer;
    private int[]           actualColor;
    private SmartLampAPI smartLampAPI       = SmartLampAPIHolder.getApi();
    private int[]        servo1Positions    = {0, 30, 90, 30, 120, 150, 90, 30, 0};
    private int          servoPositionIndex = 1;

    public LampMusicVisualizer(Rect rect) {
        this.rect = rect;
        init();
    }

    public void setActualColor(int[] actualColor) {
        this.actualColor = actualColor;
    }

    @Override
    public void handleWaveFormData(byte[] bytes) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long elapsedTime = currentTime - creationTime;

        String actualColorString = "";
        if (actualColor != null) {
            actualColorString = " - actualColor: " + actualColor[0] + "," + actualColor[1] + "," + actualColor[2];
        }

        // hundred milliseconds
        int firstDigitOfTheLastThree = findTheFirstNDigitsOfTheLastNDigits(1, 3, elapsedTime);
        if (firstDigitOfTheLastThree > 0 && firstDigitOfTheLastThree % 3 == 0) {
            changeLedColor();
            Log.d(TAG, "changeLedColor() - CALLED! - elapsedTime: " + elapsedTime + " - " + elapsedTime % 1000 + " - "
                    + firstDigitOfTheLastThree);
        }

        // tenth seconds
        int firstDigitOfTheLastFive = findTheFirstNDigitsOfTheLastNDigits(1, 5, elapsedTime);
        if (firstDigitOfTheLastFive > 0 && firstDigitOfTheLastFive == servoPositionIndex) {
            //changeLampPosition(servoPositionIndex);
            ++servoPositionIndex;
            if (servoPositionIndex > servo1Positions.length) {
                servoPositionIndex = 1;
            }
        }
    }

    private int findTheFirstNDigitsOfTheLastNDigits(int numberOfFirstN, int numberOfLastN, long number) {
        float lastNDigits = number % (int) Math.pow(10, numberOfLastN);
        double firstNDigits = lastNDigits / Math.pow(10, numberOfLastN - numberOfFirstN);

        return (int) firstNDigits;
    }

    private void changeLedColor() {
        if (smartLampAPI != null) {
          Log.d(TAG, "changeLedColor()::smartLampAPI - NOT NULL");
          smartLampAPI.setLedValue(actualColor[0], actualColor[1], actualColor[2], commandCallback);
        }
        else {
            Log.d(TAG, "changeLedColor()::smartLampAPI - NULL");
        }
    }

    private void changeLampPosition(int index) {
        if (smartLampAPI != null) {
            Log.d(TAG, "changeLampPosition()::smartLampAPI - NOT NULL :: servo_pos: " + servo1Positions[index-1]);
            //smartLampAPI.setServoPosition(ServoMotorEntity.SERVO1, servo1Positions[index-1], commandCallback);
        }
        else {
            Log.d(TAG, "changeLampPosition()::smartLampAPI - NULL");
        }
    }

    @Override
    public void handleFFTData(byte[] bytes) {

    }

    private void init() {
        VisualizerCapturedDataHandler visualizerCapturedDataHandler = VisualizerCapturedDataHandler.getInstance();
        visualizerCapturedDataHandler.registerDataHandler(this);
        creationTime = Calendar.getInstance().getTimeInMillis();
        patternRenderer = new CirclePatternRenderer();
    }

    private CommandCallback commandCallback = new CommandCallback() {
        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }

        @Override
        public void onResult(String state) {

        }
    };
}
