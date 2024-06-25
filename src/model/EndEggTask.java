package model;

class EndEggTask extends ActionTask {

	EndEggTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.freeAutomaton();
	}
}
