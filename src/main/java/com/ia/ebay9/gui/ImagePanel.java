package com.ia.ebay9.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public void setImage(BufferedImage image) {
        this.image=image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if ( image!=null ) {
            final double width;
            final double height;
            if ( image.getWidth()>image.getHeight() ) {
                final double scale = (getWidth()*1f)/image.getWidth();
                width = getWidth();
                height = image.getHeight()*scale;
            }
            else {
                final double scale = (getHeight()*1f)/image.getHeight();
                height = getHeight();
                width = image.getWidth()*scale;
            }
            final double x = (getWidth()-width)/2;
            final double y = (getHeight()-height)/2;
            g.drawImage(image, (int)x, (int)y, (int)width, (int)height, this);
        }
    }

}