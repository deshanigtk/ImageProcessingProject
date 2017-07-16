/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.imageprocessing;

/**
 *
 * @author deshani
 */
public class MeanFilter {

    int[][] mask;

    public void initNormalMask(int n) {
        mask = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mask[i][j] = 1;
            }
        }
    }

    public void initPriorityMask(int n) {
        mask = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == Math.ceil(n / 2) && j == Math.ceil(n / 2)) {
                    mask[i][j] = 2;
                }
                mask[i][j] = 1;
            }
        }
    }
    
    public void initGaussianMask(){
        mask=new int[5][5];
        
    }
}
