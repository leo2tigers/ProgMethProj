package math;

public class CircleEquation extends Equation {
	private double x, y, r;

	public CircleEquation(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
		// TODO Auto-generated constructor stub
	}
	
	public CircleEquation (Vector point, double radius) {
		this.x = point.x;
		this.y = point.y;
		this.r = radius;
	}

	@Override
	public double compute(Vector vector) {
		return Math.pow(vector.x - x, 2) + Math.pow(vector.y - y, 2) - Math.pow(this.r, 2);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( x -  ( " + x + " ) )^2 + ( y - ( " + y + " ) )^2 - ( " + r + " )^2 = 0" ;
	}

}
