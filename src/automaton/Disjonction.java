package automaton;

import model.Entity;

public class Disjonction implements Condition {

	Condition c1;
	Condition c2;

	public Disjonction(Condition c1, Condition c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	public boolean eval(Entity e) {
		return c1.eval(e) || c2.eval(e);
	}
}
