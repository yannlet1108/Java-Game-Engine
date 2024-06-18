package automaton;

/**
 * Classe permettant d'ecrire un automate pour creer un comportement d'entite.
 */
public class Transition {

	State start;
	Condition cond;
	Action action;
	State end;

	/**
	 * Cree une nouvelle transition a partir d'une action et d'une condition.
	 * 
	 * @param start  : Etat initial
	 * @param cond   : Condition de transition
	 * @param action : Action resultante
	 * @param end    : Etat final
	 */
	Transition(State start, Condition cond, Action action, State end) {
		this.start = start;
		this.cond = cond;
		this.action = action;
		this.end = end;
	}

}
