#include <math.h>
#include <SoftwareSerial.h>

const int buzerSound=11;                 //Connect the LED Grove module to Pin3, Digital 3
const int thresholdvalue=150;         //The threshold for which the LED should turn on. 
const int ledPin =  8;      // the number of the LED pin

float Rsensor; //Resistance of sensor in K

int buttonInput = 3;
int ledOutput = 13;
int buttonState = 1;

void setup() {
  Serial.begin(9600);                //Start the Serial connection
  pinMode(buzerSound,OUTPUT);            //Set the LED on Digital 3 as an OUTPUT
  digitalWrite(ledPin,HIGH);  
  pinMode(ledOutput, OUTPUT);
  pinMode(buttonInput, INPUT);
  digitalWrite(buttonInput, HIGH);
  Serial.begin(9600);
  Serial.println("Initialize...");  
}
void loop() {
//for laser
  int sensorValue = analogRead(0); 
  Rsensor=(float)(1923-sensorValue)*10/sensorValue;
  if(Rsensor>thresholdvalue)
  {
    digitalWrite(buzerSound,HIGH);
    Serial.println("Full");
  }
  else
  {
  digitalWrite(buzerSound,LOW);
  Serial.println("Empty");
  }
  //Serial.println("the analog read data is ");
  //Serial.println(sensorValue);
  //Serial.println("the sensor resistance is ");
  //Serial.println(Rsensor,DEC);//show the light intensity on the serial monitor;
  
//for metal detector
  buttonState = digitalRead(buttonInput);
  
  if(buttonState == HIGH){
    digitalWrite(ledOutput, LOW);
    //digitalWrite(buzerSound,HIGH);
  }
  else{
    digitalWrite(ledOutput, HIGH);   
  }
  
  delay(1000);
}  
