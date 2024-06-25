package model;

import java.util.TimerTask;

class ActionTask extends TimerTask {
	protected Entity entity;
	protected long duration;

	ActionTask(Entity entity, long duration) {
		this.entity = entity;
		this.duration = duration;
	}

	@Override
	public void run() {
	}

	long getDuration() {
		return duration;
	}

}
