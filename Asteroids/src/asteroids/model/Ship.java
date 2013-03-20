package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create Spaceships for playing asteroids. 
 * A ship has 2D coordinates in km for its position, a velocity in km/s,
 * a radius and a direction.
 * The ships are able to move, turn and accelerate.
 * This class can also predict time and place of collision between 2 ships.
 * 
 * @invar Angle must be a valid angle
 * 		| isValidAngle(getAngle())
 * @invar Radius must be a valid radius
 * 		| isValidRadius(getRadius())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Ship  extends ObjectInSpace{
	
	public Ship() {
		super();
		setAngle(0);
	}
	
	/**
	 * Initialize a new ship with given x-coordinate, given y-coordinate, given
	 * velocity in the x direction, given velocity in the y direction, given
	 * radius and given direction
	 * 
	 * @param x
	 *            The x-coordinate of the position for this new ship.
	 * @param y
	 *            The y-coordinate of the position for this new ship.
	 * @param velocityX
	 *            The velocity in the x direction for this new ship.
	 * @param velocityY
	 *            The velocity in the y direction for this new ship.
	 * @param radius
	 *            The radius for this new ship.
	 * @param angle
	 *            The direction for this new ship.
	 * 
	 * @post the given radius is set as the radius of this new ship 
	 *			| (new this).radius == radius
	 * @effect the given x-coordinate and the given y-coordinate is set as 
	 * 			the x-coordinate and the y-coordinate of this new ship
	 *         |setPosition(x, y)
	 * @effect the given velocity in the x direction and the given velocity in
	 *         the y direction is set as the velocity in the x direction and the
	 *         velocity in the y direction of this new ship
	 *         |setVelocity(velocityX, velocityY)
	 * @effect the given angle is set as the angle of this new ship
	 *         |setAngle(angle)
	 * 
	 * @throws IllegalArgumentException
	 *             The given radius is not a valid radius for this new ship.
	 *             |!isValidRadius(radius)
	 * @throws NullPointerException
	 *             one of the given coordinates or the radius is not an number
	 *        		|!isValidDouble(radius)
	 * 
	 */
	public Ship(double x, double y, double velocityX, double velocityY,
			double radius, double mass, double angle) throws IllegalArgumentException,
			NullPointerException {
		super(x,y,velocityX,velocityY,radius, mass);
		setAngle(angle);
	}
	
	/**
	 * variable registering the angle of the ship
	 */
	private double angle;
	
	/**
	 * variable registering if the thrust of this ship is on
	 */
	private boolean thrustEnabled;
	
	private final double THRUST = 1.1*Math.pow(10, 18);
	
	/**
	 * Return the angle of the ship 
	 * the angle expresses in which angle, with the
	 * x-as, the acceleration of the ship is
	 * 
	 */
	@Basic
	public double getAngle() {
		return angle;
	}

	/**
	 * Check whether the angle of the ship is valid
	 * 
	 * @param angle
	 *            The angle to check
	 * @return true if angle lies between 0 and 2PI
	 * 			| result == angle >= 0 && angle < 2*Math.PI
	 */
	public boolean isValidAngle(double angle) {
		return (angle >= 0 && angle < 2 * Math.PI && isValidDouble(angle));
	}

	/**
	 * Set the angle of the ship to the given angle
	 * 
	 * @param angle
	 *            the new angle of the ship
	 * @pre the given angle of the ship must be a valid angle
	 * 		 |isValidAngle(angle)
	 * @post the new angle of the ship is equal to the given angle
	 *       |new.getAngle() == angle
	 */
	public void setAngle(double angle) {
		assert isValidAngle(angle);
		this.angle = angle;
	}


	/**
	 * if the amount is a valid number, this ship accelerates with the given amount in the current direction.
	 * 
	 * @param amount
	 *        the amount of acceleration you want to give to the ship
	 * @post  if the amount is not a valid double or if it's less than 0, 
	 * 		  then the amount is set to 0.
	 * 		  | if (!isValidDouble(amount) || !isValidThrust(amount))
			  |  then amount = 0;
	 * @effect this ship accelerates with the given amount in the current direction.
	 * 		  | newVelocityX = this.velocityX + amount * Math.cos(getAngle())
	 *		  | newVelocityY = this.velocityY + amount * Math.sin(getAngle())
	 *        | setVelocity(newVelocityX, newVelocityY)
	 */
	public void thrust(double amount) {
		if (!isValidDouble(amount) || !isValidThrust(amount)) {
			amount = 0;
		}
		double newVelocityX = this.getVelocityX() + amount * Math.cos(getAngle());
		double newVelocityY = this.getVelocityY() + amount * Math.sin(getAngle());
		setVelocity(newVelocityX, newVelocityY);
	}

	/**
	 * Check whether the amount of thrust is more or equal to zero
	 * 
	 * @param amount
	 *           the amount that has to be checked.
	 * @return true if the amount of thrust is more or equal to zero
	 * 		   | result == amount >= 0
	 */
	public boolean isValidThrust(double amount) {
		return amount >= 0;
	}
	
	/**
	 *Turns the ship by adding the given angle to the current angle.
	 * 
	 * @param angle
	 * 			the angle that has to be added.
	 * @effect sets the angle of this ship to the current angle plus the given angle.
	 * 		   | this.setAngle(this.getAngle()+angle)
	 */
	public void turn(double angle) {
		this.setAngle(this.getAngle()+angle);
	}

	public Bullet firebullet(){
		return new Bullet(this);
	}


	
}
