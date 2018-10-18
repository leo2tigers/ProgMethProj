package gameComponent;

import collider.RectangleCollider;
import math.Box;
import math.Vector;

public enum MonsterType {
	AMI (
			"Artificial Monster I", 
			1,
			new Box(2, 2),
			new Vector(0, 1)
			),
	AMII (
			"Artificial Monster II", 
			2
			);
	
	private final String name;
	private final double maxHealth;
	private Box hitBox;
	private Vector relativeCenter;
	
	private MonsterType(String name, double maxHealth) {
		this.name = name;
		this.maxHealth = maxHealth;
	}
	
	private MonsterType(String name, double maxHealth, Box hitBox, Vector relativeCenter) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.hitBox = hitBox;
		this.relativeCenter = relativeCenter;
	}
	
	public Monster spawn() {
		Monster monster = new Monster(this.name, this, this.maxHealth);
		return monster;
	}
	
	public Monster spawn(Vector position) {
		Monster monster = new Monster(this.name, this, this.maxHealth, position);
		monster.setHitBox(new RectangleCollider(this.hitBox, Vector.add(position, this.relativeCenter)));
		return monster;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
