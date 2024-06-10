package automaton;

import model.Entity;

/**
 * Classe exprimant la negation d'une condition
 */
public class Negation implements Condition {

	Condition cond;

	/**
	 * Defini la condition a refuser
	 * @param cond : Condition a parametrer
	 */
	Negation(Condition cond) {
		this.cond = cond;
	}

	/**
	 * Evalue la valeur de la condition
	 */
	@Override
	public boolean eval(Entity e) {
		return !cond.eval(e);
	}
}
