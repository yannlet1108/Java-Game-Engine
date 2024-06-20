package automaton;

import java.util.LinkedList;
import java.util.List;

import gal.ast.AST;

/**
 * Banque d'automates construite par le visiteur
 */
public class AutomatonBank {

	private List<Automaton> automatons;

	/**
	 * Constructeur de banque d'automate pour le modèle (à la création du monde)
	 */
	public AutomatonBank() {
		loadAutomatons("gal_bank/BackAndForth.gal");
	}

	/**
	 * Construit la banque d'automates à partir d'une liste d'objets (automates)
	 * (pour le visiteur)
	 */
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
		throw new IllegalArgumentException("Automaton named " + name + " not found in the AntomatonBank");
	}

	private void loadAutomatons(String path_file) {
		AST ast;
		try {
			ast = gal.parser.Parser.from_file(path_file);
			AutBuilder autBuilder = new AutBuilder(ast);
			automatons = ((AutomatonBank) ast.accept(autBuilder)).getAutomatons();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Parsing impossible for the file " + path_file);
		}
	}
	
	@Override
	public String toString() {
		return automatons.toString();
	}

}
