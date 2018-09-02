package com.shahar_zafran;


import com.shahar_zafran.Window.Window;
import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.io.File;

import static org.opencv.imgproc.Imgproc.rectangle;

public class Main {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public static void main(String[] args) {
	// write your code here

        Window window = new Window();
        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened())
            System.err.println("Could Not Open Camera");

        CascadeClassifier faceDetector = new CascadeClassifier("cars.xml");
        Mat frame = new Mat();

        while (camera.isOpened()) {
            camera.read(frame);
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(frame, faceDetections);
            // Draw a bounding box around each face.
            for (Rect rect : faceDetections.toArray()) {
                rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
            }
            window.draw(frame);
        }
    }
}
