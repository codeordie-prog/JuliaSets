
public class BoundaryChecker {
	
	/*This class has an algorithm that checks whether a given point is within the MandelBrot Set or not
	 * 
	 * if in the Mandelbrot set it returns True if not return value is False in the boolean method isMandelBrot
	 * 
	 * its crucial in tracking the JuliaSets since we know they are within the MandelBrot Set**/
	
	
	private final int MAX_ITERATIONS = 1000;
	private final double ESCAPE_THRESHOLD = 4;
	private int iterations = 0;
	
	//Compute MandelBrot sets
	
	public int mandelBrotSets(double cReal, double cImag) {
		
		double xTemp;
		double x = 0;
		double y = 0;
		
		while(x * x + y * y < ESCAPE_THRESHOLD && iterations < MAX_ITERATIONS) {
			
			xTemp = x * x - y * y + cReal;
			
			y = 2 * y * x + cImag;
			
			x = xTemp;
			
			iterations++;
		}
		
		return iterations;
	}
	
	//check whether its a Mandelbrot
	
	public boolean isMandelBrot(double x, double y) {
		
		
		return mandelBrotSets(x,y) == MAX_ITERATIONS; // returns false if point lies outside the MandelBrotSet
	}
	
	
	
}
