package model;

public class EndHitTask extends ActionTask {
	Direction direction;

	public EndHitTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.freeAutomaton();
	}

}
