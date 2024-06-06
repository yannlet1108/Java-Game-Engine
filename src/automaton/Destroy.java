package automaton;

import model.Entity;

public class Destroy implements Action {

	@Override
	public void exec(Entity e) {
		e.destroy();
	}

}
