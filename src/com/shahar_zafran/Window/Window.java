package com.shahar_zafran.Window;

import org.opencv.core.Mat;

import javax.swing.*;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by Shahar Zafran on 06/03/2017.
 */
public class Window extends JFrame {

    //region Constant
    private static final int WIDTH = 320;
    private static final int HEIGHT = 240;
    //endregion

    //region Variables
    private JLabel _image_label;
    //endregion

    //region Constructor
    public Window() {


        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
        init();
    }
    //endregion

    //region Class Methods
    public void init() {
        this._image_label = new JLabel();
        this.getContentPane().add(this._image_label);
    }

    public void draw(Mat frame) {

        _image_label.setIcon(new ImageIcon(mat2Img(frame)));
    }

    public static BufferedImage mat2Img(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;

    }
    //endregion

    //region Override Methods
    //endregion

    //region Get Methods
    //endregion

    //region Set Methods
    //endregion

}
