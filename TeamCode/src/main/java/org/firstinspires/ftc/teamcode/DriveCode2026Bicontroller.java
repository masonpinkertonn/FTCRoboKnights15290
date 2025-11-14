package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

@TeleOp(name="DriveCode2026BicontrollerBlue", group="TeleOp")
public class DriveCode2026Bicontroller extends LinearOpMode {

    SampleMecanumDrive drive;

    // Drive motors
    private DcMotor rightFront, rightBack, leftBack, leftFront;
    // Other motors
    private DcMotor vector, intake, launch0, launch1;
    // Continuous rotation servo
    private CRServo corner;

    private Pose2d startPose;

    @Override
    public void runOpMode() {

        drive = new SampleMecanumDrive(hardwareMap);

        startPose = PoseStorage.currentPose;

        drive.setPoseEstimate(startPose);

        // --- Initialize motors ---
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        vector = hardwareMap.get(DcMotor.class, "vector");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launch0 = hardwareMap.get(DcMotor.class, "launch0");
        launch1 = hardwareMap.get(DcMotor.class, "launch1");
        launch0.setDirection(DcMotorSimple.Direction.REVERSE);
        launch1.setDirection(DcMotorSimple.Direction.REVERSE);

        // --- Initialize CRServo ---
        corner = hardwareMap.get(CRServo.class, "corner");

        // Motor directions
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        vector.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);
        corner.setDirection(DcMotor.Direction.REVERSE);


        // Stop servo initially
        corner.setPower(0);

        telemetry.addLine("Ready to run");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Joystick values
            double driveY = -gamepad1.left_stick_y; // forward/back
            double driveX = gamepad1.left_stick_x;  // strafe
            double turn   = gamepad1.right_stick_x;   // rotation

            // Mecanum drive formula
            double rf = driveY - driveX - turn;
            double rb = driveY + driveX - turn;
            double lf = driveY + driveX + turn;
            double lb = driveY - driveX + turn;

            // Normalize so max is 1
            double max = Math.max(Math.abs(rf), Math.max(Math.abs(rb), Math.max(Math.abs(lf), Math.abs(lb))));
            if (max > 1.0) {
                rf /= max;
                rb /= max;
                lf /= max;
                lb /= max;
            }

            // Set motor powers
            rightFront.setPower(rf);
            rightBack.setPower(rb);
            leftFront.setPower(lf);
            leftBack.setPower(lb);

            // Intake motor control (B button) //yeah I know they're backwards it works
            if (gamepad2.b) {
                vector.setPower(-1);
                intake.setPower(1);

                // Vector motor control (Right bumper)
            } else if (gamepad2.right_bumper) {
                intake.setPower(-1);

                //Corner CRServo control (X button)
            } else if (gamepad2.x) {
                corner.setPower(1);
                intake.setPower(1);
            } else {
                corner.setPower(0);
                intake.setPower(0);
                vector.setPower(0);
            }

            //launch button (Y button)
            if(gamepad2.y){
                launch0.setPower(0.5);
                launch1.setPower(0.5);
            }else{
                launch0.setPower(0);
                launch1.setPower(0);
            }

            //Long Launch (A button)
            if(gamepad2.a){
                launch0.setPower(1);
                launch1.setPower(1);
            }else{
                launch0.setPower(0);
                launch1.setPower(0);
            }
            
            if (gamepad1.a)
            {
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(drive.getPoseEstimate())
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
                        .build());
            }

            telemetry.addData("Pose:", drive.getPoseEstimate());

            telemetry.update();

            drive.update();

            idle();
        }
    }
}
