package model;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;

public class Model {
	private Entity[][] board;
	private int boardHeight;
	private int boardWidth;
	private Collection<Entity> entities;

	public Model(int size) {
		boardHeight = size;
		boardWidth = size;
		board = new Entity[boardWidth][boardHeight];
		entities = new LinkedList<Entity>();
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public void step() {
		for (Entity entity : entities) {
			entity.step();
		}
	}

	Entity getEntityAt(Point position) {
		return board[position.y][position.x];
	}

	void setEntityAt(Point position, Entity e) {
		board[position.y][position.x] = e;
	}

	void removeEntity(Entity e) {
		entities.remove(e);
		board[e.getY()][e.getX()] = null;
	}

	void addEntity(Entity e) {
		entities.add(e);
		board[e.getY()][e.getX()] = e;
	}
}
