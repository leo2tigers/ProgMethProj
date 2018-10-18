package math;

import java.util.ArrayList;

public class Vector {
	public double x,y;
	
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double norm() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	
	@Override
	public String toString() {
		return "( " + x + " , " + y + " )";
	}
	
	// Static Classes and Methods
	
	public static Vector Origin = new Vector(0, 0);
	public static Vector Gravity = new Vector(0, -9.8);
	
	public static Vector add(Vector A, Vector B) {
		return new Vector(A.x + B.x, A.y + B.y);
	}
	
	public static Vector add(Vector...vectors) {
		double x = 0, y = 0;
		for(Vector v : vectors) {
			x += v.x;
			y += v.y;
		}
		return new Vector(x, y);
	}
	
	public static Vector subtract(Vector A, Vector B) {
		return new Vector(A.x - B.x, A.y - B.y);
	}
	
	public static Vector multiply(Vector V, double c) {
		if (V == null) {
			return Vector.Origin;
		}
		return new Vector(V.x * c, V.y * c);
	}

	public static Vector add(ArrayList<Vector> vectors) {
		double x = 0, y = 0;
		if (vectors == null) {
			return Vector.Origin;
		}
		for (Vector vector : vectors) {
			x += vector.x;
			y += vector.y;
		}
		return new Vector(x, y);
	}
}
