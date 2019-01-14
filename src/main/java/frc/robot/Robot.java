/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Servo;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  
  private SerialPort serialportkUSB1;
  private String serialString;
  private String[] parsedSerialString;
  private int targetX;
  private Servo servo;
  private double servoAngle;

  @Override
  public void robotInit() {

    serialportkUSB1 = new SerialPort(115200, Port.kUSB1); //kUSB1 is the port towards the inside of the RoboRio
    servo = new Servo(9); 
    servo.setAngle(90);
  }

  @Override
  public void teleopPeriodic() {

    serialString = serialportkUSB1.readString();
    parsedSerialString = serialString.split(" ");
    
    if(parsedSerialString.length > 1){
      targetX = Integer.valueOf(parsedSerialString[2].strip());
      servoAngle = servo.getAngle();


      System.out.println("X=" + targetX + " " + "ANGLE=" + servo.getAngle() + " ");

      if(targetX < -100){

        if(servo.getAngle() < 5){
          servo.setAngle(0);
        }else{
          servo.setAngle(servo.getAngle()  - 2);
        }
      }
      else if(targetX > 100){

        if(servo.getAngle() > 170){
          servo.setAngle(180);
        }else{
          servo.setAngle(servo.getAngle() + 2);
        }
      }
    }
    
  }
}