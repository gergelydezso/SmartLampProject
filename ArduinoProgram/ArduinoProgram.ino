#include <Servo.h> 
 
Servo myservo; 
               
int pos = 0;    
int incomingByte = 0;
 
void setup() 
{ 
  Serial.begin(9600); 
  myservo.attach(9);  
} 
void loop() 
{                                 
    if (Serial.available() > 0) {
     
      char c1 = Serial.read();
      pos = c1 - '0';
      Serial.print(pos);
      myservo.write(pos*10);             
      delay(20);                     
    
  }
 
} 
