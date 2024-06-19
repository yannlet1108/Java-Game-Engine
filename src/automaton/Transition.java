package automaton;

import model.Entity;

/**
 * Classe permettant d'ecrire un automate pour creer un comportement d'entite.
 */
public class Transition {

	Condition cond;
	Action action;
	State end;

	/**
	 * Cree une nouvelle transition a partir d'une action et d'une condition.
	 * 
	 * @param cond   : Condition de transition
	 * @param action : Action resultante
	 * @param end    : Etat final
	 */
	Transition(Condition cond, Action action, State end) {
		this.cond = cond;
		this.action = action;
		this.end = end;
	}

	boolean eval(Entity e) {
		return cond.eval(e);
	}
	
	void exec(Entity e) {
		action.exec(e);
	}

}
