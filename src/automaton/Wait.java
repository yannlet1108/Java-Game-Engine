package automaton;

import model.Entity;

class Wait implements Action {

	Wait() {
		// Nothing to do here
	}

	@Override
	public void exec(Entity e) {
		e.doWait();
	}

	@Override
	public String toString() {
		return "Wait";
	}
}
