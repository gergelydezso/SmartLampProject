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

      motion.add(createState(1, ServoMotorEntity.SERVO1, 150));
      motion.add(createState(2, ServoMotorEntity.SERVO1, 125));
      motion.add(createState(3, ServoMotorEntity.SERVO1,100));
      motion.add(createState(4, ServoMotorEntity.SERVO1, 125));

      motion.add(createState(6, ServoMotorEntity.SERVO2, 120));

      motion.add(createState(9, ServoMotorEntity.SERVO1, 150));
      motion.add(createState(10, ServoMotorEntity.SERVO1, 125));
      motion.add(createState(11, ServoMotorEntity.SERVO1,100));
      motion.add(createState(12, ServoMotorEntity.SERVO1, 125));

      motion.add(createState(14, ServoMotorEntity.SERVO2, 120));

      motion.add(createState(16, ServoMotorEntity.SERVO1, 150));
      motion.add(createState(17, ServoMotorEntity.SERVO1, 125));
      motion.add(createState(18, ServoMotorEntity.SERVO1,100));
      motion.add(createState(19, ServoMotorEntity.SERVO1, 125));

      motion.add(createState(21, ServoMotorEntity.SERVO2, 120));

      motion.add(createState(23, ServoMotorEntity.SERVO1, 150));
      motion.add(createState(24, ServoMotorEntity.SERVO1, 125));
      motion.add(createState(25, ServoMotorEntity.SERVO1,100));
      motion.add(createState(26, ServoMotorEntity.SERVO1, 125));

      motion.add(createState(29, ServoMotorEntity.SERVO2, 120));

      motion.add(createState(31, ServoMotorEntity.SERVO1, 150));
      motion.add(createState(32, ServoMotorEntity.SERVO1, 125));
      motion.add(createState(33, ServoMotorEntity.SERVO1,100));
      motion.add(createState(34, ServoMotorEntity.SERVO1, 125));

      motion.add(createState(74, ServoMotorEntity.SERVO1, 150));
      motion.add(createState(75, ServoMotorEntity.SERVO1, 125));
      motion.add(createState(76, ServoMotorEntity.SERVO1,100));
      motion.add(createState(77, ServoMotorEntity.SERVO1, 125));

      motion.add(createState(80, ServoMotorEntity.SERVO2, 120));

      motion.add(createState(82, ServoMotorEntity.SERVO1, 150));
      motion.add(createState(83, ServoMotorEntity.SERVO1, 125));
      motion.add(createState(84, ServoMotorEntity.SERVO1,100));
      motion.add(createState(85, ServoMotorEntity.SERVO1, 125));


      motion.add(createState(88, ServoMotorEntity.SERVO2, 120));
      motion.add(createState(140, ServoMotorEntity.SERVO2, 120));
      motion.add(createState(148, ServoMotorEntity.SERVO2, 120));

      return motion;
    }

    private static LampHeadState createState(double timeInSeconds, ServoMotorEntity servoMotorEntity, int servoPositon) {
        return new LampHeadState(timeInSeconds, servoMotorEntity, servoPositon);
    }
}
