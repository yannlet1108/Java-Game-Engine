package automaton;

import java.util.LinkedList;
import java.util.List;

import model.Entity;

public class Automaton {

	private String name;
	private State initialState;
	private List<Mode> modes;

	/**
	 * Automate instancié de manière unique dans le jeu et utilisé par plusieurs FSM
	 * 
	 * @param initialState
	 * @param modes
	 */
	Automaton(String name, State initialState, List<Object> modes) {
		this.name = name;
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

	/**
	 * @param name
	 * @return the mode with the corresponding name
	 */
	private Mode getMode(State name) {
		for (Mode mode : modes) {
			if (mode.isSameState(name)) {
				return mode;
			}
		}
		throw new IllegalArgumentException("No mode found with that name");
	}

	boolean hasName(String name) {
		return name.equals(this.name);
	}
	
	@Override
	public String toString() {
		return name;
	}

}
