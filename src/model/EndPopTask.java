package model;

public class EndPopTask extends ActionTask {
	Direction direction;

	public EndPopTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.freeAutomaton();
	}

}
