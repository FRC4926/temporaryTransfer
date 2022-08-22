// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer.Subsystems;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.CurryAuton;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static class Subsystems {
    public final static DriveSubsystem driveSubsystem = new DriveSubsystem();
    public final static LimeLightSubsystem limelightSubsystem = new LimeLightSubsystem();
    public final static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    public final static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    public final static ClimberSubsystem climberSubsystem = new ClimberSubsystem();
    public final static CurryAuton curryAuton = new CurryAuton();
    // Auton practice path
    // public final static CurryAuton curryAutonSubsystem = new CurryAuton();
  }

  public static final XboxController driveController = new XboxController(0);
  public static final XboxController operatorController = new XboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    return (new InstantCommand())
        .andThen((new WaitCommand(3))
            .deadlineWith((new InstantCommand().andThen((() -> Subsystems.driveSubsystem.arcadeDrive(-0.3, 0))))))
        .andThen(() -> Subsystems.intakeSubsystem.runAllIntake(0.3));


        
  }

}
