package automaton;

import java.util.LinkedList;
import java.util.List;

import model.Entity;

public class Automaton {

	// String name; // Maybe later
	State initialState;
	List<Mode> modes;

	/**
	 * Automate instancié de manière unique dans le jeu et utilisé par plusieurs FSM
	 * 
	 * @param initialState
	 * @param modes
	 */
	Automaton(State initialState, List<Object> modes) {
		this.initialState = initialState;
		this.modes = new LinkedList<Mode>();
		for (Object mode : modes) {
			this.modes.add((Mode) mode);
		}
	}

	/**
	 * Exécute un step dans l'automate à partir de l'état courant (appelé par la
	 * FSM)
	 * 
	 * @param currentState
	 */
	void step(Entity e, State currentState) {
		Mode mode = getMode(currentState);
		mode.step(e);
	}

	State getInitialState() {
		return initialState;
	}

	Mode getMode(State name) {
		for (Mode mode : modes) {
			if (mode.isSameState(name)) {
				return mode;
			}
		}
		throw new IllegalArgumentException("No mode found with that name");
	}

	/*
	 * List<Transition> getTransitions(State state){ for (Mode mode : modes) { if
	 * (mode.isSameState(state)) { return mode.getBehaviour().getTransitions(); } }
	 * return null; // shouldn't happen }
	 */

}
