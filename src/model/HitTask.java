package model;

import java.util.Timer;

public class HitTask extends ActionTask {
	Direction direction;

	public HitTask(Entity entity, long duration) {
		super(entity, duration);
		this.direction = direction;
	}

	@Override
	public void run() {
		if (direction == null) {
			entity.hit();
		} else {
			entity.hit(direction);
		}
		Timer timer = new Timer();
		ActionTask endMoveTask = new EndHitTask(entity, duration);
		timer.schedule(endMoveTask, endMoveTask.getDuration());
	}

}
