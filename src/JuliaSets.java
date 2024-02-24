
public class JuliaSets {

	/*
	 *
	 * This class has the JuliaSets algorithm
	 * The only difference between the Juliaset Algorithm and the MandelBrot is that in JuliaSets complex
	 * number C is kept constant while with the MandelBrot Set, C is dynamic and changes.
	 *  
	 * **/
	

	private final int maxIterations = 1000;
	private final int  escapeThreshold = 4;
	private int iterations = 0;
	
	
	public int computeJuliaSet(double zx, double zy, double cReal, double cImag) {
		
		/*
		 * 
		 * @ return integer value iterations
		 * @param cReal and cImag - complex number C.
		 * 
		 * **/
        iterations = 0;
        while (zx * zx + zy * zy < escapeThreshold * escapeThreshold && iterations < maxIterations) {
            double xTemp = zx * zx - zy * zy + cReal;
            zy = 2 * zx * zy + cImag;
            zx = xTemp;
            iterations++;
        }
        return iterations;
    }
}
