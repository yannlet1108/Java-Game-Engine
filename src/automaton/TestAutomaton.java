package automaton;

import java.util.List;

import gal.ast.AST;

public class TestAutomaton {

	public static void main(String args[]) throws Exception {
		loadAutomatons("gal_bank/BackAndForth.gal");
	}

	private static List<Automaton> loadAutomatons(String path_file) throws Exception {
		AST ast = gal.parser.Parser.from_file(path_file);

		AutBuilder autBuilder = new AutBuilder(ast);
		automaton.AST newAst = (automaton.AST) ast.accept(autBuilder);

		return newAst.automatons;
	}
}
