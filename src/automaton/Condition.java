package automaton;

import model.Entity;

/**
 * Interface des conditions liees aux transitions des automates
 */
interface Condition {

	/**
	 * Demande à l'entité d'évaluer la condition
	 */
	boolean eval(Entity e);

}
