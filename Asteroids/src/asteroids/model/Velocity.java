package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create velocity in a 2D setting and with a velocity in the x direction, a velocity in the y direction
 *  and a speedlimit in km/s
 *
 * @invar VelocityX, Velocity Y doubles must be valid numbers.
 * 		| isValidDouble(getVelocityX()) && isValidDouble(getVelocityY()) 
 * @invar The velocity must be a valid velocity, less than the speed limit.
 * 		| isValidVelocity(getVelocityX(),getVelocityY())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Velocity extends VectorInSpace{
	
	
	/**
	 * Variable for the speed limit in km/s.
	 */
	private final double speedLimit = 300000;
	
	/**
	 * Initialize a new velocity on default, the x- and y-velocity are both zero
	 * 
	 * @effect the x- and y-velocity are both zero
	 * 			| setVelocity(0, 0)
	 */
	public Velocity (){
		
		super(0, 0);
	}
	
	/**
	 * Initialize a new velocity with the given the x- and y-velocity
	 * @param velocityX
	 * 			the x-coordinate of this velocity
	 * @param velocityY
	 * 			the y-coordinate of this velocity
	 * @effect the x- and y-velocity are the given the x- and y-velocity
	 * 			|setVelocity(velocityX, velocityY)
	 */
	public Velocity (double velocityX, double velocityY){
		super(velocityX, velocityY);
		if(!isValidVelocity(velocityX,  velocityY )){
			double direction = getDirection(velocityX, velocityY);
			new Velocity(this.getSpeedLimit() * Math.cos(direction), this.getSpeedLimit() * Math.sin(direction));
		}
	}
	
	/**
	 * Returns the speed limit  in km/s.
	 */
	@Basic
	@Immutable
	public double getSpeedLimit() {
		return speedLimit;
	}

	/**
	 * Checks if the velocity is a valid number and if it is less or equal to
	 * the speed limit.
	 * 
	 * @param velocityX
	 *            velocity in the x direction in km/s
	 * @param velocityY
	 *            velocity in the y direction in km/s
	 * @effect Returns true if the velocity is a valid number and if it is less
	 *         or equal to the speed limit.
	 *         |Util.fuzzyLessThanOrEqualTo(getVelocity(velocityX, velocityY),
	 *         getSpeedLimit())
	 */
	public boolean isValidVelocity(double velocityX, double velocityY) {
		return Util.fuzzyLessThanOrEqualTo(getVelocity(velocityX, velocityY),
				getSpeedLimit());
	}

	/**
	 * Calculates the norm of the given velocityX and velocityY in km/s.
	 * 
	 * @param velocityX
	 *            velocity in the x direction in km/s
	 * @param velocityY
	 *            velocity in the y direction in km/s
	 * @return The square root of the sum of powers of velocityX and velocityY
	 *         |result == Math.hypot(velocityX, velocityY).
	 */
	private double getVelocity(double velocityX, double velocityY) {
		return Math.hypot(velocityX, velocityY);
	}


	/**
	 * returns the angle of the velocity, which has the components velocityX and
	 * velocityY
	 * 
	 * @param velocityX
	 *            The x component of the velocity
	 * @param velocityY
	 *            The y component of the velocity
	 * @return the angle of the velocity
	 * 		 	| result == Math.atan(velocityY/velocityX)
	 */
	public double getDirection(double velocityX, double velocityY) {
		return Math.atan(velocityY / velocityX);
	}
}
