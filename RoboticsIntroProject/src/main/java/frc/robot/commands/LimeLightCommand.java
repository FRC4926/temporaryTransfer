// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.Subsystems;

public class LimeLightCommand extends CommandBase {


  /** Creates a new LimeLightCommand. */
  public LimeLightCommand() {
    addRequirements(Subsystems.limelightSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Robot Ran", false );

    Subsystems.limelightSubsystem.getDistance();
    if (RobotContainer.operatorController.getLeftTriggerAxis() > 0.5) {
      Subsystems.limelightSubsystem.alignRobot();

    }


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
