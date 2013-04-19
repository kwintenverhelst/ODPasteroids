package asteroids.model;

import java.util.Random;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class to create asteroids in space for playing asteroids. 
 * A object has 2D coordinates (where the x-axis is horizontal and the y-axis is vertical)
 * in km for its position, a velocity in km/s, a mass, a world and a radius.
 * The objects are able to move, turn.
 * This class can also predict time and place of collision between 2 objects or the wand of the world the object is in.
 * When a asteroid dies it spawns two lesser asteroids
 * Asteroid is a subclass of ObjectInSpace
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Asteroid extends ObjectInSpace {
	
	

	/**
	 * Initialize a new asteroid with given x-coordinate, given y-coordinate, given
	 * velocity in the x direction, given velocity in the y direction and given
	 * radius
	 * 
	 * @param x
	 *            The x-coordinate of the position for this new asteroid.
	 * @param y
	 *            The y-coordinate of the position for this new asteroid.
	 * @param velocityX
	 *            The velocity in the x direction for this new asteroid.
	 * @param velocityY
	 *            The velocity in the y direction for this new asteroid.
	 * @param radius
	 *            The radius for this new asteroid.
	 *            
	 * @effect the given x-coordinate, the given y-coordinate, the given velocity in the x direction,
	 * 			the given velocity in the y direction and the given radius
	 * 			are set as the x-coordinate, the y-coordinate, the velocity in the x direction,
	 *     		the velocity in the y direction and the radius of this new asteroid
	 *     		the mass is calculated on the basis of the radius of this new asteroid
	 */
	public Asteroid(double x, double y, double velocityX, double velocityY,
			double radius) {
		this(x, y, velocityX, velocityY, radius, null);
		
	}
	
	/**
	 * Initialize a new asteroid with given x-coordinate, given y-coordinate, given
	 * velocity in the x direction, given velocity in the y direction, a given parent and given
	 * radius
	 * 
	 * @param x
	 *            The x-coordinate of the position for this new asteroid.
	 * @param y
	 *            The y-coordinate of the position for this new asteroid.
	 * @param velocityX
	 *            The velocity in the x direction for this new asteroid.
	 * @param velocityY
	 *            The velocity in the y direction for this new asteroid.
	 * @param radius
	 *            The radius for this new asteroid.
	 * @param asteroidParent
	 *            The parent of this new asteroid.
	 *            
	 * @effect the given x-coordinate, the given y-coordinate, the given velocity in the x direction,
	 * 			the given velocity in the y direction and the given radius
	 * 			are set as the x-coordinate, the y-coordinate, the velocity in the x direction,
	 *     		the velocity in the y direction and the radius of this new asteroid
	 *     
	 * @effect the mass is calculated on the basis of the radius of this new asteroid
	 * 
	 * @post the given parent is the parent of this asteroid
	 */
	private Asteroid(double x, double y, double velocityX, double velocityY,
			double radius, Asteroid asteroidParent) {

		super(x, y, velocityX, velocityY, radius, 1);
		setMass(calculateMass(radius));
		this.asteroidParent = asteroidParent;
	}

	/**
	 * Variable for the density of a asteroid in kg/km^3.
	 */
	private final static double DENSITY = 7.8 * Math.pow(10, 12);
	
	/**
	 * Variable for the density of a asteroid in kg/km^3.
	 */
	private final Asteroid asteroidParent;

	/**
	 * Let this asteroid die. Meaning that the asteroid will split in two if the radius is big enough.
	 * 
	 * @effect this asteroid is terminated 
	 * @effect if the radius of this asteroid is bigger or equal to 30 there will be two asteroids been add to the world of this asteroid
	 * 			they will have a radius that is halve of the radius of this asteroid, their speed will be 150 multiplied the speed of this asteroid and have a random direction opposite from each other 
	 * 			and they will spawn on a distance of the halve radius of this asteroid from this asteroids center on a single line which has a random angle with the x-axis (it is the same angle as the velocity)
	 */
	public void die() {
		World thisWorld = this.getWorld();
		this.terminate();
		if (this.getRadius() >= 30) {
			Random random = new Random();
			double angle = 2 * Math.PI * random.nextDouble();
			Velocity newVelocity = Velocity.createVelocityInRandomDirection(1.5 * Velocity.norm(Velocity.createVelocity(this.getVelocityX(), this.getVelocityY())),angle);
			double newRadius = this.getRadius() / 2;
			
			Asteroid asteroid1 = new Asteroid( this.getX() + newRadius * Math.cos(angle) + Util.EPSILON, this.getY() + newRadius * Math.sin(angle) + Util.EPSILON,  newVelocity.getXCoordinate() + Util.EPSILON, newVelocity.getYCoordinate() + Util.EPSILON, newRadius, this);
			Asteroid asteroid2 = new Asteroid( this.getX() - newRadius * Math.cos(angle) - Util.EPSILON, this.getY() - newRadius * Math.sin(angle) - Util.EPSILON,  -newVelocity.getXCoordinate() - Util.EPSILON, -newVelocity.getYCoordinate() - Util.EPSILON, newRadius, this);
			
			
			thisWorld.addObjectInSpace(asteroid1);
			thisWorld.addObjectInSpace(asteroid2);
		}
	}

	/**
	 * Returns the density of a asteroid in km/km³.
	 */
	@Basic
	@Immutable
	public double getDensity() {
		return DENSITY;
	}
	
	/**
	 * Returns the parent of a asteroid in .
	 */
	@Basic
	@Immutable
	public Asteroid getParent() {
		return asteroidParent;
	}

	/**
	 * Calculate the mass of a asteroid on the basis of the radius
	 *  
	 * @param radius
	 * 			the radius of the asteroid you want to calculate the mass from
	 * 
	 * @return the mass of this asteroid on the basis of the radius
	 */
	public double calculateMass(double radius) {

		return 4 * Math.PI * Math.pow(radius, 3) * getDensity() / 3;
	}

	/**
	 * Check whether class of the given object is Asteroid or a subclass of Asteroid
	 * 
	 * @param object
	 * 			the object you want to check	 
	 * @return true if the class of the given object is Asteroid or a subclass of Asteroid
	 */
	public static boolean isAsteroid(Object object) {
		return Asteroid.class.isAssignableFrom(object.getClass());
	}
}
