// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PivotCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PivotConstants;
import frc.robot.Constants.PivotConstants;
import frc.robot.subsystems.PivotSubsystem;

public class ManualPivotCommand extends Command {

  PivotSubsystem pivotSub;
  DoubleSupplier moveSpeed;

  public ManualPivotCommand(PivotSubsystem pivotSubsystem, DoubleSupplier speed){
    pivotSub = pivotSubsystem;
    moveSpeed = speed;

    addRequirements(pivotSub);
  }

  @Override
  public void initialize(){
    pivotSub.init();
    pivotSub.disablePid();
  }

  @Override
  public void execute(){
    if (Math.abs(moveSpeed.getAsDouble()) > PivotConstants.MAX_PIVOT_SPEED){
      pivotSub.setManualSpeed(Math.copySign(PivotConstants.MAX_PIVOT_SPEED, moveSpeed.getAsDouble()));
    }
    else{
      pivotSub.setManualSpeed(moveSpeed.getAsDouble());
    } 
  }

  @Override 
  public void end(boolean interrupted){
    
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}