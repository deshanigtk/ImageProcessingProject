/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.imageprocessing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author deshani
 */
public class Filter {

    public static int alpha = 0;
    public static int type = 0;

    public static BufferedImage meanFilter() {
        YCrCbImage image = ImageHandler.getCurrentYCrCbImage();

        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int sum = 0;
                int count = 9;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < width && l >= 0 && l < height) {
                            sum += image.getY(k, l);
                        } else {
                            count--;
                        }
                    }
                }
                image.setY(i, j, sum / count);
            }
        }

        return image.getRGBImage();
    }

    public static BufferedImage alphaTrimmedMeanFilter() {
        YCrCbImage image = ImageHandler.getCurrentYCrCbImage();
        int h = image.getHeight();
        int w = image.getWidth();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int count = 9;
                ArrayList<Integer> pixels = new ArrayList<>();
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < w && l >= 0 && l < h) {
                            pixels.add(image.getY(k, l));
                        } else {
                            count--;
                        }
                    }
                }
                Collections.sort(pixels);
                int f_value = 0;
                int f_count = 0;
                int f_alpha = alpha;
                if (count != 9) {
                    f_alpha = alpha - (((9 - count) / 2) + 1);
                    if (f_alpha < 0) {
                        f_alpha = 0;
                    }
                }
                for (int m = f_alpha; m < pixels.size() - f_alpha; m++) {
                    f_value += pixels.get(m);
                    f_count++;
                }
                image.setY(i, j, f_value / f_count);
            }
        } return image.getRGBImage();
    }

    public static BufferedImage medianFilter() {
        YCrCbImage image = ImageHandler.getCurrentYCrCbImage();

        int h = image.getHeight();
        int w = image.getWidth();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                ArrayList<Integer> pixels = new ArrayList<>();
                int count = 9;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < w && l >= 0 && l < h) {
                            pixels.add(image.getY(k, l));
                        } else {
                            count--;
                        }
                    }
                }
                Collections.sort(pixels);
                if (count % 2 != 0) {
                    image.setY(i, j, pixels.get(((count - 1) / 2) + 1));
                } else {
                    image.setY(i, j, pixels.get(count / 2));
                }
            }
        }
        return image.getRGBImage();
    }
}
