package model;

class EndThrowTask extends ActionTask {

	EndThrowTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.freeAutomaton();
	}
	
}
