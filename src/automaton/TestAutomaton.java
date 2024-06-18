package automaton;

import java.util.LinkedList;
import java.util.List;

import gal.ast.AST;

public class TestAutomaton {

	public static void main() throws Exception {
		loadAutomatons("");
	}
	
	private static List<Automaton> loadAutomatons(String path_file) throws Exception{
		List<Automaton> automatonList = new LinkedList<Automaton>();
		AST ast = gal.parser.Parser.from_file(path_file);
		
		AutBuilder autBuilder = new AutBuilder(ast);
		ast.accept(autBuilder);
		
		
		return automatonList;
	}
}
