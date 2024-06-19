package automaton;

import java.util.LinkedList;
import java.util.List;

import model.Entity;

public class Behaviour {

	List<Transition> transitions;

	Behaviour(List<Object> transitions) {
		this.transitions = new LinkedList<Transition>();
		for (Object transition : transitions) {
			this.transitions.add((Transition) transition);
		}
	}

	/**
	 * Exécute un pas en cherchant la bonne transition (appelé par le Mode)
	 */
	void step(Entity e) {
		for (Transition transition : transitions) {
			if (transition.eval(e)) {
				transition.exec(e);
				return;
			}
		}
	}

	List<Transition> getTransitions() {
		return transitions;
	}
}
