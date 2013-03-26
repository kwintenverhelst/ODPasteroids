package asteroids.model;

import java.util.Random;

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
	
	public Asteroid(double x, double y, double velocityX, double velocityY,
			double radius, World world, Random random){
		super(x,y,velocityX*random.nextDouble(),velocityY,radius, 1);
		setMass(calculateMass(radius));
		setWorld(world);
	}
	
	private final static double DENSITY = 7.8*Math.pow(10, 12);
	
	/**
	 * Terminate this asteroid.
	 */
	public  void terminate(){
		
	}
	
	/**
	 * Terminate this asteroid.
	 */
	public  void Die(){
		if(isValidWorld(this.getWorld()) && (this.getRadius() >=30)) {
			
		}
		this.terminate();
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
