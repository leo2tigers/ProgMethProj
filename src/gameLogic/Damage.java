package gameLogic;

import math.Vector;

public class Damage {
	public double point;
	public Vector force;
	
	public Damage(double point, Vector force) {
		this.point = point;
		this.force = force;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Damage " + this.point + " with force " + this.force;
	}
}
