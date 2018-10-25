package gameComponent;

import collider.Collider;
import collider.RectangleCollider;
import gameLogic.Damage;
import gameLogic.PlayerSkill;
import gameLogic.Skill;
import math.Vector;

public class Player extends Creature {
	
	public Player(double maxHealth) {
		super("Player", maxHealth);
	}
	
	@Override
	public void getHit(Damage damage) {
		
		damage = damageReduction(damage);
		this.setHealth(this.health - damage.point);
		this.force.add(damage.force);
		
		if(this.health <= 0) {
			this.isAlive = false;
		}
		else {
			this.isAlive = true;
		}
		
	}
	
	public Damage damageReduction(Damage damage) {
		double newDamage;
		newDamage= (damage.point - this.armour > 0) ? damage.point - this.armour : damage.point;
		return new Damage(newDamage, damage.force);
	}
	
	public boolean attack() {
		
		Skill normalAttack = new Skill(PlayerSkill.NORMAL_ATTACK, this.orientation);
		Damage damage = normalAttack.damage;
		Collider damageCollider = new RectangleCollider(
				normalAttack.damageBox, 
				Vector.add(
						normalAttack.relativePosition,
						this.position
						)
				);
		
		System.out.println(this.name + " attacks with " + normalAttack.name + " {\n\tDamage = " + damage + "\n\tCollider = " + damageCollider + "\n}");
		
		boolean isHit = false;
		for (Monster monster : this.currentMap.monsters) {
			if(damageCollider.overlap(monster.hitBox)) {
				isHit = true;
				System.out.println("Hits " + monster.name +"!");
				monster.getHit(damage);
			}
		}
		
		return isHit;
		
	}
	
	public void activateSkill(Skill skill) {
		System.out.println("activate " + skill);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Player : " + super.toString();
	}
}