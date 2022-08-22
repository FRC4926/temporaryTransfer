// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  public Rev2mDistanceSensor distanceSensor = new Rev2mDistanceSensor(Port.kMXP);
  public ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
 public CANSparkMax feederMotor = new CANSparkMax(16, MotorType.kBrushless);
  public WPI_TalonSRX rightIndexer = new WPI_TalonSRX(14);
  public WPI_TalonSRX leftIndexer = new WPI_TalonSRX(15); 
  public WPI_TalonSRX topIndexer = new WPI_TalonSRX(17);
  public WPI_TalonSRX intakeArm = new WPI_TalonSRX(18);
  public CANSparkMax ballIntake = new CANSparkMax(7, MotorType.kBrushless);
  public DutyCycleEncoder intakeArmEncoder = new DutyCycleEncoder(new DigitalInput(2));

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    feederMotor.setSmartCurrentLimit(40);
    rightIndexer.configPeakCurrentLimit(40);
    leftIndexer.configPeakCurrentLimit(40);
    topIndexer.configPeakCurrentLimit(40);
    intakeArm.configPeakCurrentLimit(40);

    distanceSensor.setAutomaticMode(true);
    distanceSensor.setEnabled(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public double ballIntaked(){
   // return distanceSensor.getRange(Unit.kInches) < 1 ? true : false;
    return redValue();
   }

  public double getDistance(){
    return distanceSensor.getRange(Unit.kInches);
  }
  public boolean isBallColorCorrect(){
    int blue = colorSensor.getBlue();
    int red = colorSensor.getRed();
    String colorOfBall = blue > red ? "Blue" : "Red";
    return DriverStation.getAlliance().name().equals(colorOfBall);
  }

  public void feed(double effort) {
    feederMotor.set(-effort);
  }

  public int blueValue() {
  return colorSensor.getBlue();
}

public int redValue() {
  return colorSensor.getRed();
}

public void raiseArm(double effort) {
    double position = intakeArmEncoder.getAbsolutePosition();
    SmartDashboard.putNumber("Arm Position", position);
    if(position < 0.965)
      intakeArm.set(effort);
}

  public void lowerArm(double effort) {
    double position = intakeArmEncoder.getAbsolutePosition();
    SmartDashboard.putNumber("Arm Position", position);
    if(position > 0.15)
      intakeArm.set(-effort);
    
  }

  
  public  void runAllIntake(double effort) {
    rightIndexer.set(effort);
    topIndexer.set(effort);
    leftIndexer.set(-effort);
    ballIntake.set(-effort);
    feederMotor.set(-effort);
  }  
}
