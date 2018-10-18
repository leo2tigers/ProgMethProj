package gameLogic;

import math.Box;
import math.Vector;

public class Skill {
	
	public String name;
	public Damage damage;
	public Box damageBox;
	public Vector relativePosition;
	
	protected double preSkillDelay, skillDuration, postSkillDelay;
	
	public Skill() {}
	
	public Skill(Damage damage, Box damageBox, Vector relativePosition) {
		this.damage = damage;
		this.damageBox = damageBox;
		this.relativePosition = relativePosition;
	}
	
	public Skill(String name, Damage damage, Box damageBox, Vector relativePosition) {
		this.name = name;
		this.damage = damage;
		this.damageBox = damageBox;
		this.relativePosition = relativePosition;
	}
	
	public Skill(PlayerSkill playerSkill, Vector Orientation) {
		this.name = playerSkill.name;
		this.damage = playerSkill.damage;
		this.damageBox = playerSkill.damageBox;
		this.relativePosition = Vector.add(
				playerSkill.relativePostiion, 
				Vector.multiply(
						Orientation, 
						playerSkill.orientationCoeff
						)
				);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
