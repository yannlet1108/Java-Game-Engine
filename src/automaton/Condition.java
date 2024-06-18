package automaton;

import model.Entity;

/**
 * Interface des conditions liees aux transitions des automates
 */
interface Condition {

	boolean eval(Entity e);

}
