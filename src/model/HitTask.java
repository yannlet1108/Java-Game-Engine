package model;

import java.util.Timer;

class HitTask extends ActionTask {
	Direction direction;

	HitTask(Entity entity, long duration, Direction direction) {
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
		ActionTask endHitTask = new EndHitTask(entity, duration);
		entity.currenTask = endHitTask;
		timer.schedule(endHitTask, endHitTask.getDuration());
	}

}
