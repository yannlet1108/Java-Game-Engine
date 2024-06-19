package automaton;

import java.util.LinkedList;
import java.util.List;

/**
 * Banque d'automates construite par le visiteur
 */
class AutomatonBank {

	private List<Automaton> automatons;

	AutomatonBank(List<Object> automatons) {
		this.automatons = new LinkedList<Automaton>();
		for (Object automaton : automatons) {
			this.automatons.add((Automaton) automaton);
		}
	}

	List<Automaton> getAutomatons() {
		return automatons;
	}

	Automaton getAutomaton(String name) {
		for (Automaton automaton : automatons) {
			if (automaton.hasName(name)) {
				return automaton;
			}
		}
		throw new IllegalArgumentException("Automaton named " + name + " not found");
	}

}
