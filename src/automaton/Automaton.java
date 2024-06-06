package automaton;

import java.util.LinkedList;
import java.util.List;

import model.Entity;
import model.Direction;
import model.Category;

public class Automaton {

	State currentState;
	List<Transition> transitions;

	Entity e;

	public static final int SNAKE = 0;
	public static final int APPLE = 1;
	public static final int BLOCK = 2;

	/*
	 * Constructeur d'Automate Paramètres : L'entité à laquelle l'automate est
	 * associé Le type d'automate voulu (comportement voulu pour cette entité)
	 */
	public Automaton(Entity e, int whichAutomaton) {
		this.e = e;
		load(whichAutomaton);
	}

	private void load(int whichAutomaton) {
		switch (whichAutomaton) {
		case SNAKE:
			currentState = new State(State.WAIT);
			transitions = snakeTransitions();
			break;
		case APPLE:
			currentState = new State(State.WAIT);
			transitions = appleTransitions();
			break;
		case BLOCK:
			currentState = new State(State.WAIT);
			transitions = blockTransitions();
		default:
			throw new IllegalArgumentException();
		}
	}

	private List<Transition> snakeTransitions() {
		State waitState = currentState;
		State forwardState = new State(State.FORWARD);
		State deadState = new State(State.DEAD);

		List<Transition> transitions = new LinkedList<Transition>();

		Direction forward = new Direction(Direction.F);
		Category voidCategory = new Category(Category.V);
		Category obstacle = new Category(Category.O);

		transitions.add(new Transition(waitState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), waitState));
		return transitions;
	}

	private List<Transition> appleTransitions() {
		return null;
	}

	private List<Transition> blockTransitions() {
		return null;
	}

}
