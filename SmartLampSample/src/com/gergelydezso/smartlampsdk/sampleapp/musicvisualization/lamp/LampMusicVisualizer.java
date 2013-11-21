package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.lamp;

import android.util.Log;
import com.gergelydezso.smartlampsdk.SmartLamp;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampHolder;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.VisualizerCapturedDataHandler;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.VisualizerDataHandler;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Used for applying music visualization on the lamp.
 *
 * @author robert.fejer
 */
public class LampMusicVisualizer implements VisualizerDataHandler {

    private static final String TAG = "LampMusicVisualizer";//LampMusicVisualizer.class.getSimpleName();

    private long  creationTime;
    private int[] actualColor;
    private SmartLamp smartLamp          = SmartLampHolder.getSmartLamp();
    private TreeSet<LampHeadState> motion;

    public LampMusicVisualizer() {
        motion = new TreeSet<LampHeadState>();
        motion.addAll(LampStateFactory.createHeadMotion());
        init();
    }

    public void setActualColor(int[] actualColor) {
        this.actualColor = actualColor;
    }

    @Override
    public void handleWaveFormData(byte[] bytes) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long elapsedTime = currentTime - creationTime;

        // hundred milliseconds
        int firstDigitOfTheLastThree = findTheFirstNDigitsOfTheLastNDigits(1, 3, elapsedTime);
        if (firstDigitOfTheLastThree > 0 && firstDigitOfTheLastThree % 3 == 0) {
            changeLedColor();
            //Log.d(TAG, "changeLedColor() - CALLED! - elapsedTime: " + elapsedTime + " - " + elapsedTime % 1000 + " - " + firstDigitOfTheLastThree);
        }

        if (!motion.isEmpty()) {
            LampHeadState currentHeadState = motion.first();
            if (currentHeadState.getTimeInSeconds() == elapsedTime / 1000) {
                Log.d(TAG, "changeLampPosition() - CALLED! - elapsedTime: " + elapsedTime + " - " + elapsedTime / 1000);
                changeLampPosition(currentHeadState);
                motion.pollFirst();
            }
        }
    }

    private int findTheFirstNDigitsOfTheLastNDigits(int numberOfFirstN, int numberOfLastN, long number) {
        float lastNDigits = number % (int) Math.pow(10, numberOfLastN);
        double firstNDigits = lastNDigits / Math.pow(10, numberOfLastN - numberOfFirstN);

        return (int) firstNDigits;
    }

    private void changeLedColor() {
        if (smartLamp != null) {
          //Log.d(TAG, "changeLedColor()::smartLamp - NOT NULL");
          smartLamp.adjustLedComponent(actualColor[0], actualColor[1], actualColor[2], commandCallback);
        }
        else {
            //Log.d(TAG, "changeLedColor()::smartLamp - NULL");
        }
    }

    private void changeLampPosition(LampHeadState lampHeadState) {
        if (smartLamp != null) {
            Log.d(TAG, "changeLampPosition()::smartLamp - NOT NULL :: lampHeadState: " + lampHeadState);
            smartLamp.adjustServoComponent(lampHeadState.getServoMotorEntity(), lampHeadState.getServoPositon(), commandCallback);
        }
        else {
            //Log.d(TAG, "changeLampPosition()::smartLamp - NULL");
        }
    }

    @Override
    public void handleFFTData(byte[] bytes) {

    }

    private void init() {
        VisualizerCapturedDataHandler visualizerCapturedDataHandler = VisualizerCapturedDataHandler.getInstance();
        visualizerCapturedDataHandler.registerDataHandler(this);
        creationTime = Calendar.getInstance().getTimeInMillis();
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
