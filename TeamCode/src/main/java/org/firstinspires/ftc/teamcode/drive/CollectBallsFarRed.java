package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@TeleOp(name = "Red - Far Auto Ball Collection")
public class CollectBallsFarRed extends OpMode {
    DcMotor intake;
    DcMotor vector;

    DcMotor launch0;
    DcMotor launch1;

    CRServo corner;

    TrajectorySequence mySequence;

    SampleMecanumDrive drive;

    Pose2d start;

    @Override
    public void init() {
        intake = hardwareMap.get(DcMotor.class, "intake");
        vector = hardwareMap.get(DcMotor.class, "vector");

        launch0 = hardwareMap.get(DcMotor.class, "launch0");
        launch1 = hardwareMap.get(DcMotor.class, "launch1");

        corner = hardwareMap.get(CRServo.class, "corner");

        corner.setDirection(DcMotorSimple.Direction.REVERSE);

        launch0.setDirection(DcMotorSimple.Direction.REVERSE);
        launch1.setDirection(DcMotorSimple.Direction.REVERSE);

        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        drive = new SampleMecanumDrive(hardwareMap);

        start = new Pose2d(75, 25, Math.toRadians(90));

        drive.setPoseEstimate(start);

        mySequence = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                //.lineTo(new Vector2d(10, 0))
                //.turn(Math.toRadians(-135))
                /*.splineTo(new Vector2d(11.75, -28), Math.toRadians(-90))
                .forward(50)
                .waitSeconds(50)*/
                .lineToLinearHeading(new Pose2d(-25, 25, Math.toRadians(45)))
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
                .lineToLinearHeading(new Pose2d(-11.75, 28, Math.toRadians(90)))
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
                .lineToLinearHeading(new Pose2d(-25, 25, Math.toRadians(45)))
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
                .lineToLinearHeading(new Pose2d(11.75, 28, Math.toRadians(90)))
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
                .lineToLinearHeading(new Pose2d(-25, 25, Math.toRadians(45)))
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
                .lineToLinearHeading(new Pose2d(37.25, 28, Math.toRadians(90)))
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
                .lineToLinearHeading(new Pose2d(-25, 25, Math.toRadians(45)))
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

        drive.followTrajectorySequenceAsync(mySequence);
    }

    @Override
    public void loop() {
        drive.update();
        //drive.updatePoseEstimate();
        PoseStorage.currentPose = drive.getPoseEstimate();
    }
}
