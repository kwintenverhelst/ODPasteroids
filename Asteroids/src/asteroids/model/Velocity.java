package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create velocity in a 2D setting and with a velocity in the x
 * direction, a velocity in the y direction and a speedlimit in km/s 
 * you cannot make a velocity that has is greater then the speedlimit 
 * (where the x-axis is horizontal and the y-axis is vertical)
 * this class is a subclass of VectorInSpace class
 * 
 * @invar The velocity must be a valid velocity, less than the speed limit. 
 * 			| isValidVelocity(getVelocityX(),getVelocityY())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
@Value
public class Velocity extends VectorInSpace {


	/**
	 * Initialize a new velocity on default, the x- and y-coordinate of the velocity are both zero
	 * 
	 */
	public Velocity() {
		super(0, 0);
	}

	/**
	 * Initialize a new velocity with the given the x- and y-velocity
	 * 
	 * @param velocityX
	 *            the x-coordinate of this velocity
	 * @param velocityY
	 *            the y-coordinate of this velocity
	 */
	@Raw
	private Velocity(double velocityX, double velocityY) {
		super(velocityX, velocityY);
	}

	/**
	 * Variable for the speed limit in km/s.
	 */
	private final double speedLimit = 300000;
	
	/**
	 * Initialize a new velocity with the given the x- and y-velocity and if they make a velocity that is bigger then the speedlimit, 
	 * they will be set in the same direction but together they will make the value of the speedlimit.
	 * 
	 * @param velocityX
	 *            the x-coordinate of this velocity
	 * @param velocityY
	 *            the y-coordinate of this velocity
	 *
	 * @return a new velocity that is less or equal then the speedlimit
	 * 			| if(new Velocity(velocityX, velocityY).hasValidVelocity()))
	 * 			| then result == new Velocity(velocityX, velocityY).hasValidVelocity())
	 * 			| else result == new Velocity(velocity.getSpeedLimit()*Math.cos(direction), velocity.getSpeedLimit()*Math.sin(direction));
	 */
	public static Velocity createVelocity(double velocityX, double velocityY) {
		Velocity velocity = new Velocity(velocityX, velocityY);
		if (!(velocity.hasValidVelocity())) {
			double direction = VectorInSpace.getDirection(velocity);
			velocity = new Velocity(velocity.getSpeedLimit()*Math.cos(direction), velocity.getSpeedLimit()*Math.sin(direction));
		}
		return velocity;

	}
	
	/**
	 * Initialize a new velocity with the given value and with the angle equal to the second given number 
	 * 
	 * @param velocity
	 * 			the total value the new velocity must have (the length of the vector)
	 * @param direction
	 * 			determs in which direction of this new velocity is
	 * @return a new velocity with the given value and with the angle equal to the second given number 
	 * 			| result == Velocity.createVelocity(velocity*Math.cos(direction), velocity*Math.sin(direction))
	 */
	public static Velocity createVelocityInRandomDirection(double velocity, double direction) {
		return Velocity.createVelocity(velocity*Math.cos(direction), velocity*Math.sin(direction));

	}
	
	/**
	 * Returns the speed limit in km/s.
	 */
	@Basic
	@Immutable
	public double getSpeedLimit() {
		return speedLimit;
	}
	
	/**
	 * Makes a new velocity with given x-coordinate and y-coordinate
	 * 
	 * @param xCoordinate
	 * 
	 * @param yCoordinate
	 * 
	 * @return a new velocity with given x-coordinate and y-coordinate
	 * 			| result == Velocity.createVelocity(xCoordinate, yCoordinate)
	 */
	@Override
	public Velocity changeVector(double xCoordinate, double yCoordinate) {
		return Velocity.createVelocity(xCoordinate, yCoordinate);
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
	 *         |Util.fuzzyLessThanOrEqualTo(VectorInSpace.norm(this),
	 *         getSpeedLimit())
	 */
	public boolean hasValidVelocity() {
		return Util.fuzzyLessThanOrEqualTo(VectorInSpace.norm(this),
				getSpeedLimit());
	}
	
	/**
	 * returns the subtract of the second given velocity from the first given velocity
	 * 
	 * @param vector1
	 * 			the velocity from which you want to subtract the other velocity
	 * @param vector2
	 * 			the velocity that you subtract from the first
	 * @return the subtract of the second given velocity from the first given velocity
	 * 			| result == Velocity.createVelocity(vector1.getXCoordinate() - vector2.getXCoordinate(),
	 * 			|     		 vector1.getYCoordinate() - vector2.getYCoordinate())
	 * 			
	 */
	public static Velocity vectorChange(Velocity vector1,
			Velocity vector2) {
		return Velocity.createVelocity(vector1.getXCoordinate()
				- vector2.getXCoordinate(), vector1.getYCoordinate()
				- vector2.getYCoordinate());
	}
	
}
