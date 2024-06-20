package automaton;

import model.Entity;

class Key implements Condition {

	private String key;

	Key(String key) {
		this.key = key;
	}

	@Override
	public boolean eval(Entity e) {
		e.doKey(key);
	}

	@Override
	public String toString() {
		return "Key(" + key + ")";
	}

}
