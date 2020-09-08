package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private final static String MINE = "\uD83D\uDCA3";
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private final static String FLAG = "\uD83D\uDEA9";
    private Color COLOR_FIELD = Color.ORANGE;
    private Color COLOR_FLAG = Color.YELLOW;
    private boolean isGameStopped;
    private int countFlags;
    private int countClosedTiles=SIDE*SIDE;
    private int score;
    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        setScreenSize(SIDE, SIDE);
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x,y,"");
            }
        }

        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, COLOR_FIELD);
            }
        }
        countMineNeighbors();
        countFlags=countMinesOnField;

    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }
    private void countMineNeighbors(){
        for (int i = 0; i <gameField.length ; i++) {
            for (int j = 0; j <gameField[i].length ; j++) {
                if (!gameField[i][j].isMine){
                    for (int k = 0; k <getNeighbors(gameField[i][j]).size() ; k++) {
                        if (getNeighbors(gameField[i][j]).get(k).isMine)
                        gameField[i][j].countMineNeighbors++;
                    }
                }

            }

        }
    }
    private void openTile(int x, int y){
        if (!isGameStopped && !gameField[y][x].isFlag && !gameField[y][x].isOpen) {
            if (!gameField[y][x].isMine) {
                gameField[y][x].isOpen = true;
                score+=5;
                setScore(score);
                countClosedTiles--;
                setCellColor(x, y, Color.GREEN);
                if (gameField[y][x].countMineNeighbors == 0) {
                    for (GameObject gameObj : getNeighbors(gameField[y][x])
                    ) {
                        if (!gameObj.isOpen) {
                            openTile(gameObj.x, gameObj.y);

                        }

                    }
                    setCellValue(x, y, "");


                } else if (gameField[y][x].countMineNeighbors != 0) {
                    setCellNumber(x, y, gameField[y][x].countMineNeighbors);

                }

            } else {
                setCellValueEx(x, y, Color.RED, MINE);
                gameField[y][x].isOpen = true;

                gameOver();
                return;

            }
        }
        if (countClosedTiles == countMinesOnField && !gameField[y][x].isMine){
            win();
        }


    }
    private void markTile(int x, int y){

        if (!isGameStopped) {
            if (!gameField[y][x].isOpen) {
                if (countFlags>0) {
                    if (!gameField[y][x].isFlag) {
                        gameField[y][x].isFlag = true;
                        countFlags--;
                        setCellValue(x, y, FLAG);
                        setCellColor(x, y,COLOR_FLAG );
                    }else
                    if (gameField[y][x].isFlag) {
                        gameField[y][x].isFlag = false;
                        countFlags++;
                        setCellValue(x, y, "");
                        setCellColor(x, y, COLOR_FIELD);
                    }
                }
            }
        }


    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped){
            restart();

        }else {
            openTile(x,y);
        }

    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x,y);
    }
    private void gameOver(){
        isGameStopped=true;
        showMessageDialog(Color.BLACK,"Game is Over!",Color.RED,15);

    }
    private void win(){
            showMessageDialog(Color.BLUE,"You WIN! \n Congratulation!!!",Color.YELLOW,15);
            isGameStopped=true;

    }
    private void restart(){
        isGameStopped=false;
        countMinesOnField=0;
        countClosedTiles=SIDE*SIDE;
        countFlags=0;
        score=0;
        setScore(score);
        createGame();


    }
}