package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.lamp;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The factory used to create a movement for the lamp.
 *
 * @author robert.fejer
 */
public class LampStateFactory {

    private LampStateFactory() {

    }

    public static List<LampHeadState> createHeadMotion() {
        List<LampHeadState> motion = new ArrayList<LampHeadState>();

        motion.add(createState(20, ServoMotorEntity.SERVO1, 50));
        motion.add(createState(10, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(1, ServoMotorEntity.SERVO1, 165));

        return motion;
    }

    private static LampHeadState createState(int timeInSeconds, ServoMotorEntity servoMotorEntity, int servoPositon) {
        return new LampHeadState(timeInSeconds, servoMotorEntity, servoPositon);
    }
}
