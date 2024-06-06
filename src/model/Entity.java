package model;

import java.awt.Point;

import automaton.Automaton;

public class Entity {
	Point position;
	Direction direction;
	Model model;

	public Entity(Point position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}

	public int getX() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public int getY() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public void move(Direction direction) {
		throw new RuntimeException("Not Yet Implemented");
	}

	public void egg() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public void explode() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public boolean cell(Direction direction, Category category) {
		throw new RuntimeException("Not Yet Implemented");
	}

	public Direction getDirection() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public void setDirection(Direction direction) {
		throw new RuntimeException("Not Yet Implemented");
	}

	public void step() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
