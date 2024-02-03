// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSubsystem;

public class ManualPivotCmd extends Command {

  PivotSubsystem pivotSub = new PivotSubsystem();
  double moveSpeed;

  public ManualPivotCmd(PivotSubsystem pivotSubsystem, double speed){
    pivotSubsystem = pivotSub;
    moveSpeed = speed;

    addRequirements(pivotSub);
  }

  @Override
  public void initialize(){
    pivotSub.setManualSpeed(moveSpeed);
  }

  @Override
  public void execute(){

  }

  @Override
  public void end(boolean interrupted){

  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
