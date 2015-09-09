/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elona.graphic.operation;

/**
 *
 * @author Owner
 */
public class GraphicOperationCoodinates extends GraphicOperation {
    int x;
    int y;
    
    public GraphicOperationCoodinates(int i, int argx, int argy) {
        super(i);
        x=argx;
        y=argy;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
}
