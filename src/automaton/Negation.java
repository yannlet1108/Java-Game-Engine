package automaton;

import model.Entity;

/**
 * Classe exprimant la negation d'une condition
 */
class Negation implements Condition {

	private Condition cond;

	/**
	 * Constructeur de négation
	 */
	Negation(Condition cond) {
		this.cond = cond;
	}

	/**
	 * Evalue la condition
	 */
	@Override
	public boolean eval(Entity e) {
		return !cond.eval(e);
	}

	@Override
	public String toString() {
		return "not(" + cond.toString() + ")";
	}
}
