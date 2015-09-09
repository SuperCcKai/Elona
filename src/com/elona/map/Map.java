package com.elona.map;

public class Map {
    
    int[][] mapChipID;
    int mapChipFileID;
    int floor;
    
    public Map(int[][] mcID, int mcfID, int f){
        mapChipID=mcID;
        mapChipFileID=mcfID;
        floor=f;
        // テストによる追加部分
    }
    
}
