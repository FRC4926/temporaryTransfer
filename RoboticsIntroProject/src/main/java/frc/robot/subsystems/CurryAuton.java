// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Calendar;
import java.util.Date;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer.Subsystems;

public class CurryAuton extends SubsystemBase {
  /** Creates a new CurryAuton. */

public double startTime = 0;



  public CurryAuton() {


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }

  public boolean curryBackup(){
    Calendar c1 = Calendar.getInstance();
    Date dateOne = c1.getTime();
//    double time = System.currentTimeMillis();

    if(startTime == 0) {
      startTime = dateOne.getTime();

    }

    if((dateOne.getTime()-startTime) <= 2000) {

      Subsystems.driveSubsystem.left1.set(-0.3);
      Subsystems.driveSubsystem.right1.set(-0.3);
      Subsystems.driveSubsystem.left2.set(-0.3);
      Subsystems.driveSubsystem.right2.set(-0.3);
return false;

      }
      else {
        
        Subsystems.driveSubsystem.left1.set(0);
        Subsystems.driveSubsystem.right1.set(0);
        Subsystems.driveSubsystem.left2.set(0);
        Subsystems.driveSubsystem.right2.set(0);
        return true;
      }
  }



  /** This function is called periodically during autonomous. */
   
  }
