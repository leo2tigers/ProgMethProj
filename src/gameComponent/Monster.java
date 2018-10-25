package gameComponent;

import collider.Collider;
import collider.RectangleCollider;
import gameLogic.Damage;
import gameLogic.Skill;
import math.Vector;

public class Monster extends Creature {
	
	public MonsterType type;
	
	public Monster(String name, MonsterType type, double maxHealth){
		super(name, maxHealth);
		this.type = type;
	}
	
	public Monster(String name, MonsterType type, double maxHealth, Vector position){
		super(name, maxHealth);
		this.type = type;
		this.position = position;
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
	
	public boolean attack() {
		
		Skill attack = null; // waiting for edit in the future
		Damage damage =attack.damage;
		Collider damageCollider = new RectangleCollider(
				attack.damageBox, 
				Vector.add(
						attack.relativePosition,
						this.position
						)
				);
		
		System.out.println(this.name + " attacks with " + attack.name + " {\n\tDamage = " + damage + "\n\tCollider = " + damageCollider + "\n}");
		
		boolean isHit = false;
		if (damageCollider.overlap(this.currentMap.player.hitBox)) {
			isHit = true;
			System.out.println("Hits Player!");
			this.currentMap.player.getHit(damage);
		}
		return isHit;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.type + " : " + super.toString();
	}
	
}