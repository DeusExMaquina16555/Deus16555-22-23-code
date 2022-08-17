package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "autonomousencoder (Blocks to Java)", group = "")
public class autonomousencoder extends LinearOpMode {

  private DcMotor LMotor;
  private DcMotor RMotor;
  private Servo LClaw;
  private Servo RClaw;
  private Servo Arm_Y;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    LMotor = hardwareMap.dcMotor.get("LMotor");
    RMotor = hardwareMap.dcMotor.get("RMotor");
    LClaw = hardwareMap.servo.get("LClaw");
    RClaw = hardwareMap.servo.get("RClaw");
    Arm_Y = hardwareMap.servo.get("Arm_Y");

    // Put initialization blocks here.
    waitForStart();
    LMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    if (opModeIsActive()) {
      // Starting Position
      GrabObject(-0.5, 0.4, 0.2);
      // Drive Straight for 24 in
      MoveRobot(1600, 1600);
      // Pick Up Object
      GrabObject(0.07, 0.1, 0.2);
      // Turn CW 90 degrees
      MoveRobot(1000, -1000);
      // Drive Straight for 36 in
      MoveRobot(2800, 2800);
      // Turn CCW 90 degrees
      MoveRobot(-900, 900);
      // Drive straight for 48 in
      MoveRobot(4800, 4800);
    }
  }

  /**
   * Describe this function...
   */
  private void MoveRobot(double LeftDistance, double RightDistance) {
    LMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    RMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    LMotor.setTargetPosition(LeftDistance);
    RMotor.setTargetPosition(RightDistance);
    LMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    RMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    LMotor.setPower(1);
    RMotor.setPower(1);
    while (LMotor.isBusy() || RMotor.isBusy()) {
      telemetry.addData("Current Right Position", RMotor.getCurrentPosition());
      telemetry.addData("Current Left Position", LMotor.getCurrentPosition());
    }
    RMotor.setPower(0);
    LMotor.setPower(0);
    sleep(1000);
  }

  /**
   * Describe this function...
   */
  private void GrabObject(double RClaw2, double LClaw2, double Arm_Position) {
    LClaw.setPosition(LClaw2);
    RClaw.setPosition(RClaw2);
    sleep(1000);
    Arm_Y.setPosition(Arm_Position);
    sleep(1000);
  }
}
