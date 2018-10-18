package math;

public class LinearEquation extends Equation {
	private double x, y, c;

	public LinearEquation(double x, double y, double c) {
		super();
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	@Override
	public double compute(Vector vector) {
		return x*vector.x + y*vector.y + c;
	}
	
	@Override
	public String toString() {
		return "( " + x + " ) x + " + "( " + y + " ) y + ( " + c + " ) = 0";
	}
	
	// static
	
	public static LinearEquation line(Vector A, Vector B) {
		double dy = B.y - A.y;
		double dx = B.x - A.x;
		return new LinearEquation(dy, -dx, dx*A.y - dy*A.x);
	}
}
