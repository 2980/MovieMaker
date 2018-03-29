package com.mycompany.javacv;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;

public class Demo {

    public static void main(String[] args) throws FrameGrabber.Exception, InterruptedException, IOException {

        //FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("FerrisWheel640.mp4");
        //grabber.start();
        //CanvasFrame canvasFrame = new CanvasFrame("Extracted Frame", 1);
        //canvasFrame.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight());
        int width = 800;
        int height = 600;
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("Out.mp4", width, height);
        recorder.setVideoBitrate(4000000);

        recorder.start();

        Java2DFrameConverter j = new Java2DFrameConverter();

        List<Segment> segments = new ArrayList<Segment>();

        segments.add(new Segment("Clean.jpeg", 5, new FadeToBlack(2)));
        segments.add(new Segment("Tower.jpeg", 5, new FadeToBlack(2)));
        segments.add(new Segment("wedding.jpeg", 7, new FadeToBlack(2)));
        segments.add(new Segment("Huddle.jpeg", 5, new FadeToBlack(2)));

        for (Segment segment : segments) {
            BufferedImage bi = ImageIO.read(new File(segment.getFilename()));

            float framesPerSecond = 25;

            int framesInSegment = (int) (segment.getDuration() * framesPerSecond);

            for (int i = 0; i < framesInSegment; i++) {

                Frame newFrame = j.convert(bi);

                recorder.record(newFrame);
            }
            
            //Now do the transition
            
            Transition transition = segment.getTransition();
            
            if(transition == null)
                continue;
            
            int framesInTransition = (int) (transition.getDuration() * framesPerSecond);

            for (int i = 0; i < framesInTransition; i++) {

                
                BufferedImage transitionFrame = transition.getTransitionImage(bi, i/(float)framesPerSecond);
                
                Frame newFrame = j.convert(transitionFrame);

                recorder.record(newFrame);
            }
        }

        recorder.stop();

        recorder.release();

    }

}
