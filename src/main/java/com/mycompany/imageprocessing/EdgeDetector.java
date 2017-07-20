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
public class EdgeDetector {

    public static float threshold = 0;

    public static BufferedImage getPrewittHImage() {
        int[][] mask = new int[][]{{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
        return convolute(mask);
    }

    public static BufferedImage getPrewittVImage() {
        int[][] mask = new int[][]{{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}};
        return convolute(mask);
    }

    public static BufferedImage getSobelHImage() {
        int[][] mask = new int[][]{{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        return convolute(mask);
    }

    public static BufferedImage getSobelVImage() {
        int[][] mask = new int[][]{{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        return convolute(mask);
    }

    public static BufferedImage getLaplacianImage() {
        int[][] mask = new int[][]{{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
        return convolute(mask);
    }

    private static BufferedImage convolute(int[][] mask) {

        YCrCbImage image = ImageHandler.getCurrentYCrCbImage();
        BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int sum = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (i - k >= 0 && i - k < width && j - l >= 0 && j - l < height) {
                            sum += image.getY(i - k, j - l) * mask[k][l];
                        }
                    }
                }
                if (sum > threshold) {
                    temp.setRGB(i, j, new Color(0, 0, 0).getRGB());
                } else {
                    temp.setRGB(i, j, new Color(255, 255, 255).getRGB());
                }
            }
        }

        return temp;
    }
}
