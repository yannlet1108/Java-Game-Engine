package automaton;

import model.Entity;

public class Explode implements Action {

	@Override
	public void exec(Entity e) {
		e.explode();
	}

}
