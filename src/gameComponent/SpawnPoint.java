package gameComponent;

import math.Vector;

public class SpawnPoint extends GameComponent {
	protected MonsterType monsterType;
	protected boolean spawnOnce;
	protected boolean spawnable;
	
	public SpawnPoint(MonsterType monsterType,Vector position, boolean spawnOnce) {
		this.monsterType = monsterType;
		this.position = position;
		this.spawnOnce = spawnOnce;
		this.spawnable = true;
	}
	
	public Monster spawn() {
		if (!this.spawnOnce || this.spawnable) {
			this.spawnable = false;
			return this.monsterType.spawn(this.position);
		}
		else {
			return null;
		}
	}
	
	public void resetSpawnable() {
		this.spawnable = true;
	}
}
