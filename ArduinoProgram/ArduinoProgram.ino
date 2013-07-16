#include <Servo.h> 

Servo myservo1;
Servo myservo3;
Servo myservo5;

int ledPinRed = 11;  
int ledPinGreen = 12;    
int ledPinBlue = 13;


char charArray[10];
byte index = 0;
boolean assessed = false;
boolean ledCommand = false;
boolean servoCommand = false;

void setup() 
{ 
  Serial.begin(9600);
  myservo1.attach(10);
  myservo3.attach(9);
  myservo5.attach(8);
} 

void loop() 
{                                 
  while (Serial.available() > 0) {
    char c = Serial.read();
    charArray[index] = c;
    Serial.println("Command:");
    Serial.println(c);
    index ++;

    Serial.println(index);

    if (index == 5){
      assessed =  false;
      servoCommand = true;
      Serial.println("Index = 5");
    }
    if (index > 5 ){
      assessed = false;
      ledCommand = true;
      servoCommand = false;

      Serial.println("Index = 10");
    }
  } 

  if (!assessed) {


    assessed = true;
    index = 0;


    if (servoCommand){

      int hundred = (charArray[2] - '0') *100;
      int then = (charArray[3] - '0') *10;
      int one = charArray[4] - '0';
      int value = hundred + then + one;

      Serial.println("Servo");
      switch (charArray[1] - '0'){
      case 1:
        Serial.println("Servo1");
        myservo1.write(value);
        break;
      case 3:
        Serial.print("Servo3");
        myservo3.write(value);
        break;
      case 5:
        Serial.print("Servo5"); 
        myservo5.write(value);
        break;
      }

      servoCommand = false;

    }


    if (ledCommand){



      int hundredRed = (charArray[1] - '0') *100;
      int thenRed = (charArray[2] - '0') *10;
      int oneRed = charArray[3] - '0';
      int valueRed = hundredRed + thenRed + oneRed;


      int hundredGreen = (charArray[4] - '0') *100;
      int thenGreen = (charArray[5] - '0') *10;
      int oneGreen = charArray[6] - '0';
      int valueGreen = hundredGreen + thenGreen + oneGreen;


      int hundredBlue = (charArray[7] - '0') *100;
      int thenBlue = (charArray[8] - '0') *10;
      int oneBlue = charArray[9] - '0';
      int valueBlue = hundredBlue + thenBlue + oneBlue;

      Serial.println("ez egy led");

      Serial.print(valueRed);
      Serial.print(valueGreen);
      Serial.print(valueBlue);

      analogWrite(ledPinRed, valueRed*0.6);
      analogWrite(ledPinGreen, valueGreen);
      analogWrite(ledPinBlue, valueBlue);

      ledCommand = false;

    }

  } 

} 




















