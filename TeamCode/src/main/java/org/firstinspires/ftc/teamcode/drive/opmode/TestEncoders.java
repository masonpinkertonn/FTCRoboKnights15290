package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.util.Encoder;

@TeleOp
@Disabled
public class TestEncoders extends OpMode {
    Encoder frontEncoder;
    Encoder rightEncoder;
    Encoder leftEncoder;

    @Override
    public void init() {
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "launch1")); //no encoder launch1 or launch0 or leftBack or leftFront
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "launch0"));
        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "intake"));//works 100%
    }

    @Override
    public void loop() {
        telemetry.addData("Front Encoder", String.valueOf(frontEncoder.getCurrentPosition()));
        telemetry.addData("Left Encoder", String.valueOf(leftEncoder.getCurrentPosition()));
        telemetry.addData("Right Encoder", String.valueOf(rightEncoder.getCurrentPosition()));
    }
}
