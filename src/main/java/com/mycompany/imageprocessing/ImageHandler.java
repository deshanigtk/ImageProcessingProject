/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.imageprocessing;

import java.awt.image.BufferedImage;
import java.util.HashMap;

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
    }
    
    public static BufferedImage getCurrentImage(){
        return (BufferedImage) historyImages.get(historyImages.size()-1);
    }
    
    public static void undo(){
        setCurrentImage((BufferedImage) historyImages.get(historyImages.size()-1));
    }
}
