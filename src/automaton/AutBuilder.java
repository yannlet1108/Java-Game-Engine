package automaton;

import java.util.Iterator;
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
		case "A":
			return model.Category.ADVERSARY;
		case "T":
			return model.Category.TEAM_MEMBER;
		/* On utilise Underscore comme une Direction */
		case "_":
			// System.out.println("Underscore found in Category");
			return model.Direction.UNDERSCORE;
		default:
			throw new IllegalArgumentException("Category's name not recognised : " + cat.toString());
		}
	}

	@Override
	public Object visit(Direction dir) {
		switch (dir.toString()) {
		// Directions absolues
		case "N":
			return model.Direction.N;
		case "S":
			return model.Direction.S;
		case "E":
			return model.Direction.E;
		case "W":
			return model.Direction.W;
		case "NW":
			return model.Direction.NW;
		case "NE":
			return model.Direction.NE;
		case "SW":
			return model.Direction.SW;
		case "SE":
			return model.Direction.SE;
		// Directions relatives
		case "H":
			return model.Direction.HERE;
		case "F":
			return model.Direction.FORWARD;
		case "B":
			return model.Direction.BACKWARD;
		case "L":
			return model.Direction.LEFT;
		case "R":
			return model.Direction.RIGHT;
		// Directions spéciales
		case "d":
			return model.Direction.d;
		default:
			throw new IllegalArgumentException("Direction's name not recognised : " + dir.toString());
		}
	}

	/**
	 * TO DO : Voir avec le controlleur
	 */
	@Override
	public Object visit(Key key) {
		return null;
	}

	/**
	 * On utilise directement des int
	 */
	@Override
	public Object visit(Value v) {
		return v.value;
	}

	/**
	 * Seems to never be called
	 */
	@Override
	public Object visit(Underscore u) {
		System.out.println("Underscore found in Underscore");
		return model.Direction.UNDERSCORE;
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

	/**
	 * For now : Key uses Strings
	 */
	@Override
	public Object build(FunCall funcall, List<Object> parameters) {
		switch (funcall.name) {
		// Conditions
		case "Cell":
			return new Cell((model.Direction) parameters.get(0), (model.Category) parameters.get(1));
		case "Closest":
			return new Closest((model.Category) parameters.get(0), (model.Direction) parameters.get(1));
		case "Key":
			return new automaton.Key(((Key) funcall.parameters.get(0)).toString());
		// Actions
		case "Move":
			if (parameters.isEmpty()) {
				return new Move(null);
			}
			return new Move((model.Direction) parameters.get(0));
		case "Explode":
			return new Explode();
		case "Hit":
			if (parameters.isEmpty()) {
				return new Hit(null);
			}
			return new Hit((model.Direction) parameters.get(0));
		case "Pop":
			return new Pop((int) parameters.get(0));
		case "Egg":
			if (parameters.isEmpty()) {
				return new Egg(null);
			}
			return new Egg((model.Direction) parameters.get(0));
		case "Wait":
			return new Wait();
		// TrueCondition
		case "True":
			return new TrueCondition();
		// Unexpected Function's name
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

		if (funcalls.isEmpty()) {
			return null;
		}
		return (automaton.Action) funcalls.get(0);

		/**
		 * automaton.Actions actions = new automaton.Actions(); for (Iterator<Object>
		 * iterator = funcalls.iterator(); iterator.hasNext();) { FunCall funcall =
		 * (FunCall) iterator.next(); actions.add((automaton.Action) funcall); } return
		 * actions;
		 */
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
