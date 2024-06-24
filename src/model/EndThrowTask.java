package model;

class EndThrowTask extends ActionTask {

	public EndThrowTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.freeAutomaton();
	}
	
}
