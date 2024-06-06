package automaton;

import model.Entity;

/**
 * Interface des conditions liees aux transitions des automates
 */
public interface Condition {

	public boolean eval(Entity e);

}
