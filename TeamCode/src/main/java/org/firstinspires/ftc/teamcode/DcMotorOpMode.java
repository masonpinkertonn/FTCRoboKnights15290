package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Dc Motor Testing Ben Gies")
public class DcMotorOpMode extends OpMode {

    DcMotor rightFront;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor leftBack;

    @Override
    public void init() {
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        rightFront.setPower(gamepad1.right_trigger);
        leftFront.setPower(gamepad1.left_trigger);
        rightBack.setPower(gamepad1.right_stick_y);
        leftBack.setPower(gamepad1.left_stick_y);
    }
}
