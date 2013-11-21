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

        motion.add(createState(1.6, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(2.1, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(2.6, ServoMotorEntity.SERVO1, 35));
        motion.add(createState(3.1, ServoMotorEntity.SERVO1, 125));

        motion.add(createState(5.4, ServoMotorEntity.SERVO2, 135));

        motion.add(createState(7.1, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(7.6, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(8.1, ServoMotorEntity.SERVO1, 35));
        motion.add(createState(8.6, ServoMotorEntity.SERVO1, 125));

        motion.add(createState(13.4, ServoMotorEntity.SERVO2, 135));

        motion.add(createState(14.6, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(15.1, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(15.6, ServoMotorEntity.SERVO1, 35));
        motion.add(createState(16.1, ServoMotorEntity.SERVO1, 125));

        motion.add(createState(20.4, ServoMotorEntity.SERVO2, 135));

        motion.add(createState(21.6, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(22.1, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(22.6, ServoMotorEntity.SERVO1, 35));
        motion.add(createState(23.1, ServoMotorEntity.SERVO1, 125));

        motion.add(createState(28.4, ServoMotorEntity.SERVO2, 135));

        motion.add(createState(29.1, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(29.6, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(30.1, ServoMotorEntity.SERVO1, 35));
        motion.add(createState(30.6, ServoMotorEntity.SERVO1, 125));

        motion.add(createState(74.1, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(74.6, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(75.1, ServoMotorEntity.SERVO1, 35));
        motion.add(createState(75.6, ServoMotorEntity.SERVO1, 125));

        motion.add(createState(79.4, ServoMotorEntity.SERVO2, 135));

        motion.add(createState(81.1, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(81.6, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(82.1, ServoMotorEntity.SERVO1, 35));
        motion.add(createState(82.6, ServoMotorEntity.SERVO1, 125));


        motion.add(createState(87.4, ServoMotorEntity.SERVO2, 135));
        motion.add(createState(139.4, ServoMotorEntity.SERVO2, 135));
        motion.add(createState(147.4, ServoMotorEntity.SERVO2, 135));

        return motion;
    }

    private static LampHeadState createState(double timeInSeconds, ServoMotorEntity servoMotorEntity, int servoPositon) {
        return new LampHeadState(timeInSeconds, servoMotorEntity, servoPositon);
    }
}
