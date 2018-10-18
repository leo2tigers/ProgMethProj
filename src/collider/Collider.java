package collider;

import math.Vector;

abstract public class Collider {
	protected final String type;
	protected Vector center;
	
	public Collider(String type) {
		this.type = type;
	}
	
	abstract public Vector[] getPoints();
	
	abstract public Vector getCenter() ;
	
	abstract public void scale(double scale);
	
	abstract public void scale(double scale, Vector ref);
	
	abstract public void translate(Vector vector);
	
	abstract protected boolean innerPoint(Vector point);
	
	abstract public boolean overlap(Collider other);
}