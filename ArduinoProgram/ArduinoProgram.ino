#include <Servo.h>

Servo Servo1;
Servo Servo2;
Servo Servo3;
Servo Servo4;
Servo Servo5;

int LEDPinRed = 11;
int LEDPinGreen = 12;
int LEDPinBlue = 13;

byte index = 0;
char commandArray[25];

boolean assessed = false;
boolean LEDCommand = false;
boolean servoCommand = false;
boolean allCommand = false;

void setup()
{
  Serial.begin(9600);

  Servo1.attach(6);
  Servo2.attach(7);
  Servo3.attach(8);
  Servo4.attach(9);
  Servo5.attach(10);
}

void loop()
{
  while (Serial.available() > 0) {
    char buffer = Serial.read();
    commandArray[index] = buffer;
    index ++;
    delay(5);
  }

  switch (index){
  case 5:
    assessed = false;
    servoCommand = true;
    break;
  case 10:
    assessed = false;
    LEDCommand = true;
    servoCommand = false;
    break;
  case 24:
    assessed = false;
    Serial.println("SetAll Command");
    Serial.println(commandArray);
    break;
  }

  if (!assessed) {

    assessed = true;
    index = 0;

    if (servoCommand){

      Serial.println("Servo Command");

      int servoValue = caluclateValue(commandArray, 2, 3, 4);

      switch (commandArray[1] - '0'){
      case 1:
        Serial.println("Servo1");
        Serial.println(servoValue);
        Servo1.write(servoValue);
        break;
      case 2:
        Serial.println("Servo2");
        Serial.println(servoValue);
        Servo1.write(servoValue);
        break;
      case 3:
        Serial.println("Servo3");
        Serial.println(servoValue);
        Servo3.write(servoValue);
        break;
      case 4:
        Serial.println("Servo4");
        Serial.println(servoValue);
        Servo1.write(servoValue);
        break;
      case 5:
        Serial.println("Servo5");
        Serial.println(servoValue);
        Servo5.write(servoValue);
        break;
      }

      servoCommand = false;

    }

    if (LEDCommand){

      Serial.println("LED Commnad");

      int valueRed = calculatevalue(commandArray, 1, 2, 3);
      int valueGreen = calculatevalue(commandArray, 4, 5, 6);
      int valueBlue = calculatevalue(commandArray, 7, 8, 9);

      Serial.println(valueRed);
      Serial.println(valueGreen);
      Serial.println(valueBlue);

      analogWrite(LEDPinRed, valueRed*0.6);
      analogWrite(LEDPinGreen, valueGreen);
      analogWrite(LEDPinBlue, valueBlue);

      LEDCommand = false;

    }
  }
}

int caluclateValue (char command[], int hundredIndex, int thenIndex, int oneIndex){

  int hundred = (command[hundredIndex] - '0') *100;
  int then = (command[thenIndex] - '0') *10;
  int one = command[oneIndex] - '0';
  int value = hundred + then + one;

  return value;
}

void executeLedCommand(char command[]){

  Serial.println("LED Commnad");

  int valueRed = calculatevalue(command, 1, 2, 3);
  int valueGreen = calculatevalue(command, 4, 5, 6);
  int valueBlue = calculatevalue(command, 7, 8, 9);

  Serial.println(valueRed);
  Serial.println(valueGreen);
  Serial.println(valueBlue);

  analogWrite(LEDPinRed, valueRed*0.6);
  analogWrite(LEDPinGreen, valueGreen);
  analogWrite(LEDPinBlue, valueBlue);

}

void executeServCommand(char command[]){

  int servoValue = caluclateValue(commandArray, 2, 3, 4);
  int servoID = command[1] - '0';

  Serial.println("Servo Command");
  Serial.println(servoValue);

  switch (servoID){
  case 1:
    Serial.println("Servo1");
    Servo1.write(servoValue);
    break;
  case 2:
    Serial.println("Servo2");
    Servo1.write(servoValue);
    break;
  case 3:
    Serial.println("Servo3");
    Servo3.write(servoValue);
    break;
  case 4:
    Serial.println("Servo4");
    Servo1.write(servoValue);
    break;
  case 5:
    Serial.println("Servo5");
    Servo5.write(servoValue);
    break;
  }

}

void executeParallelCommand(char command[]){

  int valueServo1 = calculatevalue(command, 1, 2, 3);
  int valueServo2 = calculatevalue(command, 4, 5, 6);
  int valueS = calculatevalue(command, 7, 8, 9);
  int valueRed = calculatevalue(command, 1, 2, 3);
  int valueGreen = calculatevalue(command, 4, 5, 6);
  int valueBlue = calculatevalue(command, 7, 8, 9);
  int valueRed = calculatevalue(command, 1, 2, 3);
  int valueGreen = calculatevalue(command, 4, 5, 6);

}















