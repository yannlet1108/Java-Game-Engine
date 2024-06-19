package automaton;

import model.Entity;

/**
 * Classe evaluant la conjonction (ET) de deux conditions
 */
class Conjonction implements Condition {

	private Condition c1;
	private Condition c2;

	/**
	 * Enregistre les deux conditions a comparer
	 * 
	 * @param c1 : Condition 1
	 * @param c2 : Condition 2
	 */
	Conjonction(Condition c1, Condition c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	/**
	 * Evalue la conjonction
	 */
	public boolean eval(Entity e) {
		return c1.eval(e) && c2.eval(e);
	}
}
