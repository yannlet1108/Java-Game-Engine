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

public class AutBuilder implements IVisitor{
	
	public AutBuilder(AST bot) {
		// TO DO
	}

	@Override
	public Object build(FunCall arg0, List<Object> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(UnaryOp arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(Condition arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(AST arg0, List<Object> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(BinaryOp arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(Mode arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(Actions arg0, String arg1, List<Object> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(Automaton arg0, Object arg1, List<Object> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object build(Transition arg0, Object arg1, Object arg2, Object arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(FunCall arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(BinaryOp arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(UnaryOp arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(Mode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(Condition arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(Actions arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(Transition arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(Automaton arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(AST arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(FunCall arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(BinaryOp arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(UnaryOp arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Mode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Condition arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Actions arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Transition arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Automaton arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(AST arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object visit(Category arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Direction arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Key arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Value arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Underscore arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(FunCall arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BinaryOp arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object visit(State arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(Mode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Actions arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object visit(Behaviour arg0, List<Object> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
