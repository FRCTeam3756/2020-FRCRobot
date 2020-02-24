/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.music.Orchestra;

import frc.robot.Subsystems.DriveTrain;
import frc.robot.Falcon_500_Config;
import frc.robot.Subsystems.IntakeSole;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


 // Orchestra orchestra;

  //String song = "megalovania.chrp";

  final int kUnitsPerRevolution = 2048;

  final TalonFXInvertType kInvertType = TalonFXInvertType.CounterClockwise; // <<< What direction you want "forward/up" to be.
  final TalonFXInvertType rightInvertType = TalonFXInvertType.Clockwise;

  final NeutralMode kBrakeDurNeutral = NeutralMode.Brake;

  TalonFX _talonLeftMaster = new TalonFX(3);
  TalonFX _talonLeftSlave = new TalonFX(4);
  TalonFX _talonRightMaster = new TalonFX(5);
  TalonFX _talonRightSlave = new TalonFX(6);

  //TalonFX[] instruments = {_talonLeftMaster, _talonLeftSlave, _talonRightMaster, _talonRightSlave};

  Joystick _joy = new Joystick(0);

  //int _loops = 0;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //int encoderPos =_talonRightMaster.getSelectedSensorPosition();
  }
  

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
//    _talonLeftMaster.set(ControlMode.MotionMagic, _joy.getY()*4096*10);
//    _talonRightMaster.set(ControlMode.MotionMagic, _joy.getY()*4096*-10);

    
  }
  

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code \
   * works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Falcon_500_Config._talonLeftMaster.set(ControlMode.MotionMagic, 4096*10);
    Falcon_500_Config._talonRightMaster.set(ControlMode.MotionMagic, 4096*-10);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopInit() {
    //orchestra.loadMusic(song);
  }
  @Override
  public void teleopPeriodic() {
    //orchestra.play();
    DriveTrain.getInstance().drive(OI.getInstance().getJoystick1());
    IntakeSole.getInstance().action(OI.getInstance().getJoystick1());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
