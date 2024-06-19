package automaton;

import model.Entity;

public class FSM {

	Entity e;
	State currentState;
	Automaton automaton;

	/**
	 * Constructeur de FSM, champ d'une entité permettant d'effectuer un pas
	 * d'automate
	 * 
	 * @param automaton
	 */
	FSM(Entity entity, Automaton automaton) {
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

	State getCurrentState() {
		return currentState;
	}

}
