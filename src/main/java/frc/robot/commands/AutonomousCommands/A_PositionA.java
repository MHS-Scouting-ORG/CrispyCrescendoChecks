package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.CrispyPositionCommands.FeedToIndexer;
import frc.robot.commands.ElevatorCommands.ElevatorToTransferCmd;
import frc.robot.commands.IntakeCommands.IntakeCmd;
import frc.robot.commands.PivotCommands.PivotPidCommand;
import frc.robot.commands.ShindexerCommands.IndexToShooterAutoCommand;
import frc.robot.commands.ShindexerCommands.IndexToShooterCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class A_PositionA extends SequentialCommandGroup {

  public A_PositionA(SwerveSubsystem swerveSub, UnderIntakeSubsystem intakeSub, IndexerSubsystem indexSub, ShooterSubsystem shooterSub, ElevatorSubsystem elevSub, PivotSubsystem pivotSub) {
    int red = 1;
    if (DriverStation.getAlliance().get() == Alliance.Red) {
      red = -1;
    }
    addCommands(
      new InstantCommand(() -> swerveSub.resetOdometry(new Pose2d(0, 0, new Rotation2d()))),

      new InstantCommand(() -> swerveSub.zeroHeading()), 

      new InstantCommand(() -> swerveSub.setNavxOffset(-60)),

        new IndexToShooterAutoCommand(shooterSub, indexSub), // shoot preload

        // Runs intake and drives to note. At the same time, sets elevator and pivot
        new ParallelCommandGroup(
            new IntakeCmd(intakeSub),

            new S_DriveToPositionCommand(swerveSub, 6.5, red * 2, 0, false),//, // FIXME rotation to be determined

           new PivotPidCommand(pivotSub, 32)
           ), 
        
      new S_DriveToPositionCommand(swerveSub, 6.5, red * 2, 92, true), 

       new FeedToIndexer(indexSub, intakeSub),

       new IndexToShooterAutoCommand(shooterSub, indexSub) // shoot second note
    );
  }
}
