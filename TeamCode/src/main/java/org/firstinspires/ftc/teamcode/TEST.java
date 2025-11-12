package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Test intake")
public class TEST extends OpMode {

    DcMotor intake;
    DcMotor vector;
    CRServo corner;

    @Override
    public void init() {
        intake = hardwareMap.get(DcMotor.class, "intake");
        vector = hardwareMap.get(DcMotor.class, "vector");
        corner = hardwareMap.get(CRServo.class, "corner");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        corner.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        intake.setPower(gamepad1.left_trigger);
        corner.setPower(gamepad1.right_trigger);
    }
}
