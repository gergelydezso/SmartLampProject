package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.lamp;

import com.gergelydezso.smartlampsdk.ServoMotorEntity;

/**
 * A class for describing the lamp head's position at a given time.
 *
 * @author robert.fejer
 */
public class LampHeadState implements Comparable<LampHeadState> {

    private int              timeInSeconds;
    private ServoMotorEntity servoMotorEntity;
    private int              servoPositon;

    public LampHeadState() {

    }

    public LampHeadState(int timeInSeconds, ServoMotorEntity servoMotorEntity, int servoPositon) {
        this.timeInSeconds = timeInSeconds;
        this.servoMotorEntity = servoMotorEntity;
        this.servoPositon = servoPositon;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public ServoMotorEntity getServoMotorEntity() {
        return servoMotorEntity;
    }

    public void setServoMotorEntity(ServoMotorEntity servoMotorEntity) {
        this.servoMotorEntity = servoMotorEntity;
    }

    public int getServoPositon() {
        return servoPositon;
    }

    public void setServoPositon(int servoPositon) {
        this.servoPositon = servoPositon;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("LampHeadState [timeInSeconds = " + timeInSeconds);
        strBuilder.append(", servoMotorEntity = " + servoMotorEntity.name());
        strBuilder.append(", servoPositon = " + servoPositon);
        strBuilder.append("]");

        return strBuilder.toString();
    }

    @Override
    public int compareTo(LampHeadState otherLampHeadState) {
        int ret = 0;

        if (getTimeInSeconds() < otherLampHeadState.getTimeInSeconds()) {
            ret = -1;
        }
        else if (getTimeInSeconds() > otherLampHeadState.getTimeInSeconds()) {
            ret = 1;
        }

        return ret;
    }
}
