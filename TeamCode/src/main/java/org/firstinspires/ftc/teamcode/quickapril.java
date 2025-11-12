package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name="April Tag Vision", group="Pinkerton")
public class quickapril extends OpMode {

    AprilTagProcessor aprilTagProcessor;

    @Override
    public void init() {
        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawTagOutline(true)
                .setDrawTagID(true)
                .setDrawCubeProjection(true)
                .setDrawAxes(true)
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640,480))
                .addProcessor(aprilTagProcessor)
                .enableLiveView(true)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();
    }

    @Override
    public void loop() {
        if (!aprilTagProcessor.getDetections().isEmpty()) {
            AprilTagDetection ids = aprilTagProcessor.getDetections().get(0);
            telemetry.addData("ID", ids.id);
            telemetry.addData("X (L/R DISTANCE)", ids.ftcPose.x);
            telemetry.addData("Y (FORWARD/BACKWARD DISTANCE)", ids.ftcPose.y);
            telemetry.addData("Z (UP/DOWN DISTANCE)", ids.ftcPose.z);
            //telemetry.addData("roll", ids.ftcPose.roll);
            //telemetry.addData("pitch", ids.ftcPose.pitch);
            telemetry.addData("Yaw (Z-axis Rotation)", ids.ftcPose.yaw);
            if (ids.id == 23) {
                telemetry.addData("Key:", "P P G");
            }
            else if (ids.id == 22) {
                telemetry.addData("Key:", "P G P");
            }
            else {
                telemetry.addData("Key:", "G P P");
            }
        }
    }
}
