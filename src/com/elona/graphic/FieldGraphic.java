/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elona.graphic;

import com.elona.Module;
import static com.elona.Module.TransparentBMPImage;
import com.elona.constants.Operation;
import com.elona.graphic.operation.GraphicOperation;
import com.elona.graphic.operation.GraphicOperationCoodinates;
import com.elona.graphic.operation.GraphicOperationChipID;
import java.io.File;
import java.util.Queue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

public class FieldGraphic {
    
    int frame = 0;
    
    int windowWidth = 800;
    int windowHeight = 600;
    int infoAreaWidth, infoAreaHeight;
    int fieldAreaWidth, fieldAreaHeight;
    int mapChipTileSize = 48;
    int charaChipTileSize = 48;
    
    Pane root;
    
    int mapXlen,mapYlen;
    int mapChipID[][];
    
    Canvas mapChipLayerCanvas;
    WritableImage mapChipLayer;
    GraphicsContext mapChipLayerGraphicer;
        
    Canvas charaChipLayerCanvas;
    GraphicsContext charaChipLayerGraphicer;
    
    Image charaChipImage;
    PixelReader charaChipReader;
    WritableImage boueie;
        
    Canvas infoLayerCanvas;
    GraphicsContext infoLayerGraphicer;
    
    Image interfaceImage;
    PixelReader interfaceReader;
        
    WritableImage miniMapBase;
    WritableImage logWindowBase;
    WritableImage infoBarBase;
    
    GraphicOperation currentTask;
    Queue<GraphicOperation> graphicOperationQueue;

    public FieldGraphic(Pane pane, Queue<GraphicOperation> q, int width, int height){
        root = pane;
        graphicOperationQueue=q;
        
        windowWidth = width;
        windowHeight = height;
        
        infoAreaWidth = windowWidth;
        infoAreaHeight = 87;
    
        fieldAreaWidth = windowWidth;
        fieldAreaHeight = windowHeight - infoAreaHeight;
        
        mapChipLayerCanvas = new Canvas(windowWidth,windowHeight);
        mapChipLayerGraphicer = mapChipLayerCanvas.getGraphicsContext2D();
        root.getChildren().add(mapChipLayerCanvas);
        
        charaChipLayerCanvas = new Canvas(windowWidth,windowHeight);
        charaChipLayerGraphicer = charaChipLayerCanvas.getGraphicsContext2D();
        root.getChildren().add(charaChipLayerCanvas);
        
        charaChipImage = new Image("File:"+System.getProperty("user.dir")+"\\graphic\\character.bmp");
        charaChipReader = charaChipImage.getPixelReader();
        boueie = TransparentBMPImage(new WritableImage(charaChipReader, 528, 336, charaChipTileSize, charaChipTileSize));
        
        infoLayerCanvas = new Canvas(windowWidth,windowHeight);
        infoLayerGraphicer = infoLayerCanvas.getGraphicsContext2D();
        root.getChildren().add(infoLayerCanvas);
        
        //interfaceImage = new Image("graphic/interface.bmp");
        interfaceImage = new Image("File:"+System.getProperty("user.dir")+"\\graphic\\interface.bmp");
        interfaceReader = interfaceImage.getPixelReader();
        
        miniMapBase = new WritableImage(interfaceReader, 120, 505, 123, 87);
        infoLayerGraphicer.drawImage(miniMapBase, 0, windowHeight-infoAreaHeight);
        
        logWindowBase = new WritableImage(interfaceReader, 496, 528, 192, 72);
        for(int i=0;123+i*192<infoAreaWidth;i++){            
            infoLayerGraphicer.drawImage(logWindowBase, 123+i*192, windowHeight-infoAreaHeight);
        }
        
        infoBarBase = new WritableImage(interfaceReader, 0, 439, 192, 16);
        for(int i=0;123+i*192<infoAreaWidth;i++){            
            infoLayerGraphicer.drawImage(infoBarBase, 123+i*192, windowHeight-infoAreaHeight+72);
        }
    }
    
    public void update(){
        currentTask = (GraphicOperation) graphicOperationQueue.poll();
        if (currentTask==null) return;
        
        for (;currentTask!=null;currentTask = (GraphicOperation) graphicOperationQueue.poll()){
            switch(currentTask.getGraphicOparation()){
            case Operation.relocatePC:
                relocatePC(((GraphicOperationCoodinates) currentTask).getX(),((GraphicOperationCoodinates) currentTask).getY());
                break;
            case Operation.initializeMap:
                initializeMap(((GraphicOperationChipID) currentTask).getMapChipID());
                break;
            }
        }
    }    
    
    public void initializeMap(int[][] arg){
        mapChipID=arg;
        mapXlen = mapChipID.length;
        mapYlen = mapChipID[0].length;
        
        Image mapChipImage = new Image("File:"+System.getProperty("user.dir")+"\\graphic\\map1.bmp");
        PixelReader mapChipReader = mapChipImage.getPixelReader();
        
        mapChipLayer = new WritableImage(mapXlen*mapChipTileSize, mapYlen*mapChipTileSize);
        PixelWriter pw = mapChipLayer.getPixelWriter();
        int mapChipWidth = (int) mapChipImage.getWidth();
        
        for (int i=0;i<mapXlen;i++) {
            for(int j=0;j<mapYlen;j++){
                pw.setPixels(i*mapChipTileSize, j*mapChipTileSize, mapChipTileSize, mapChipTileSize, mapChipReader, (mapChipTileSize*mapChipID[i][j])%mapChipWidth, ((mapChipTileSize*mapChipID[i][j])/mapChipWidth)*mapChipTileSize);
            }
        }
    }
    
    public void relocatePC(int x,int y){
        mapChipLayerGraphicer.drawImage(mapChipLayer, Module.limit(fieldAreaWidth/2-x*charaChipTileSize-charaChipTileSize/2, fieldAreaWidth-mapXlen*charaChipTileSize, 0),Module.limit(fieldAreaHeight/2-y*charaChipTileSize-charaChipTileSize/2, fieldAreaHeight-mapYlen*charaChipTileSize, 0));
        charaChipLayerGraphicer.clearRect(0, 0, windowWidth, windowHeight);
        charaChipLayerGraphicer.drawImage(boueie, fieldAreaWidth/2-charaChipTileSize/2-Module.differenceByLimit(fieldAreaWidth/2-x*charaChipTileSize-charaChipTileSize/2, fieldAreaWidth-mapXlen*charaChipTileSize, 0),fieldAreaHeight/2-charaChipTileSize/2-Module.differenceByLimit(fieldAreaHeight/2-y*charaChipTileSize-charaChipTileSize/2, fieldAreaHeight-mapYlen*charaChipTileSize, 0)-(mapChipID[x][y]==168 ? 0 : charaChipTileSize/3), charaChipTileSize, charaChipTileSize);
    }
}
