package automaton;

import model.Entity;

public class FalseCondition implements Condition{

	public FalseCondition() {
		
	}
	
	@Override
	public boolean eval(Entity e) {
		return false;
	}
	
}
