package automaton;

import java.util.LinkedList;
import java.util.List;

import model.Entity;
import model.Direction;
import model.Category;

/**
 * Classe de generation et de gestion des automates de controle des entite.
 * 
 * @implNote TODO Penser a faire un fichier a part pour les variables globale
 */
public class Automaton {

	State currentState;
	List<Transition> transitions;

	Entity e;

	public static final int SNAKE = 0;
	public static final int APPLE = 1;
	public static final int BLOCK = 2;

	/**
	 * Cree l'automate en fonction du type d'entite.
	 * 
	 * @param e              : Entite proprietaire de l'automate
	 * @param whichAutomaton : Numero de l'automate defini
	 */
	public Automaton(Entity e, int whichAutomaton) {
		this.e = e;
		load(whichAutomaton);
	}

	/**
	 * Charge les transitions de l'automate selectionne au chargement et
	 * l'initialise.
	 * 
	 * @param whichAutomaton : Numero de l'automate defini
	 */
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

	/**
	 * Charge les transitions liee aux entites de type snake
	 * 
	 * @return : Liste des transitions
	 */
	private List<Transition> snakeTransitions() {
		State waitState = currentState;
		State forwardState = new State(State.FORWARD);

		List<Transition> transitions = new LinkedList<Transition>();

		Direction forward = Direction.FORWARD;
		Category voidCategory = new Category(Category.V);

		transitions.add(new Transition(waitState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), waitState));
		return transitions;
	}

	/**
	 * Charge les transitions liee aux entites de type apple
	 * 
	 * @return : Liste des transitions
	 */
	private List<Transition> appleTransitions() {
		return null;
	}

	/**
	 * Charge les transitions liee aux entites de type block
	 * 
	 * @return : Liste des transitions
	 */
	private List<Transition> blockTransitions() {
		return null;
	}

}
