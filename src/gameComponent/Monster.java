package gameComponent;

import gameLogic.Damage;
import math.Vector;

public class Monster extends Creature {
	protected MonsterType type;
	
	public Monster() {
		super(1);
	}
	
	public Monster(MonsterType type, double maxHealth){
		super(maxHealth);
		this.type = type;
	}
	
	public Monster(String name, MonsterType type, double maxHealth){
		super(name, maxHealth);
		this.type = type;
	}
	
	public Monster(String name, MonsterType type, double maxHealth, Vector position){
		super(name, maxHealth);
		this.type = type;
		this.position = position;
	}
	
	public MonsterType getType() {
		return type;
	}

	public void setType(MonsterType type) {
		this.type = type;
	}

	@Override
	public void getHit(Damage damage) {
		damage = this.damageReduction(damage);
		System.out.println(this.name + " recieves " + damage + ".");
		this.setHealth(this.health - damage.point);
		if(this.health <= 0) {
			this.isAlive = false;
			System.out.println(this.name + " is dead!");
		}
	}
	
	public Damage damageReduction(Damage damage) {
		double newDamage = (damage.point - this.armour > 0) ? (damage.point - this.armour) : 0;
		Vector newForce = Vector.multiply(damage.force, newDamage/damage.point);
		return new Damage(newDamage, newForce);
	}
	/*
	public boolean attack() {
		Damage damage = this.attackSkill.damage;
		Collider damageCollider = new RectangleCollider(
				this.attackSkill.damageBox, 
				Vector.add(
						this.attackSkill.relativePosition,
						this.position
						)
				);
		boolean isHit = false;
		
		for (Monster monster : this.currentMap.monsters) {
			if(damageCollider.overlap(monster.hitBox)) {
				isHit = true;
				monster.getHit(damage);
			}
		}
		
		return isHit;
	}
	*/
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.type + " : " + super.toString();
	}
}