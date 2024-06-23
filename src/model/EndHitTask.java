package model;

public class EndHitTask extends ActionTask {

	public EndHitTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.freeAutomaton();
	}

}
