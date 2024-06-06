package automaton;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Entity;
import model.Direction;
import model.Category;

public class Automaton {

	private State currentState;
	private List<Transition> transitions;

	private Entity e;

	public static final int SNAKE = 0;
	public static final int APPLE = 1;
	public static final int BLOCK = 2;

	/*
	 * Constructeur d'Automate
	 * 
	 * @param : L'entité à laquelle l'automate est associé et Le type d'automate
	 * voulu (comportement voulu pour cette entité)
	 */
	public Automaton(Entity e, int whichAutomaton) {
		this.e = e;
		load(whichAutomaton);
	}

	private void load(int whichAutomaton) {
		switch (whichAutomaton) {
		case SNAKE:
			currentState = State.WAIT;
			transitions = snakeTransitions();
			break;
		case APPLE:
			currentState = State.WAIT;
			transitions = appleTransitions();
			break;
		case BLOCK:
			currentState = State.WAIT;
			transitions = blockTransitions();
		default:
			throw new IllegalArgumentException();
		}
	}

	public void step() {
		for (Iterator<Transition> iterator = transitions.iterator(); iterator.hasNext();) {
			Transition transition = (Transition) iterator.next();
			if (transition.start == currentState) {
				if (transition.cond.eval(e)) {
					currentState = transition.end;
					transition.action.exec(e);
				}
			}
		}
		throw new IllegalStateException("Aucune transition de l'état courant n'est valide");
	}

	private List<Transition> snakeTransitions() {
		State waitState = State.WAIT;
		State forwardState = State.FORWARD;
		State deadState = State.DEAD;

		List<Transition> transitions = new LinkedList<Transition>();

		Direction forward = Direction.FORWARD;
		Category voidCategory = Category.VOID;
		Category obstacle = Category.OBSTACLE;

		transitions.add(new Transition(waitState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), waitState));
		return transitions;
	}

	private List<Transition> snakeTransitionsEssai() {
		// Instanciation unique des États
		State waitState = State.WAIT;
		State forwardState = State.FORWARD;
		State deadState = State.DEAD;

		List<Transition> transitions = new LinkedList<Transition>();

		// Instanciation unique des Directions
		Direction forward = Direction.FORWARD;
		Direction right = Direction.RIGHT;
		Direction left = Direction.LEFT;

		// Instanciation unique des Catégories
		Category voidCategory = Category.VOID;
		Category obstacle = Category.OBSTACLE;

		/*
		 * * * * * * * * * Ajout des différentes transitions de l'automate * * * * * * *
		 */
		// si libre en face, on avance
		transitions.add(new Transition(waitState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), forwardState));

		// si occupé en face et libre à droite, on tourne à droite
		transitions.add(new Transition(forwardState,
				new Conjonction(new Cell(forward, obstacle), new Cell(right, voidCategory)), new Move(forward),
				forwardState));

		// si occupé en face et libre à gauche, on tourne à gauche
		transitions.add(
				new Transition(forwardState, new Conjonction(new Cell(forward, obstacle), new Cell(left, voidCategory)),
						new Move(forward), forwardState));

		// si occupé en face, à droite et à gauche, on meurt
		transitions.add(new Transition(forwardState,
				new Conjonction(new Conjonction(new Cell(forward, obstacle), new Cell(right, obstacle)),
						new Cell(left, obstacle)),
				new Destroy(), deadState));

		return transitions;
	}

	private List<Transition> appleTransitions() {
		return null;
	}

	private List<Transition> blockTransitions() {
		return null;
	}

}
