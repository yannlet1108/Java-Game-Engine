package automaton;

import model.Entity;

class Explode implements Action {
	
	Explode(){
		// Nothing to do here
	}

	@Override
	public void exec(Entity e) {
		e.doExplode();
	}

}
