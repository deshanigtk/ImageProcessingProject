/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.imageprocessing;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author deshani
 */
public class ImageHandler {

    private static BufferedImage currentImage = null;
    private static YCrCbImage currentYCrCbImage = null;
    private static HashMap historyImages=new HashMap<Integer, BufferedImage>();
    private static int count=0;

    public static void setCurrentImage(BufferedImage bufferedImage) {
        currentImage = bufferedImage;
        historyImages.put(count,currentImage);
        count++;
         if (currentImage != null) {
            historyImages.put(count, currentImage);
            count++;
        }
        currentImage = bufferedImage;
        currentYCrCbImage = new YCrCbImage(bufferedImage);
    }
    
    public static BufferedImage getCurrentImage(){
        return currentImage;
    }
    
    public static void undo(){
        setCurrentImage((BufferedImage) historyImages.get(historyImages.size()-1));
    }
    
    public static YCrCbImage getCurrentYCrCbImage(){
        return currentYCrCbImage;
    }
     public static void reset() {
        int size = historyImages.size();
        if (size > 0) {
            setCurrentImage((BufferedImage) historyImages.get(0));
        }
    }

    public static HashMap<Integer, String> getHistoryDetails() {
        return historyImages;
    }

    public static void saveImage(JFrame context) {
        if (getCurrentImage() != null) {
            JFileChooser locationChooser = new JFileChooser();
            if (locationChooser.showSaveDialog(context) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = new File(locationChooser.getSelectedFile().getAbsolutePath() + ".png");
                    ImageIO.write(getCurrentImage(), "png", file);
                    JOptionPane.showMessageDialog(locationChooser, "FILE SAVED");
                } catch (HeadlessException | IOException ex) {
                    JOptionPane.showMessageDialog(locationChooser, "FILE NOT SAVED");
                }
            }
        }
    }
}
