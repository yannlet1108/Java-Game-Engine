package automaton;

import java.util.LinkedList;
import java.util.List;

public class AST {

	List<Automaton> automatons;

	AST(List<Object> automatons) {
		this.automatons = new LinkedList<Automaton>();
		for (Object automaton : automatons) {
			this.automatons.add((Automaton) automaton);
		}
	}

}
