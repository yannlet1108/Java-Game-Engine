package model;

public class EndMoveTask extends ActionTask {
	Direction direction;
	
	public EndMoveTask(Entity entity, long duration) {
		super(entity, duration);
		this.direction = direction;
	}

	@Override
	public void run() {
		entity.setForce(new Vector(0,0));
	}

}
