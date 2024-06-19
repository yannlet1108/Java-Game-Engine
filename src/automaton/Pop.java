package automaton;

import model.Entity;

class Pop implements Action {

	int val;

	Pop(int val) {
		this.val = val;
	}

	@Override
	public void exec(Entity e) {
		e.doPop(val);
	}

	@Override
	public String toString() {
		return "Pop(" + val + ")";
	}

}
