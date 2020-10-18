package com.javarush.task.task35.task3513;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
	private static final int FIELD_WIDTH = 4;
	private Tile[][] gameTiles;
	int score;
	int maxTile = 2;

	public Model() {
		this.score = 0;
		this.maxTile = 0;
		resetGameTiles();

	}


	/*Метод для получения списка пустых ячеек на поле игры*/
	private List<Tile> getEmptyTiles() {
		return Arrays.stream(gameTiles).flatMap(Arrays::stream)
				.filter(Tile::isEmpty).collect(Collectors.toList());
	}

	private void addTile() {
		List<Tile> emptyTiles = getEmptyTiles();
		if (emptyTiles.size() > 0)
			emptyTiles.get((int) (emptyTiles.size() * Math.random())).value = (Math.random() < 0.9 ? 2 : 4);
	}

	public void resetGameTiles() {
		this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
		for (int i = 0; i < FIELD_WIDTH; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				gameTiles[i][j] = new Tile();
			}
		}
		addTile();
		addTile();
	}

	private boolean compressTiles(Tile[] tiles) {
		boolean flag = false;
		Tile temps;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (i < j && tiles[i].value == 0 && tiles[j].value > 0) {
					temps = tiles[i];
					tiles[i] = tiles[j];
					tiles[j] = temps;
					flag = true;
				}
			}
		}

		return flag;

	}

	private boolean mergeTiles(Tile[] tiles) {
		boolean isChanged = false;
		for (int j = 0; j < 3; j++) {
			if (tiles[j].getValue() != 0 && tiles[j].getValue() == tiles[j + 1].getValue()) {
				tiles[j].setValue(tiles[j].getValue() * 2);
				tiles[j + 1].setValue(0);
				if (tiles[j].getValue() > maxTile) maxTile = tiles[j].getValue();
				score += tiles[j].getValue();
				isChanged = true;

			}
		}

		if (isChanged) {
			Tile temp;
			for (int j = 0; j < 3; j++) {
				if (tiles[j].getValue() == 0 && tiles[j + 1].getValue() != 0) {
					temp = tiles[j];
					tiles[j] = tiles[j + 1];
					tiles[j + 1] = temp;
				}
			}
		}

		return isChanged;
	}

	public void left() {
		boolean hasChanged = moved();
		if(hasChanged) {
			addTile();
		}
	}

	private boolean moved() {
		boolean hasChanged = false;
		for(int i = 0; i < gameTiles.length; i++) {
			if(compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	public void right() {

		rotate();
		rotate();
		if (moved()) {
			addTile();
		}
		rotate();
		rotate();
	}
	public void up() {
		rotate();
		rotate();
		rotate();
		if (moved()) {
			addTile();
		}
		rotate();

	}
	public void down() {
		rotate();
		if (moved()) {
			addTile();
		}
		rotate();
		rotate();
		rotate();
	}

	private void rotate(){
		Tile[][] tiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
		for (int i = 0; i <gameTiles.length ; i++) {
			for (int j = 0; j <gameTiles[i].length ; j++) {
				tiles[i][tiles.length-1-j] = gameTiles[j][i];
			}
		}
		gameTiles = tiles.clone();

	}



}
