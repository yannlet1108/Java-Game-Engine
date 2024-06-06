package automaton;

import java.awt.Point;

import model.Direction;
import model.Entity;
import model.Snake;

public class Tests {

	public static void main(String[] args) {
		
		/*
		Point pos = new Point(0, 0);
		Entity snake = new Snake(pos, Direction.N);
		
		snake.step();
		
		System.out.println("Le serpent est aux coordonnées suivantes :");
		*/
		
		Transition transition = new Transition(State.WAIT, new TrueCondition(), null, State.FORWARD);
		
	}
	
}
