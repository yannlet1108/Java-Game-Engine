package model;

import java.awt.Point;
import automaton.Automaton;
import view.AvatarApple;
import view.AvatarSnake;

public class Apple extends Entity {
	Apple(Point P, Direction direction, Model model) {
		super(P, direction, model);
		this.category = Category.APPLE;
		automaton = new Automaton(this, Automaton.APPLE);
		model.m_view.store(new AvatarApple(model.m_view, this));
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
