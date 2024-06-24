package model;

public class EndExplodeTask extends ActionTask {

	public EndExplodeTask(Entity entity, long duration) {
		super(entity, duration);
	}

	@Override
	public void run() {
		entity.setState(State.WAITING);
		entity.model.toRemove.add(entity);
		entity.freeAutomaton();
	}

}
