package org.firstinspires.ftc.teamcode.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;


public class ArtifactPipeline extends OpenCvPipeline {
    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);

    private Mat ycrcbMat = new Mat();
    private Mat binaryMat = new Mat();
    private Mat maskedInputMat = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, ycrcbMat, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(ycrcbMat, ycrcbMat, Imgproc.COLOR_RGB2YCrCb);
        Core.inRange(ycrcbMat, lower, upper, ycrcbMat);
        maskedInputMat.release();
        Core.bitwise_and(input, input, maskedInputMat, ycrcbMat);
        return maskedInputMat;
        //Core.inRange(ycrcbMat, lower, upper, binaryMat);
        //maskedInputMat.release();
        //Core.bitwise_and(input, input, maskedInputMat, binaryMat);
        //return maskedInputMat;
        //return ycrcbMat;
    }

    @Override
    public void onViewportTapped() {
        //
    }
}
