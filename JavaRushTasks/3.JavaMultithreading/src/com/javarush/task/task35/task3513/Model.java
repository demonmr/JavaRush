package com.javarush.task.task35.task3513;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Model {
	private static final int FIELD_WIDTH = 4;
	private Stack<Tile[][]> previousStates = new Stack<>();
	private Stack<Integer> previousScores = new Stack<>();
	private boolean isSaveNeeded = true;


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
		if (isSaveNeeded) {
			saveState(gameTiles);
		}

		boolean hasChanged = moved();

		if (hasChanged) {
			addTile();
			isSaveNeeded = true;
		}

	}

	private boolean moved() {
		boolean hasChanged = false;
		for (int i = 0; i < gameTiles.length; i++) {
			if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	public void right() {
		saveState(gameTiles);
		rotate();
		rotate();
		if (moved()) {
			addTile();
		}
		rotate();
		rotate();
	}

	public void up() {
		saveState(gameTiles);
		rotate();
		rotate();
		rotate();
		if (moved()) {
			addTile();
		}
		rotate();

	}

	public void down() {
		saveState(gameTiles);
		rotate();
		if (moved()) {
			addTile();
		}
		rotate();
		rotate();
		rotate();
	}

	private void rotate() {
		Tile[][] tiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
		for (int i = 0; i < gameTiles.length; i++) {
			for (int j = 0; j < gameTiles[i].length; j++) {
				tiles[i][tiles.length - 1 - j] = gameTiles[j][i];
			}
		}
		gameTiles = tiles.clone();

	}

	public Tile[][] getGameTiles() {
		return gameTiles;
	}

	public boolean canMove() {
		for (int i = 0; i < FIELD_WIDTH; i++) {
			for (int j = 0; j < FIELD_WIDTH; j++) {
				if (gameTiles[i][j].value == 0) {
					return true;
				}
				if (i != 0 && gameTiles[i - 1][j].value == gameTiles[i][j].value) {
					return true;
				}
				if (j != 0 && gameTiles[i][j - 1].value == gameTiles[i][j].value) {
					return true;
				}
				if (i < FIELD_WIDTH - 1 && gameTiles[i + 1][j].value == gameTiles[i][j].value) {
					return true;
				}
				if (j < FIELD_WIDTH - 1 && gameTiles[i][j + 1].value == gameTiles[i][j].value) {
					return true;
				}

			}
		}
		return false;
	}

	private void saveState(Tile[][] tiles) {
		Tile[][] save = new Tile[FIELD_WIDTH][FIELD_WIDTH];
		for (int i = 0; i < save.length; i++) {
			for (int j = 0; j < save[i].length; j++) {
				save[i][j] = new Tile(gameTiles[i][j].value);
			}
		}

		previousStates.push(save);
		previousScores.push(score);
		isSaveNeeded = false;
	}

	public void rollback() {
		if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
			gameTiles = previousStates.pop();
			score = previousScores.pop();
			isSaveNeeded = true;
		}
	}

	public void randomMove() {
		int n = ((int) (Math.random() * 100)) % 4;
		switch (n) {
			case 0:
				left();
				break;
			case 1:
				right();
				break;
			case 2:
				up();
				break;
			case 3:
				down();
				break;
		}
	}

	public boolean hasBoardChanged() {
		int summOriginals = Arrays.stream(gameTiles).flatMap(Arrays::stream).mapToInt(e -> e.value).sum();
		int summStack = Arrays.stream(previousStates.peek()).flatMap(Arrays::stream).mapToInt(e -> e.value).sum();
		if (summOriginals != summStack) {
			return true;
		} else
			return false;
	}

	public MoveEfficiency getMoveEfficiency(Move move) {
		move.move();
		if (!hasBoardChanged()) {
			rollback();
			return new MoveEfficiency(-1, 0, move);
		} else {
			rollback();
			return new MoveEfficiency(getEmptyTiles().size(), score, move);
		}
	}

	public void autoMove() {
		PriorityQueue<MoveEfficiency> queue = new PriorityQueue <>(4,Collections.reverseOrder());
		queue.offer(getMoveEfficiency(this::left));
		queue.offer(getMoveEfficiency(this::right));
		queue.offer(getMoveEfficiency(this::up));
		queue.offer(getMoveEfficiency(this::down));
		MoveEfficiency moveEfficiency = queue.poll();
		moveEfficiency.getMove().move();

	}
}

