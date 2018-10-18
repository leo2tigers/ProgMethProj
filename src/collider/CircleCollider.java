package collider;

import math.CircleEquation;
import math.Vector;

public class CircleCollider extends Collider {
	protected double radius;

	public CircleCollider(Vector center, double radius) {
		super("CircleCollider");
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Vector[] getPoints() {
		return new Vector[] {this.center};
	}

	@Override
	public Vector getCenter() {
		return this.center;
	}

	@Override
	public void scale(double scale) {
		this.radius *= scale;
	}

	@Override
	public void scale(double scale, Vector ref) {
		this.center = Vector.add(this.center, Vector.multiply(Vector.subtract(this.center, ref), scale - 1));
		this.radius *= scale;
	}

	protected CircleEquation border() {
		return new CircleEquation(this.center, this.radius);
	}

	@Override
	protected boolean innerPoint(Vector point) {
		return (this.border().compute(point) <= 0);
	}

	public boolean overlap(Collider other) {
		if (other instanceof CircleCollider) {
			return (Vector.subtract(this.center, ((CircleCollider)other).center).norm() < this.radius + ((CircleCollider)other).radius);
		}
		else {
			return false;
		}
	}

	@Override
	public void translate(Vector vector) {
		this.center = Vector.add(this.center, vector);
	}

}
