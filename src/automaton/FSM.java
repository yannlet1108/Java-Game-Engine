package automaton;

import model.Entity;

public class FSM {

	private Entity e;
	private State currentState;
	private Automaton automaton;

	/**
	 * Constructeur de FSM, champ d'une entité permettant d'effectuer un pas
	 * d'automate
	 * 
	 * @param entity    : l'entité à qui on associe l'automate demandé
	 * @param automaton
	 */
	public FSM(Entity entity, Automaton automaton) {
		e = entity;
		currentState = automaton.getInitialState();
		this.automaton = automaton;
	}

	/**
	 * Méthode appelée par l'entité pour effectuer un pas d'automate
	 */
	public void step() {
		automaton.step(e, currentState);
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setState(State state) {
		currentState = state;
	}

	@Override
	public String toString() {
		return "Automate " + automaton.toString() + " dans l'état " + currentState.toString();
	}

}
