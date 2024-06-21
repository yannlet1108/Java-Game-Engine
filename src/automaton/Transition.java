package automaton;

import model.Entity;

/**
 * Classe Transition
 */
class Transition {

	private Condition cond;
	private Action action;
	private State end;

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

	/**
	 * Demande à l'entité d'évaluer la condition de la transition
	 */
	boolean eval(Entity e) {
		return cond.eval(e);
	}

	/**
	 * Demande à l'entité d'exécuter l'action de la transition
	 */
	void exec(Entity e) {
		if (action != null) {
			action.exec(e);
		}
		e.getFSM().setState(end);
	}

	@Override
	public String toString() {
		if (action == null) {
			return cond.toString() + "? ";
		}
		return cond.toString() + "? " + action.toString();
	}

}
