package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToTopCmd extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorToTopCmd(ElevatorSubsystem newElevSub) {
    elevSub = newElevSub;
  
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {
    SmartDashboard.putBoolean("TopPosition?", false);
  }

  @Override
  public void execute() {
    // Top/Amp is 174
    elevSub.setSetpoint(168); //used to be 168
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
    SmartDashboard.putBoolean("TopPosition?", true);
  }  

  @Override
  public boolean isFinished() {
    return elevSub.getTopLimitSwitch() || elevSub.isAtSetpoint();
  }
}
