package model;

class EndPopTask extends ActionTask {

	EndPopTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.freeAutomaton();
	}

}
