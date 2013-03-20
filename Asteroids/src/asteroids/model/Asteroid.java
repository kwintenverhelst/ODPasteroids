package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Asteroid extends ObjectInSpace {
	
	public Asteroid(){
		this(0,0,0,0,1,1);
	}
	
	public Asteroid(double x, double y, double velocityX, double velocityY,
			double radius, double mass){
		
		super(x,y,velocityX,velocityY,radius, mass );
		setMass(calculateMass(radius));
	}
	
	private final static double DENSITY = 7.8*Math.pow(10, 12);
	
	/**
	 * Returns the density in km/km³.
	 */
	@Basic
	@Immutable
	public double getDensity() {
		return DENSITY;
	}
	
	public double calculateMass (double radius){
		
		return 4*Math.PI*Math.pow(radius, 3)*getDensity()/3;
	}
}
