package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "berthateleop (Blocks to Java)", group = "")
public class berthateleop extends LinearOpMode {

  private DcMotor RMotor;
  private DcMotor LMotor;
  private Servo Arm_Y;
  private Servo LClaw;
  private Servo RClaw;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    float tgtpower;

    RMotor = hardwareMap.dcMotor.get("RMotor");
    LMotor = hardwareMap.dcMotor.get("LMotor");
    Arm_Y = hardwareMap.servo.get("Arm_Y");
    LClaw = hardwareMap.servo.get("LClaw");
    RClaw = hardwareMap.servo.get("RClaw");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        tgtpower = gamepad1.left_stick_y;
        RMotor.setPower(-tgtpower);
        LMotor.setPower(tgtpower);
        tgtpower = gamepad1.left_stick_x;
        LMotor.setPower(tgtpower);
        RMotor.setPower(tgtpower);
        if (gamepad1.dpad_up) {
          Arm_Y.setPosition(0.8);
        }
        if (gamepad1.dpad_down) {
          Arm_Y.setPosition(0.2);
        }
        if (gamepad1.a) {
          LClaw.setPosition(0.4);
          RClaw.setPosition(-0.4);
        }
        if (gamepad1.b) {
          LClaw.setPosition(0.1);
          RClaw.setPosition(0.07);
        }
        telemetry.update();
      }
    }
  }
}
