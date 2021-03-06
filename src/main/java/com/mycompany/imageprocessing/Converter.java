/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author deshani
 */
public class Converter {

    public static BufferedImage getNegativeImage() {
        BufferedImage image = ImageHandler.getCurrentImage();
        BufferedImage tempImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                Color newColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
                tempImage.setRGB(i, j, newColor.getRGB());
            }
        }
        return tempImage;
    }

    public static BufferedImage getGrayscaleImage() {
        YCrCbImage image = new YCrCbImage(ImageHandler.getCurrentImage());
        BufferedImage tempImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getY(i, j), image.getY(i, j), image.getY(i, j));
                tempImage.setRGB(i, j, color.getRGB());
            }
        }
        return tempImage;
    }

}
