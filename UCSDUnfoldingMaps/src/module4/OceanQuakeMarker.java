package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		
float magni = this.getMagnitude();

				
		
		// save previous styling
		pg.pushStyle();
			if(!this.isOnLand()) {
		// determine color of marker from depth
		//colorDetermine(pg);
		
		// call abstract method implemented in child class to draw marker shape
		//drawEarthquake(pg, x, y);
		
		//pg.fill(255,255,255);
		if(magni > THRESHOLD_MODERATE ) {
			pg.ellipse(x,y,12,12);
			
		}else if(magni >= THRESHOLD_LIGHT &&
				magni<= THRESHOLD_MODERATE) {
			pg.ellipse(x,y, 7,7);
			
		}else {
				pg.ellipse(x, y, 5, 5);
		}
		
	}
	
	}

	

}
