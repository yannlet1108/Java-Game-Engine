package automaton;

import model.Entity;

/**
 * Classe correspondant à une condition toujours vraie (True dans l'automate)
 */
public class TrueCondition implements Condition {

	public TrueCondition() {
		// Nothing to do here
	}

	@Override
	public boolean eval(Entity e) {
		return true;
	}

	@Override
	public String toString() {
		return "True";
	}

}
