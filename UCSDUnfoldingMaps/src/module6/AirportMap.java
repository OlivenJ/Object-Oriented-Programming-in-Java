package module6;

import java.util.*;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	private List<Marker> aussieList;
	private List<Marker> chinaList;
	HashMap<String, Integer> airportCount;
	HashMap<String, Double> airportCountSD;
	
	public void setup() {
		// setting up PAppler
		size(800,600, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 50, 50, 750, 550);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		airportCount = new HashMap<String, Integer>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
	
			m.setRadius(5);
			airportList.add(m);
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		aussieList = new ArrayList<Marker>();
		chinaList = new ArrayList<Marker>();
		
		for(Marker m : airportList) {
			if(m.getProperty("country").equals("Australia")) {
				aussieList.add(m);
				
			}else if(m.getProperty("country").equals("China")) {
				chinaList.add(m);
			}
			
		}
		
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			//routeList.add(sl);
		}
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		//map.addMarkers(routeList);
		
		//map.addMarkers(airportList);
		map.addMarkers(aussieList);
		countPerCountry();
		printData();
		
	}
	
	public void draw() {
		background(0);
		map.draw();
		
	}
	
	
	
	public void countPerCountry() {
		for(Marker m : airportList) {
			String countryName = (String) m.getProperty("country");
			if(!airportCount.containsKey(countryName)) {
				
				airportCount.put(countryName, 1);
			}else {
				int counter = airportCount.get(countryName); 
				airportCount.put(countryName, counter + 1 );
				
			}
		
			
		}
		
	}
	
	public void countPerCountrySD() {
		double sum = 0.0;
		double count = 0.0;
		double avg = 0.0;
		double sd = 0.0;
		
		airportCountSD = new HashMap<String, Double>();
		
		for(String s : airportCount.keySet()) {
			
			sum += airportCount.get(s);
			count ++ ;
			
		}
		avg = sum / count;
		sum = 0.0;
		
		for(String s: airportCount.keySet()) {
			
			sum += Math.pow(airportCount.get(s) - avg,2);
			
		}
		sd = sum / count;
		
		for(String s: airportCount.keySet()) {
			
			airportCountSD.put(s, (airportCount.get(s) - avg)/sd);
			
		}
	}
	
	public void printData() {
		
		for(String s :airportCountSD.keySet()) {
			
			System.out.println(s + airportCountSD.get(s));
			
		}
		
	}
	

}
