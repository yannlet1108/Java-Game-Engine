package model;

import java.awt.Point;

import automaton.Automaton;

public class Apple extends Entity {
	Apple(Point P, Direction direction, Model model) {
		super(P, direction, model);
		automaton = new Automaton(this, Automaton.APPLE);
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub
		
	}
	
}
