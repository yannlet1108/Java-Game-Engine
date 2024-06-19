package automaton;

import gal.ast.AST;

public class TestAutomaton {

	public static void main(String args[]) throws Exception {
		// Bank with all the automatons
		AutomatonBank automatonBank = loadAutomatons("gal_bank/GalBank.gal");

		// Only one automaton
		Automaton oneAutomaton = loadAutomaton("gal_bank/GalBank.gal", "Goldfish");
	}

	private static AutomatonBank loadAutomatons(String path_file) throws Exception {
		AST ast = gal.parser.Parser.from_file(path_file);

		AutBuilder autBuilder = new AutBuilder(ast);
		automaton.AutomatonBank automatonBank = (automaton.AutomatonBank) ast.accept(autBuilder);

		return automatonBank;
	}

	private static Automaton loadAutomaton(String path_file, String name) throws Exception {
		AST ast = gal.parser.Parser.from_file(path_file);

		AutBuilder autBuilder = new AutBuilder(ast);
		automaton.AutomatonBank automatonBank = (automaton.AutomatonBank) ast.accept(autBuilder);

		return automatonBank.getAutomaton(name);
	}
}
