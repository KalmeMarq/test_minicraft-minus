package minicraft.item;

public class PotionEffect {
	private PotionType type;
	private int duration;

	public PotionEffect(PotionType type, int duration) {
		this.type = type;
		this.duration = duration;
	}

	public PotionType getPotionType() {
	  return type;
	}

	public int getDuration() {
		return this.duration;
	}

	public PotionEffect addDuration(int time) {
		this.duration += time;
		return this;
	}

	public PotionEffect substractDuration(int time) {
		this.duration -= time;
		return this;
	}
}
