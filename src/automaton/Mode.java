package automaton;

import model.Entity;

public class Mode {

	State name;
	Behaviour behaviour; // Liste de transitions

	Mode(State name, Behaviour behaviour) {
		this.name = name;
		this.behaviour = behaviour;
	}

	/**
	 * Exécute un pas depuis ce mode (appelé par l'automate)
	 */
	void step(Entity e) {
		behaviour.step(e);
	}

	Behaviour getBehaviour() {
		return behaviour;
	}

	boolean isSameState(State state) {
		return state.equals(name);
	}

}
