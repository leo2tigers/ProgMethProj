package gameComponent;

import java.util.ArrayList;

import collider.RectangleCollider;
import gameLogic.Damage;
import math.Vector;

abstract class Creature extends GameComponent {
	public String name;
	
	protected double mass;
	protected double health;
	protected double maxHealth;
	public double armour;
	public double attackPower;
	
	protected boolean jumping;
	public boolean isAlive;
	
	public Vector velocity;
	public Vector orientation;
	public ArrayList<Vector> force;
	
	protected RectangleCollider hitBox;
	protected RectangleCollider footBox;
	
	protected Platform currentPlatform;
	
	protected Map currentMap;
	
	
	public Creature() {
		this.isAlive = true;
	}
	
	public Creature(String name, double maxHealth) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.isAlive = true;
	}
	
	public Creature(double maxHealth) {
		this.setMaxHealth(maxHealth);
		this.setHealth(maxHealth);
		this.isAlive = true;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass > 0 ? mass : 0;
	}

	public double getSpeed() {
		return this.velocity.norm();
	}
	
	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = (health > 0) ? ((health < this.maxHealth) ? health : this.maxHealth) : 0;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = (maxHealth > 0) ? maxHealth : 0;
	}
	
	public RectangleCollider getHitBox() {
		return hitBox;
	}

	public void setHitBox(RectangleCollider hitBox) {
		this.hitBox = hitBox;
	}

	public RectangleCollider getFootBox() {
		return footBox;
	}

	public void setFootBox(RectangleCollider footBox) {
		this.footBox = footBox;
	}

	public Platform getCurrentPlatform() {
		return currentPlatform;
	}

	public void setCurrentPlatform(Platform currentPlatform) {
		this.currentPlatform = currentPlatform;
	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}

	abstract public void getHit(Damage damage);
	
	public Vector totalForce() {
		return Vector.add(Vector.add(force), Vector.multiply(Vector.Gravity, (this.currentMap.isGravityEnabled()) ? this.mass : 0));
		
	}
	
	protected void translate(Vector deltaPosition) {
		this.position = Vector.add(this.position, deltaPosition);
		this.hitBox.translate(deltaPosition);
		if(this.footBox != null) {
			this.footBox.translate(deltaPosition);
		}
	}
	
	protected void accel(Vector deltaVelocity) {
		this.velocity = Vector.add(this.velocity, deltaVelocity);
	}
	
	public void updatePosition(double deltaTime) {
		Vector force = this.totalForce();
		Vector deltaPosition = Vector.add(
				Vector.multiply(this.velocity, deltaTime), 
				this.mass != 0 ? Vector.multiply(force, (deltaTime*deltaTime) / (2*this.mass)) : Vector.Origin
						);
		Vector deltaVelocity = this.mass != 0 ? Vector.multiply(force, deltaTime / this.mass) : Vector.Origin;
		
		this.translate(deltaPosition);
		this.accel(deltaVelocity);
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + "\n\tHealth = " + this.health + " / " + this.maxHealth + "\n\tStatus = " + ((this.isAlive) ? "Alive" : "Dead") + "\n\tPosition = " + this.position;
	}
	
	public void show() {
		System.out.println(this);
	}
}