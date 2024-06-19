package automaton;

import java.util.List;

import gal.ast.AST;
import gal.ast.Actions;
import gal.ast.Automaton;
import gal.ast.Behaviour;
import gal.ast.BinaryOp;
import gal.ast.Category;
import gal.ast.Condition;
import gal.ast.Direction;
import gal.ast.FunCall;
import gal.ast.IVisitor;
import gal.ast.Key;
import gal.ast.Mode;
import gal.ast.State;
import gal.ast.Transition;
import gal.ast.UnaryOp;
import gal.ast.Underscore;
import gal.ast.Value;

/**
 * Visiteur qui construit une liste d'automates à partir d'un AST
 */
class AutBuilder implements IVisitor {

	AutBuilder(AST bot) {
		// Nothing to do here
	}

	@Override
	public Object visit(Category cat) {
		switch (cat.toString()) {
		case "V":
			return model.Category.VOID;
		case "O":
			return model.Category.OBSTACLE;
		default:
			throw new IllegalArgumentException("Category's name not recognised : " + cat.toString());
		}
	}

	@Override
	public Object visit(Direction dir) {
		switch (dir.toString()) {
		case "E":
			return model.Direction.E;
		case "W":
			return model.Direction.W;
		case "F":
			return model.Direction.FORWARD;
		case "B":
			return model.Direction.BACKWARD;
		default:
			throw new IllegalArgumentException("Direction's name not recognised : " + dir.toString());
		}
	}

	@Override
	public Object visit(Key key) {
		return null;
	}

	@Override
	public Object visit(Value v) {
		return null;
	}

	@Override
	public Object visit(Underscore u) {
		return null;
	}

	// FUNCALL

	@Override
	public void enter(FunCall funcall) {
	}

	@Override
	public void visit(FunCall funcall) {
	}

	@Override
	public void exit(FunCall funcall) {
	}

	@Override
	public Object build(FunCall funcall, List<Object> parameters) {
		switch (funcall.name) {
		case "Cell":
			return new Cell((model.Direction) parameters.get(0), (model.Category) parameters.get(1));
		case "Move":
			return new Move((model.Direction) parameters.get(0));
		case "True":
			return new TrueCondition();
		default:
			throw new IllegalArgumentException("Function's name not recognised : " + funcall.name);
		}
	}

	// BINOP

	@Override
	public void enter(BinaryOp binop) {
	}

	@Override
	public void visit(BinaryOp binop) {
	}

	@Override
	public void exit(BinaryOp binop) {
	}

	@Override
	public Object build(BinaryOp binop, Object left, Object right) {
		switch (binop.operator) {
		case "&":
			return new Conjonction((automaton.Condition) right, (automaton.Condition) left);
		case "/":
			return new Disjonction((automaton.Condition) right, (automaton.Condition) left);
		default:
			throw new IllegalArgumentException("Binary operator not recognised : " + binop.toString());
		}
	}

	// UNOP

	@Override
	public void enter(UnaryOp unop) {
	}

	@Override
	public void exit(UnaryOp unop) {
	}

	@Override
	public Object build(UnaryOp unop, Object expression) {
		return new Negation((automaton.Condition) expression);
	}

	// STATE

	@Override
	public Object visit(State state) {
		return new automaton.State(state.toString());
	}

	// MODE

	public void enter(Mode mode) {
	}

	public void visit(Mode mode) {
	}

	public void exit(Mode mode) {
	}

	public Object build(Mode mode, Object source_state, Object behaviour) {
		return new automaton.Mode((automaton.State) source_state, (automaton.Behaviour) behaviour);
	}

	// BEHAVIOUR

	public Object visit(Behaviour behaviour, List<Object> transitions) {
		return new automaton.Behaviour(transitions);
	}

	// CONDITION

	public void enter(Condition condition) {
	}

	public void exit(Condition condition) {
	}

	public Object build(Condition condition, Object expression) {
		return (automaton.Condition) expression;
	}

	// ACTION

	public void enter(Actions action) {
	}

	public void visit(Actions action) {
	}

	public void exit(Actions action) {
	}

	/**
	 * À modifier si on veut des listes d'actions
	 */
	public Object build(Actions action, String operator, List<Object> funcalls) {
		return (automaton.Action) funcalls.get(0);
	}

	// TRANSITION

	public void enter(Transition transition) {
	}

	public void exit(Transition transition) {
	}

	public Object build(Transition transition, Object condition, Object action, Object target_state) {
		return new automaton.Transition((automaton.Condition) condition, (automaton.Action) action,
				(automaton.State) target_state);
	}

	// AUTOMATON

	public void enter(Automaton automaton) {
	}

	public void exit(Automaton automaton) {
	}

	public Object build(Automaton automaton, Object initial_state, List<Object> modes) {
		return new automaton.Automaton(automaton.name, (automaton.State) initial_state, modes);
	}

	// AST

	public void enter(AST ast) {
	}

	public void exit(AST ast) {
	}

	public Object build(AST ast, List<Object> automata) {
		return new automaton.AutomatonBank(automata);
	}

}
