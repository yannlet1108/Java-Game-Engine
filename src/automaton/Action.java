package automaton;

import model.Entity;

/**
 * Interface des actions liees aux transitions des automates
 */
interface Action {

	/**
	 * Cette méthode demande à l'Entity E d'effectuer l'action requise
	 * 
	 * @param Entity e
	 */
	void exec(Entity e);

}
