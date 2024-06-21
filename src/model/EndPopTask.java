package model;

class EndPopTask extends ActionTask {

	public EndPopTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.freeAutomaton();
	}

}
