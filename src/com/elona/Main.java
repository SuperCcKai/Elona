package com.elona;

import com.elona.graphic.FieldGraphic;
import com.elona.graphic.operation.GraphicOperation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    
    int windowWidth = 800;
    int windowHeight = 600;
    
    @Override
    public void start(Stage stage) throws InterruptedException {
        Pane root = new Pane();
        Scene scene = new Scene(root, windowWidth-10, windowHeight-10);
        
        stage.setTitle("Elona Java ver 0.00");
        stage.setResizable(false);
        stage.setScene(scene);
        
        Queue<GraphicOperation> graphicOperationQueue = new LinkedList<>();
        FieldGraphic fieldGraphicer = new FieldGraphic(root, graphicOperationQueue, windowWidth, windowHeight);
        
        ArrayList<KeyCode> input = new ArrayList<>();
 
        scene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            if ( !input.contains(code) )
                input.add( code );
        });
 
        scene.setOnKeyReleased((KeyEvent e) -> {
            KeyCode code = e.getCode();
            input.remove( code );
        });
        
        FieldLogic fieldLogic = new FieldLogic(graphicOperationQueue,input);
        
        
        new AnimationTimer(){
                    
            int frame=0;
            
            @Override
            public void handle(long currentNanoTime){
                frame++;
                if (frame%5==0)
                    fieldLogic.update();
                fieldGraphicer.update();
                stage.setTitle("X:"+fieldLogic.cx+" Y:"+fieldLogic.cy+" ChipID:"+fieldLogic.mapChipID[fieldLogic.cx][fieldLogic.cy]);
            }
        }.start();
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}