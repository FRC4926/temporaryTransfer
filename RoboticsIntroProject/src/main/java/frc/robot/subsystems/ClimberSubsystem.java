// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
  CANSparkMax climberRight = new CANSparkMax(20,MotorType.kBrushless);
  CANSparkMax climberTiltR1 = new CANSparkMax(22,MotorType.kBrushless);
  CANSparkMax climberLeft = new CANSparkMax(10,MotorType.kBrushless);
  CANSparkMax climberTiltL1 = new CANSparkMax(12,MotorType.kBrushless);
  /** Creates a new Climber. */
  public ClimberSubsystem() {
    
  }

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }

  public void tiltClimber(double effort, boolean right) {
    if(right){
      climberTiltR1.set(effort);
    }else{
      climberTiltL1.set(effort);
    }
  }
  public void extendClimber(double effort, boolean right){
    if(right){
      climberTiltR1.set(effort);
    }else{
      climberTiltL1.set(effort);
    }
  }
}
