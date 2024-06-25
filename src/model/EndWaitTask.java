package model;

class EndWaitTask extends ActionTask {

	EndWaitTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.freeAutomaton();
	}

}
