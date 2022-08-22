// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.Subsystems;

public class IntakeCommand extends CommandBase {
  /** Creates a new IntakeCommand. */
  public IntakeCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Subsystems.intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //SmartDashboard.putNumber("Ball Intaked", Subsystems.intakeSubsystem.ballIntaked());
    SmartDashboard.putNumber("Blue Value", Subsystems.intakeSubsystem.blueValue());
    SmartDashboard.putNumber("Red Value", Subsystems.intakeSubsystem.redValue());
    SmartDashboard.putNumber("Distance of Ball", Subsystems.intakeSubsystem.getDistance());
    SmartDashboard.putBoolean("Color Matches", Subsystems.intakeSubsystem.isBallColorCorrect());
    if (RobotContainer.operatorController.getBackButton()) {
      Subsystems.intakeSubsystem.feed(0.4);
    } else {
      Subsystems.intakeSubsystem.feed(0);
    }

    if (Subsystems.intakeSubsystem.ballIntaked() < 700) {
      if (RobotContainer.operatorController.getRightBumper()) {
        Subsystems.intakeSubsystem.runAllIntake(0.55);
      } else {
        Subsystems.intakeSubsystem.runAllIntake(0);
      }
     }

    if (RobotContainer.operatorController.getPOV() == 180) {
      Subsystems.intakeSubsystem.raiseArm(0.4);
    } else if (RobotContainer.operatorController.getPOV() == 0) {
      Subsystems.intakeSubsystem.lowerArm(0.4);
    }else{
      Subsystems.intakeSubsystem.lowerArm(0);
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
