package org.firstinspires.ftc.teamcode.vision;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.List;

public class GrayProcessor implements VisionProcessor {

    org.opencv.core.Rect rectLeft = new org.opencv.core.Rect(20, 20, 800, 1000);
    org.opencv.core.Rect rectRight = new org.opencv.core.Rect(920, 20, 800, 1000);
    Selected selection = Selected.NONE;

    Mat submat = new Mat();
    Mat ycrcbMat = new Mat();

    private android.graphics.Rect makeGraphicsRect(org.opencv.core.Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left, top, right, bottom);
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {

    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, ycrcbMat, Imgproc.COLOR_RGB2YCrCb);

        double satRectLeft;

        return null;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        Paint selectedPaint = new Paint();
        selectedPaint.setColor(Color.RED);
        selectedPaint.setStyle(Paint.Style.STROKE);
        selectedPaint.setStrokeWidth(scaleCanvasDensity*4);

        Paint nonSelectedPaint = new Paint(selectedPaint);
        nonSelectedPaint.setColor(Color.GREEN);

        if (selection == Selected.NONE) {
            canvas.drawRect(makeGraphicsRect(rectLeft, scaleBmpPxToCanvasPx), nonSelectedPaint);
            canvas.drawRect(makeGraphicsRect(rectRight, scaleBmpPxToCanvasPx), nonSelectedPaint);
        }
        else if (selection == Selected.LEFT) {
            canvas.drawRect(makeGraphicsRect(rectLeft, scaleBmpPxToCanvasPx), selectedPaint);
            canvas.drawRect(makeGraphicsRect(rectRight, scaleBmpPxToCanvasPx), nonSelectedPaint);
        }
        else {
            canvas.drawRect(makeGraphicsRect(rectLeft, scaleBmpPxToCanvasPx), nonSelectedPaint);
            canvas.drawRect(makeGraphicsRect(rectRight, scaleBmpPxToCanvasPx), selectedPaint);
        }
    }

    public enum Selected {
        NONE,
        LEFT,
        RIGHT
    }
}
