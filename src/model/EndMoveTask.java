package model;

public class EndMoveTask extends ActionTask {

	public EndMoveTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.setForce(new Vector(0, 0));
		entity.freeAutomaton();
	}
}
