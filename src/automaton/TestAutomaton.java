package automaton;

import gal.ast.AST;

public class TestAutomaton {

	public static void main(String args[]) throws Exception {

		System.out.println("Test de création d'automates depuis un fichier gal\n");

		// Bank with all the automatons
		String galBankPath = "gal_bank/GalBank.gal";
		System.out.println("Lecture du fichier : " + galBankPath);

		AutomatonBank automatonBank = loadAutomatons(galBankPath);
		System.out.println("Liste des automates lus dans la GalBank : " + automatonBank + "\n");

		// Only one automaton
		String automatonName = "Goldfish";
		Automaton oneAutomaton = loadAutomaton(galBankPath, automatonName);
		System.out.println("Extraction d'un seul automate : " + oneAutomaton);
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
