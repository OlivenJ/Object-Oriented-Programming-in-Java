package GUIModule;

import processing.core.*;

public class MyDisplay extends PApplet{

	public void setup() {
		
		size(400, 400);
		background(15,39,47);
	}
	
	public void draw() {
		//ellipseMode(RADIUS);
		fill(245, 195, 66);
		ellipse(200, 200, 390, 390);
		
		fill(10,10,10);
		ellipse(125, 100, 50, 70);
		
		fill(0,0,0);
		ellipse(275,100,50, 70);
		
		noFill();
		arc(200, 275, 75, 175, 0, PI);
		
	}
	
	
}
