package asteroids.model;

import java.util.Random;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create velocity in a 2D setting and with a velocity in the x
 * direction, a velocity in the y direction and a speedlimit in km/s
 * 
 * @invar VelocityX, Velocity Y doubles must be valid numbers. |
 *        isValidDouble(getVelocityX()) && isValidDouble(getVelocityY())
 * @invar The velocity must be a valid velocity, less than the speed limit. |
 *        isValidVelocity(getVelocityX(),getVelocityY())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Velocity extends VectorInSpace {

	/**
	 * Variable for the speed limit in km/s.
	 */
	private final double speedLimit = 300000;

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
	 * @effect the x- and y-velocity are the given the x- and y-velocity
	 *         |setVelocity(velocityX, velocityY)
	 */
	private Velocity(double velocityX, double velocityY) {

		super(velocityX, velocityY);

	}

	public static Velocity createVelocity(double velocityX, double velocityY) {
		Velocity velocity = new Velocity(velocityX, velocityY);
		if (!(velocity.hasValidVelocity())) {
			double direction = VectorInSpace.getDirection(velocity);
			velocity = new Velocity(velocity.getSpeedLimit()*Math.cos(direction), velocity.getSpeedLimit()*Math.sin(direction));
		}
		return velocity;

	}

	public static Velocity createVelocityInRandomDirection(double velocity, Random random) {
		double randomDouble = random.nextDouble();
		if(Util.fuzzyEquals(randomDouble, 1) ){
			return  Velocity.createVelocityInRandomDirection(velocity, random);
		}
		return Velocity.createVelocity(velocity*Math.cos(2*Math.PI*randomDouble), velocity*Math.sin(2*Math.PI*randomDouble));

	}
	
	/**
	 * Returns the speed limit in km/s.
	 */
	@Basic
	@Immutable
	public double getSpeedLimit() {
		return speedLimit;
	}
	
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
	 *         |Util.fuzzyLessThanOrEqualTo(getVelocity(velocityX, velocityY),
	 *         getSpeedLimit())
	 */
	public boolean hasValidVelocity() {
		return Util.fuzzyLessThanOrEqualTo(VectorInSpace.norm(this),
				getSpeedLimit());
	}
	
}
