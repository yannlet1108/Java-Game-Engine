package model;

import java.awt.Point;

import automaton.Automaton;

public class Block extends Entity {

	public Block(Point position, Direction direction, Model model) {
		super(position, direction, model);
		this.category = Category.OBSTACLE;
		automaton = new Automaton(this, Automaton.BLOCK);
	}

	@Override
	public void egg() {
		throw new RuntimeException("Not Yet Implemented");

	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public void explode() {
		this.model.removeEntity(this);
	}

}
