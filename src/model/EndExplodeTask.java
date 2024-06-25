package model;

class EndExplodeTask extends ActionTask {

	EndExplodeTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.model.addEntityToRemove(entity);
		//entity.freeAutomaton();
	}

}
