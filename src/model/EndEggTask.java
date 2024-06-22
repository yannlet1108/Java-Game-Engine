package model;

class EndEggTask extends ActionTask {

	public EndEggTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.freeAutomaton();
	}
}
