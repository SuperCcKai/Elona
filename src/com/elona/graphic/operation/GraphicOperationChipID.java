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
public class GraphicOperationChipID extends GraphicOperation {
    int[][] mapChipID;
    public GraphicOperationChipID(int i, int[][] arg) {
        super(i);
        mapChipID = arg;
    }
    
    public int[][] getMapChipID(){
        return mapChipID;
    }
    
}

