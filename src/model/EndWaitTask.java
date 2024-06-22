package model;

public class EndWaitTask extends ActionTask {

	public EndWaitTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.freeAutomaton();
	}

}
