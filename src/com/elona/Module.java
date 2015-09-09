package com.elona;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Module {

    public static WritableImage TransparentBMPImage(WritableImage wi){
        PixelReader pr = wi.getPixelReader();
        PixelWriter pw = wi.getPixelWriter();
        
        int transparentColor = pr.getArgb(0, 0);
        
        for (int i=0; i<wi.getWidth();i++){
            for (int j=0;j<wi.getHeight();j++){
                if (pr.getArgb(i,j)==transparentColor){
                    pw.setArgb(i, j, 0x000000FF);
                }                 
            }
        }
        
        return wi;
    }
    
    public static int limit(int i,int min,int max){
        if (i>max) {
            return max;
        } else if (i<min) {
            return min;
        } else {
            return i;
        }
    }
    
    public static int differenceByLimit(int i,int min,int max){
        if (i>max) {
            return i-max;
        } else if (i<min) {
            return i-min;
        } else {
            return 0;
        }
    }
    
}
