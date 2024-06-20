package model;

import java.time.Duration;
import java.util.Date;
import java.util.TimerTask;

public class ActionTask extends TimerTask {
	protected Entity entity;
	protected long duration;

	public ActionTask(Entity entity, long duration) {
		this.entity = entity;
		this.duration = duration;
	}

	@Override
	public void run() {
	}

	public long getDuration() {
		return duration;
	}

}
