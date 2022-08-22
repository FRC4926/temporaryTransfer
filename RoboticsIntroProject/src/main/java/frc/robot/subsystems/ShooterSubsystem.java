// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Arrays;
import java.util.List;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer.Subsystems;
import frc.robot.util.LookupTable;

public class ShooterSubsystem extends SubsystemBase {
 // public IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public CANSparkMax leftShooter = new CANSparkMax(9, MotorType.kBrushless);
  public CANSparkMax rightShooter = new CANSparkMax(8, MotorType.kBrushless);

  public SparkMaxPIDController leftPID = leftShooter.getPIDController();
  public SparkMaxPIDController rightPID = rightShooter.getPIDController();

  private List<Double> distances = Arrays.asList(52.0, 62.5, 72.5, 82.5, 92.5, 102.5, 112.5, 122.5, 132.5, 142.5, 170.0, 242.5);
  private List<Double> rpms = Arrays.asList(1250.0, 1300.0, 1350.0, 1400.0, 1450.0, 1500.0, 1550.0, 1650.0, 1715.0, 1815.0, 2500.0, 2815.0);
  public LookupTable lookupTable = new LookupTable(distances, rpms);

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    
    leftShooter.setSmartCurrentLimit(40);
    rightShooter.setSmartCurrentLimit(40);

    leftPID.setP(0.0005);
    leftPID.setI(0);
    leftPID.setD(0);
    leftPID.setFF(2e-4);
    rightPID.setP(0.0005);
    rightPID.setI(0);
    rightPID.setD(0);
    rightPID.setFF(2e-4);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public int getRPM(double distance) {
    return (int) lookupTable.calculateRPM(distance);
  }

  public void revShooter(double speed) {
  //  if(intakeSubsystem.isBallColorCorrect()){
  //   leftPID.setReference(-speed, ControlType.kVelocity);
  //   rightPID.setReference(speed, ControlType.kVelocity);
  //  }else{
  //    leftPID.setReference(0.2, ControlType.kVelocity);
  //  }
   leftPID.setReference(-speed, ControlType.kVelocity);
   rightPID.setReference(speed, ControlType.kVelocity);
  }

  public void stopShooter(){
    leftShooter.set(0);
    rightShooter.set(0);
  }
}
