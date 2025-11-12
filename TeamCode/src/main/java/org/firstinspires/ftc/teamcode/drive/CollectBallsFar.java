package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@TeleOp(name = "Blue - Far Auto Ball Collection")
public class CollectBallsFar extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor intake = hardwareMap.get(DcMotor.class, "intake");
        DcMotor vector = hardwareMap.get(DcMotor.class, "vector");

        DcMotor launch0 = hardwareMap.get(DcMotor.class, "launch0");
        DcMotor launch1 = hardwareMap.get(DcMotor.class, "launch1");

        CRServo corner = hardwareMap.get(CRServo.class, "corner");
        corner.setDirection(DcMotorSimple.Direction.REVERSE);

        launch0.setDirection(DcMotorSimple.Direction.REVERSE);
        launch1.setDirection(DcMotorSimple.Direction.REVERSE);

        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d start = new Pose2d(75, -25, Math.toRadians(-90));

        drive.setPoseEstimate(start);

        TrajectorySequence mySequence = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                //.lineTo(new Vector2d(10, 0))
                //.turn(Math.toRadians(-135))
                /*.splineTo(new Vector2d(11.75, -28), Math.toRadians(-90))
                .forward(50)
                .waitSeconds(50)*/
                .lineToLinearHeading(new Pose2d(-25, -25, Math.toRadians(-45)))
                .addTemporalMarker(() -> {
                    intake.setPower(0.6);
                    launch0.setPower(1.0);
                    launch1.setPower(1.0);
                    corner.setPower(1.0);
                })
                .waitSeconds(7.5)
                .addTemporalMarker(() -> {
                    intake.setPower(0.0);
                    launch0.setPower(0.0);
                    launch1.setPower(0.0);
                    corner.setPower(0.0);
                })
                /*.addTemporalMarker(6, () -> {
                    launch0.setPower(1.0);
                    launch1.setPower(1.0);
                })
                .addTemporalMarker(20, () -> {
                    launch0.setPower(1.0);
                    launch1.setPower(1.0);
                })*/
                .lineToLinearHeading(new Pose2d(-11.75, -28, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    intake.setPower(0.6);
                    vector.setPower(1.0);
                })
                //.waitSeconds(0.5)
                .forward(25)
                .addDisplacementMarker(() -> {
                    intake.setPower(0.0);
                    vector.setPower(0.0);
                })
                //.back(25)
                .lineToLinearHeading(new Pose2d(-25, -25, Math.toRadians(-45)))
                //.turn(Math.toRadians(-45))
                .addTemporalMarker(() -> {
                    intake.setPower(0.6);
                    launch0.setPower(1.0);
                    launch1.setPower(1.0);
                    corner.setPower(1.0);
                })
                .waitSeconds(7.5)
                .addTemporalMarker(() -> {
                    intake.setPower(0.0);
                    launch0.setPower(0.0);
                    launch1.setPower(0.0);
                    corner.setPower(0.0);
                })
                //.turn(Math.toRadians(180))
                .lineToLinearHeading(new Pose2d(11.75, -28, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    intake.setPower(0.6);
                    vector.setPower(1.0);
                })
                .waitSeconds(0.1)
                .forward(26)
                .addDisplacementMarker(() -> {
                    intake.setPower(0.0);
                    vector.setPower(0.0);
                })
                //.back(25)
                .lineToLinearHeading(new Pose2d(-25, -25, Math.toRadians(-45)))
                //.turn(Math.toRadians(-45))
                .addTemporalMarker(() -> {
                    intake.setPower(0.6);
                    launch0.setPower(1.0);
                    launch1.setPower(1.0);
                    corner.setPower(1.0);
                })
                .waitSeconds(7.5)
                .addTemporalMarker(() -> {
                    intake.setPower(0.0);
                    launch0.setPower(0.0);
                    launch1.setPower(0.0);
                    corner.setPower(0.0);
                })
                //.turn(Math.toRadians(180))
                .lineToLinearHeading(new Pose2d(37.25, -28, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    intake.setPower(0.6);
                    vector.setPower(1.0);
                })
                .waitSeconds(0.1)
                .forward(30)
                .addDisplacementMarker(() -> {
                    intake.setPower(0.0);
                    vector.setPower(0.0);
                })
                //.back(25)
                .lineToLinearHeading(new Pose2d(-25, -25, Math.toRadians(-45)))
                //.turn(Math.toRadians(-45))
                .addTemporalMarker(() -> {
                    intake.setPower(0.6);
                    launch0.setPower(1.0);
                    launch1.setPower(1.0);
                    corner.setPower(1.0);
                })
                .waitSeconds(7.5)
                .addTemporalMarker(() -> {
                    intake.setPower(0.0);
                    launch0.setPower(0.0);
                    launch1.setPower(0.0);
                    corner.setPower(0.0);
                })
                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(mySequence);
    }
}
