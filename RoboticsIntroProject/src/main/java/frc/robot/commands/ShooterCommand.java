// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.Subsystems;

public class ShooterCommand extends CommandBase {
  /** Creates a new ShooterCommand. */
  public ShooterCommand() {
    addRequirements(Subsystems.shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (RobotContainer.operatorController.getStartButton()) {
      double rpm = Subsystems.shooterSubsystem.getRPM(Subsystems.limelightSubsystem.getDistance());
      SmartDashboard.putNumber("RPM", rpm);
      Subsystems.shooterSubsystem.revShooter(rpm);
    } else {
      Subsystems.shooterSubsystem.stopShooter();
    }

    if (RobotContainer.operatorController.getBackButton()) {
      Subsystems.intakeSubsystem.feed(0.5);
      
    } else {
      Subsystems.intakeSubsystem.feed(0);
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
