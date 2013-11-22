package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.lamp;

import android.media.MediaPlayer;
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
    private SmartLamp smartLamp = SmartLampHolder.getSmartLamp();
    private final MediaPlayer mediaPlayer;
    private TreeSet<LampHeadState> motion;

    public LampMusicVisualizer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
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
        long elapsedTime = mediaPlayer.getCurrentPosition();

        // hundred milliseconds
        int firstDigitOfTheLastThree = findTheFirstNDigitsOfTheLastNDigits(1, 3, elapsedTime);
        if (firstDigitOfTheLastThree > 0 && firstDigitOfTheLastThree % 3 == 0) {
            changeLedColor();
            //Log.d(TAG, "changeLedColor() - CALLED! - elapsedTime: " + elapsedTime + " - " + elapsedTime % 1000 + " - " + firstDigitOfTheLastThree);
        }

        if (!motion.isEmpty()) {
            LampHeadState currentHeadState = motion.first();
            if (currentHeadState.getTimeInSeconds() == elapsedTime / 1000)  { //(currentHeadState.getTimeInSeconds() * 10 == elapsedTime / 100)
                Log.d(TAG, "changeLampPosition() - CALLED! - elapsedTime: " + elapsedTime + " - " + elapsedTime / 100);
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

    private boolean shouldChangeLampPosition(LampHeadState currentHeadState, long elapsedTime) {
        boolean shouldChange = false;

        int timeInSeconds = (int) (currentHeadState.getTimeInSeconds() * 10);
        long elapsedTimeInclTenthMSec = elapsedTime / 100;

        int tenthMSecPartOfTimeInSeconds = timeInSeconds % 10;
        int tenthMSecPartOfElapsedTime = (int) elapsedTimeInclTenthMSec % 10;

        int secPartOfTimeInSeconds = (int) currentHeadState.getTimeInSeconds();
        long secPartOfElapsedTime = elapsedTime / 1000;

        if (secPartOfTimeInSeconds == secPartOfElapsedTime) {
            if ( (tenthMSecPartOfTimeInSeconds >= 0 && tenthMSecPartOfTimeInSeconds < 5) && (tenthMSecPartOfElapsedTime >= 0 && tenthMSecPartOfElapsedTime < 5) ) {
                shouldChange = true;
            }
            else if ( (tenthMSecPartOfTimeInSeconds >= 5 && tenthMSecPartOfTimeInSeconds <= 9) && (tenthMSecPartOfElapsedTime >= 5 && tenthMSecPartOfElapsedTime <= 9) ) {
                shouldChange = true;
            }
/*
            Log.d(TAG, "shouldChangeLampPosition() - CALLED! - elapsedTime: " + elapsedTime +
                    ", tenthMSecPartOfElapsedTime: " + tenthMSecPartOfElapsedTime +
                    ", timeInSeconds: " + timeInSeconds +
                    ", tenthMSecPartOfTimeInSeconds: " + tenthMSecPartOfTimeInSeconds +
                    ", secPartOfTimeInSeconds: " + secPartOfTimeInSeconds +
                    ", secPartOfElapsedTime: " + secPartOfElapsedTime);*/
        }

        return shouldChange;
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
