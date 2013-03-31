package asteroids.model;

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
	
	/**
	 * variable registering the amount of thrust this ship can make
	 */
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
	
	/**
	 * Checks if the thruster is active or not
	 * 
	 * @return true if the thruster is active
	 * 			| result == thrustEnabled
	 */
	public boolean checkIfThrustIsEnabled(){
		return thrustEnabled;
	}
	
	/**
	 * 
	 * @param active
	 * 			the new state of the thruster of this ship
	 * @post sets the thruster on the given state
	 * 			| new.thrustEnabled = active
	 */
	public void setThrusterActive(boolean active){
		thrustEnabled = active;
	}
			
	/**
	 * if the time is a valid number and the thrust is enabled, this ship accelerates within the given time in the current direction.
	 * 
	 * @param dt
	 *        the amount of time you want to give to accelerate the ship
	 * @effect this ship accelerates within the given time in the current direction if the time is valid and the thrust is enabled.
	 * 			| if(isValidTime(dt) && checkIfThrustIsEnabled())
	 * 			| then newVelocityX = this.getVelocityX() +  (Math.cos(getAngle()) * Math.pow(dt, 2)*THRUST)/getMass();
	 *		  	| newVelocityY = this.getVelocityY() +  (Math.sin(getAngle()) * Math.pow(dt, 2)*THRUST)/getMass();
	 *        	| setVelocity(newVelocityX, newVelocityY)
	 */
	public void thrust(double dt) {
		if(checkIfThrustIsEnabled()){
			if (isValidTime(dt)) {
				double newVelocityX = this.getVelocityX() +  (Math.cos(this.getAngle()) * Math.pow(dt, 2)*THRUST)/(this.getMass());
				double newVelocityY = this.getVelocityY() +  (Math.sin(this.getAngle()) * Math.pow(dt, 2)*THRUST)/(this.getMass());
				setVelocity(newVelocityX, newVelocityY);
			}
		}
	}

	/**
	 * Fires a Bullet of this ship
	 * 
	 * @return Bullet 
	 * 			the bullet that you fire
	 */
	public Bullet firebullet(){
		Bullet bullet = new Bullet(this);
		this.getWorld().AddObjectInSpace(bullet);
		return bullet;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isShip(Object object){
		return Ship.class.isAssignableFrom(object.getClass());
	}

	
}
