// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveSubsystem extends SubsystemBase {

  public  CANSparkMax left1 = new CANSparkMax(4, MotorType.kBrushless);
  public  CANSparkMax left2 = new CANSparkMax(5, MotorType.kBrushless);
  public  CANSparkMax right1 = new CANSparkMax(1, MotorType.kBrushless);
  public  CANSparkMax right2 = new CANSparkMax(2, MotorType.kBrushless);
  public  DifferentialDrive m_drive = new DifferentialDrive(left1, right1);
  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {

    left1.setIdleMode(IdleMode.kBrake);
    left2.setIdleMode(IdleMode.kBrake);
    right1.setIdleMode(IdleMode.kBrake);
    right2.setIdleMode(IdleMode.kBrake);


    left1.follow(ExternalFollower.kFollowerDisabled, 0);
    left2.follow(left1);

    right1.follow(ExternalFollower.kFollowerDisabled, 0);
    right2.follow(right1);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void tankDrive(double leftEff, double rightEff)
  {
    left1.set(leftEff);
    left2.set(leftEff);
    right1.set(rightEff);
    right2.set(rightEff);
  }

  public void arcadeDrive(double speed, double rotate)
  {
    m_drive.feed();
    m_drive.arcadeDrive(speed, -rotate);
  }
}
