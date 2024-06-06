package model;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;

public class Model {
	private Matrix board;
	private int boardHeight;
	private int boardWidth;
	private Collection<Entity> entities;

	public Model(int size) {
		boardHeight = size;
		boardWidth = size;
		board = new Matrix(boardHeight, boardWidth);
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
		return board.getEntityAt(position.y,position.x);
	}

	void setEntityAt(Point position, Entity e) {
		board.setEntityAt(position.y, position.x, e);
	}

	void removeEntity(Entity e) {
		entities.remove(e);
		board.setEntityAt(e.getY(), e.getY(), null);
	}

	void addEntity(Entity e) {
		entities.add(e);
		board.setEntityAt(e.getY(), e.getY(), e);
	}
}
