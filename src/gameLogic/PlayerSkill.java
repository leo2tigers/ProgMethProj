package gameLogic;

import math.Box;
import math.Vector;

public enum PlayerSkill {
	
	NORMAL_ATTACK 
	(
			"Normal Attack", 
			new Damage(1, null), 
			new Box(1, 1), 
			new Vector(0, 1), 
			2
			),
	
	UNSHEALTH_SLASH	
	(
			"Unshealth Slash", 
			new Damage(1, null), 
			new Box(1, 1), 
			new Vector(0, 1), 
			2
			),
	
	OVERHEAD_SLASH 	
	(
			"Overhead Slash", 
			new Damage(1, null), 
			new Box(1, 1), 
			new Vector(0, 1), 
			2
			),
	
	PUNCH 			
	(
			"Punch", 
			new Damage(1, null), 
			new Box(1, 1), 
			new Vector(0, 1), 
			2
			),
	
	UPWARD_SLASH 	
	(
			"Upward Slash", 
			new Damage(1, null), 
			new Box(1, 1), 
			new Vector(0, 1), 
			2
			),
	
	BACKWARD_SWEEP	
	(
			"Backward Sweep", 
			new Damage(1, null), 
			new Box(1, 1), 
			new Vector(0, 1), 
			2
			),
	FIRST_GUAGE_SLASH	
	(
			"Backward Sweep", 
			new Damage(1, null), 
			new Box(1, 1), 
			new Vector(0, 1), 
			2
			);
	
	protected final String name;
	protected final Damage damage;
	protected final Box damageBox;
	protected final Vector relativePostiion;
	protected final double orientationCoeff;
	
	private PlayerSkill(String name, Damage damage, Box damageBox, Vector relativePostiion, double orientationCoeff) {
		this.name = name;
		this.damage = damage;
		this.damageBox = damageBox;
		this.relativePostiion = relativePostiion;
		this.orientationCoeff = orientationCoeff;
	}
	
	public Skill skill(Vector orientation) {
		return new Skill(this, orientation);
	}
}
