package automaton;

import model.Entity;

class Conjonction implements Condition {

	Condition c1;
	Condition c2;

	Conjonction(Condition c1, Condition c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	public boolean eval(Entity e) {
		return c1.eval(e) && c2.eval(e);
	}
}
