package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Asteroid extends ObjectInSpace {
	
	public Asteroid(){
		this(0,0,0,0,1);
	}
	
	public Asteroid(double x, double y, double velocityX, double velocityY,
			double radius){
		
		super(x,y,velocityX,velocityY,radius, 1);
		setMass(calculateMass(radius));
	}
	
	private final static double DENSITY = 7.8*Math.pow(10, 12);
	
	/**
	 * Terminate this asteroid.
	 */
	public  void terminate(){
		
	}
	
	/**
	 * this asteroid collides with the given object
	 */
	public void collide(ObjectInSpace object){
		if(object != null){
			if(Asteroid.class.isAssignableFrom(object.getClass())){
				
			} else if(Bullet.class.isAssignableFrom(object.getClass())){
				
			} else if(Ship.class.isAssignableFrom(object.getClass())){
				
			}
			
		}
	}
	
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
