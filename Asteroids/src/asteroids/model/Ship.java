package asteroids.model;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create Spaceships for playing asteroids. 
 * A ship has 2D coordinates  (where the x-axis is horizontal and the y-axis is vertical)
 * in km for its position, a velocity in km/s, a radius and a direction.
 * The ships are able to move, turn and accelerate.
 * This class can also predict time and place of collision between 2 objects or the wand of the world the object is in.
 * Ship is a subclass of ObjectInSpace
 * 
 * @invar Angle must be a valid angle
 * 		| isValidAngle(getAngle())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Ship  extends ObjectInSpace{
	
	
	
	/**
	 * Initialize a new ship with given x-coordinate, given y-coordinate,
	 *  given velocity in the x direction, given velocity in the y direction,
	 *  given radius, the given mass and given direction
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
	 * @param mass
	 *            The mass for this new ship.
	 * 
	 * @effect the given x-coordinate, the given y-coordinate, the given velocity in the x direction, 
	 * 			the given velocity in the y direction, the given mass and the given radius 
	 * 			are set as the x-coordinate, the y-coordinate, the velocity in the x direction,
	 *     		the velocity in the y direction, the mass and the radius of this new ship
	 *         | super(x,y,velocityX,velocityY,radius, mass)
	 *         
	 * @effect the given angle is set as the angle of this new ship
	 *         |setAngle(angle)
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
	private final double THRUST = 1.1*Math.pow(10, 19);
	
	/**
	 * variable registering the listBullet of this ship
	 *
	 */
	private ArrayList<Bullet> listBullet = new ArrayList<Bullet>();	
	
	/**
	 * terminates this bullet
	 * 
	 * @effect terminates this bullet
	 * 			| this.terminate()
	 */
	public void die(){
		this.terminate();
	}
	
	/**
	 * Return the angle of the ship 
	 * the angle expresses in which angle, with the
	 * x-as, the acceleration of the ship is
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
 	 * return the listBullet of this ship
 	 *
 	 */
	@Basic
	public ArrayList<Bullet> getListBullet() {
		return listBullet;
	}
	
	/**
 	 * return the listBullet of this ship
 	 *
 	 */
	@Basic
	public void addBulletList(Bullet bullet) {
		getListBullet().add(bullet);
	}
	
	/**
 	 * return the listBullet of this ship
 	 *
 	 */
	@Basic
	public void removeFromBulletList(Bullet bullet) {
		getListBullet().remove(bullet);
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
	 *  sets the thruster on the given state 
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
	 *        
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
	 * @return the bullet that you fire
	 * 			| result == new Bullet(this)
	 * @effect set the Bullet in the same world as the ship
	 * 			| this.getWorld().addObjectInSpace(new Bullet(this))
	 */
	public Bullet firebullet(){
		if( getListBullet().size() < 3){
			Bullet bullet = new Bullet(this);
			try{
				this.getWorld().addObjectInSpace(bullet);
			} catch (IllegalArgumentException exc) {
				return null;
			}
			getListBullet().add(bullet);
			return bullet;
		} else{
			return null;
		}
	}

	/**
	 * Check whether class of the given object is Ship or a subclass of Ship
	 * 
	 * @param object
	 * 			the object you want to check	 
	 *
	 *@return true if the class of the given object is Ship or a subclass of Ship
	 *			| result == Ship.class.isAssignableFrom(object.getClass())
	 * 
	 */
	public static boolean isShip(Object object){
		return Ship.class.isAssignableFrom(object.getClass());
	}

	
}
