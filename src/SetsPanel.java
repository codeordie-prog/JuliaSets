import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SetsPanel extends JPanel{
	
	/***
	 * 
	 * this class draws the julia sets and stores images on an ArrayList
	 * 
	 * the method saveFractals will save .png files on the directory filePath.
	 * 
	 * 
	 * */
	
	//initialize instance variables
	
	private ArrayList<BufferedImage> fractals;
	private ArrayList<Double>points;
	private BufferedImage image, fractal;
	private JuliaSets julia = new JuliaSets();
	private GenerateJuliaPoints generator = new GenerateJuliaPoints();
	private int color;
	private int maxIterations;
	private int iterations;
	private int currentFractal = 0;
	private int width = 800;
	private int height = 500;
	private double cReal, cImag;
	private File file;
	private String filePath, fileName;
	
	
	SetsPanel(){
		
		generateJuliaSets();
	}

	//method generates the images of the fractals
	
	private void generateJuliaSets() {
		
		fractals = new ArrayList<>();
		maxIterations = 1000;
		iterations = 0;
		points = generator.generateJuliaPoints(); // generate the points arraylist
		filePath = "C://Users//LENOVO//Pictures//JuliaSets//"; //path for image storage
		
		//loops
		
	
		/**
		 * *This method is very crucial
		 * 
		 * first loop iterates over the points array lists grouping them in pairs cReal and cImaginary
		 * 
		 * These points are passed as parameters for the computeJuliaSets method as constant C
		 * 
		 * second and third loops iterate over the pane width and height generating x and y points which
		 * are used to generate zx and zy for the computeJuliaSets algorithm
		 * 
		 * iterations returned by the computeJuliaSets Method can be used for color generation as shown
		 * 
		 * **/
		
		for(int i = 0 ; i<points.size();i+=2) { // generates C points
			cReal = points.get(i);
			cImag = points.get(points.size()-1-i);
			fractal = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // for each point an image
			
			for (int x = 0 ; x<width; x++) {
				
				for(int y = 0; y<height; y++) {
					
					//mapping coordinates
					
					double zx = 1.5 * (x - width / 2) / (0.5 * width);
                    double zy = (y - height / 2) / (0.5 * height);
                    
                    iterations = julia.computeJuliaSet(zx, zy, cReal, cImag);
                    
                    //color
                   color = iterations == maxIterations ? Color.CYAN.getRGB() :
                	   Color.HSBtoRGB(iterations/256f, 1, iterations/(iterations + 60f));
                   
                   fractal.setRGB(x, y, color);
                   
                
                   
                    
				}
			}
			
			fractals.add(fractal);
			
			//saveFractals(fractals); // commented out to avoid ovewritting existing images.
			
			
			
		}
		image = fractals.get(0);
		
			
	}
	
	//save images
	
	public void saveFractals(ArrayList<BufferedImage>list) {
		
		
		for(int i = 0 ; i< list.size(); i++) {
			
			try {
				
				fileName = String.valueOf(i + ".png");
				file = new File(filePath, fileName);
				ImageIO.write(list.get(i), "png", file);
				
			}catch(IOException e) {
				
				System.out.println(e.getStackTrace());
			}
		}
	}

	//paint image
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fractals.get(currentFractal), 0, 0, this);
		
	}
	
	public int getTotalFractals() {
		
		return fractals.size();
	}
	
	public void setCurrentFractal(int index) {
		
		currentFractal = index;
	}
}
