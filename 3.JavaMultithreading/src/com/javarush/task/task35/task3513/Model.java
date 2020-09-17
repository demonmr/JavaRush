package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private int score;
    private int maxTile;
    public Model() {

        resetGameTiles ();

    }


    /*Метод для получения списка пустых ячеек на поле игры*/
    private List<Tile> getEmptyTiles(){
        return Arrays.stream(gameTiles).flatMap(Arrays::stream)
                .filter(Tile::isEmpty).collect(Collectors.toList());
    }
    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if(emptyTiles.size() > 0)
            emptyTiles.get((int)(emptyTiles.size() * Math.random())).value = (Math.random() < 0.9 ? 2 : 4);
    }
    public void resetGameTiles(){
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
    private void compressTiles(Tile[] tiles){

    }
    private void mergeTiles(Tile[] tiles){

    }
}
