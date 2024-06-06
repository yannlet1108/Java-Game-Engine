package model;

import java.awt.Point;

import automaton.Automaton;

public abstract class Entity {
	Point position;
	Direction direction;
	Model model;
	Automaton automaton;

	public Entity(Point position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}

	public int getX() {
		return position.x;
	}

	public int getY() {
		return position.y;
		
	}

	public void move(Direction direction) {
		throw new RuntimeException("Not Yet Implemented");
	}

	public abstract void egg();

	public abstract void pick();

	public abstract void explode();

	public boolean cell(Direction direction, Category category) {
		throw new RuntimeException("Not Yet Implemented");
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void step() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
