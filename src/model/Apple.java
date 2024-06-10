package model;

import java.awt.Point;
import automaton.Automaton;

public class Apple extends Entity {
	Apple(Point P, Direction direction, Model model) {
		super(P, direction, model);
		this.category = Category.APPLE;
		automaton = new Automaton(this, Automaton.APPLE);
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
		throw new RuntimeException("Not Yet Implemented");

	}

}
