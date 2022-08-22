// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer.Subsystems;
import frc.robot.util.GalacPIDController;

public class LimeLightSubsystem extends SubsystemBase {


  public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");  
  
  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double a = ta.getDouble(0.0);

  /** Creates a new LimeLightSubsystem. */
  public LimeLightSubsystem() {
    table.getEntry("ledMode").setNumber(3);
    bob.setStallEffort(0.07);
  }
  
  @Override

  public void periodic() {
    // This method will be called once per scheduler run
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    a = ta.getDouble(0.0);
  }

  public double getDistance() {
    
    // inches
    double limelightHeight = 35;
    double goalHeight = 104;

    double mountAngle = 30;
    double angle = Math.toRadians(mountAngle + y);

    double distance = (goalHeight - limelightHeight) / Math.tan(angle);

    SmartDashboard.putNumber("Distance", distance);

    return distance;
  }

GalacPIDController bob = new GalacPIDController(0.014, 0, 0.002, 0.295, () -> x, 0, 1);
  public double getEffort() {

double effort = bob.getEffort();
SmartDashboard.putNumber("effort", effort);
      return -effort;

  }

  public void alignRobot() {
    SmartDashboard.putBoolean("Robot Ran", true);
      Subsystems.driveSubsystem.arcadeDrive(0, getEffort());
  }
}
