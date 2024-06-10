package automaton;

import model.Entity;

/**
 * Interface des actions liees aux transitions des automates
 */
public interface Action {

	public void exec(Entity e);

}
