package com.gergelydezso.smartlampsdk;

/**
 * Enumeration of servo motors.
 */
public enum ServoMotorEntity {
  /**
   * Servomotor 1.
   * */
  SERVO1(1),
  /**
   * Servomotor 2.
   * */
  SERVO2(2),
  /**
   * Servomotor 3.
   * */
  SERVO3(3),
  /**
   * Servomotor 4.
   * */
  SERVO4(4),
  /**
   * Servomotor 5.
   * */
  SERVO5(5);

  ServoMotorEntity(int i) {
    this.value = i;
  }

  private int value;

  public int getValue() {
    return value;
  }

}