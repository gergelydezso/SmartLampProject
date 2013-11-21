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

        motion.add(createState(1, ServoMotorEntity.SERVO1, 179));
        //motion.add(createState(2, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(2, ServoMotorEntity.SERVO1, 35));

        motion.add(createState(6, ServoMotorEntity.SERVO2, 135));

        //motion.add(createState(7, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(7, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(8, ServoMotorEntity.SERVO1, 35));

        motion.add(createState(14, ServoMotorEntity.SERVO2, 135));

        //motion.add(createState(14.8, ServoMotorEntity.SERVO1, 179));
        //motion.add(createState(15.1, ServoMotorEntity.SERVO1, 125));
        motion.add(createState(15, ServoMotorEntity.SERVO1, 35));

        //motion.add(createState(21.5, ServoMotorEntity.SERVO2, 135));

        motion.add(createState(21, ServoMotorEntity.SERVO1, 179));
        //motion.add(createState(22.1, ServoMotorEntity.SERVO1, 125));
        //motion.add(createState(22.4, ServoMotorEntity.SERVO1, 35));

        motion.add(createState(29, ServoMotorEntity.SERVO2, 135));

        //motion.add(createState(29.3, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(30, ServoMotorEntity.SERVO1, 125));
        //motion.add(createState(30.5, ServoMotorEntity.SERVO1, 35));

        motion.add(createState(74, ServoMotorEntity.SERVO1, 179));
        //motion.add(createState(74.3, ServoMotorEntity.SERVO1, 125));
        //motion.add(createState(74.6, ServoMotorEntity.SERVO1, 35));

        motion.add(createState(80, ServoMotorEntity.SERVO2, 135));

        //motion.add(createState(81.3, ServoMotorEntity.SERVO1, 179));
        motion.add(createState(81, ServoMotorEntity.SERVO1, 125));
        //motion.add(createState(81.9, ServoMotorEntity.SERVO1, 35));

        motion.add(createState(88, ServoMotorEntity.SERVO2, 135));
        motion.add(createState(140, ServoMotorEntity.SERVO2, 135));
        motion.add(createState(148, ServoMotorEntity.SERVO2, 135));

        return motion;
    }

    private static LampHeadState createState(int timeInSeconds, ServoMotorEntity servoMotorEntity, int servoPositon) {
        return new LampHeadState(timeInSeconds, servoMotorEntity, servoPositon);
    }
}
