package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class to create bullets in space for playing asteroids. 
 * A bullet has 2D coordinates (where the x-axis is horizontal and the y-axis is vertical)
 * in km for its position, a velocity in km/s, a mass, a world and a radius.
 * The bullets are able to move.
 * This class can also predict time and place of collision between 2 objects or the wand of the world the object is in.
 * bullets can hit 2 times a wand
 * bullets cannot be intitialized with given variable but need a ship to get their variables
 * Bullet is a subclass of ObjectInSpace
 * 
 * @invar The radius must be a valid radius
 * 		| isValidRadius(this.getRadius())
 * @invar The mass must be a valid mass
 * 		| isValidMass(this.getMass())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Bullet extends ObjectInSpace {

	/**
	 * Initialize a new Bullet from a given ship which determent the x-coordinate, the y-coordinate, the
	 * velocity in the x direction, the velocity in the y direction, the direction and the mass of the bullet
	 * 
	 * @param ship
	 * 			the ship from which the bullet comes from
	 * 
	 * @effect the radius is set as 3
	 * 			| super(3)
	 * @effect the position is set on the border of the ship on the angle of the ship
	 * 			|setPosition(ship.getX() + (radius + ship.getRadius()) * Math.cos(ship.getAngle()),
	 *			|				ship.getY() + (radius + ship.getRadius()) * Math.sin(ship.getAngle()))
	 * @effect the velocity is set as 250 in the direction of the angle of the ship
	 * 			| setVelocity(250 * Math.cos(ship.getAngle()),	250 * Math.sin(ship.getAngle()))
	 * @effect the mass is calculated on the basis of the radius of the bullet which is 3 at the moment
	 * 			| setMass(calculateMass(radius))
	 */
	public Bullet(Ship ship) {
		super(3);
		double radius = 3;
		this.ship = ship;
		setPosition(
				ship.getX() + (radius + ship.getRadius())
						* Math.cos(ship.getAngle()),
				ship.getY() + (radius + ship.getRadius())
						* Math.sin(ship.getAngle()));
		setVelocity(250 * Math.cos(ship.getAngle()),
				250 * Math.sin(ship.getAngle()));
		setMass(calculateMass(radius));
	}

	/**
	 * Variable for the density of a bullet in kg/km^3.
	 */
	private final static double DENSITY = 2.65 * Math.pow(10, 12);

	/**
	 * variable registering the ship of this bullet
	 */
	private final Ship ship;

	/**
	 * Variable for how many times a bullet has collided with the wand of the world.
	 */
	private int countCollision;


	/**
	 * return the ship of this bullet 
	 */
	@Basic
	public Ship getShip() {
		return ship;
	}

	/**
	 * Check whether ship of this bullet is valid
	 * 
	 * @param ship
	 *            The ship to check
	 * @return true if ship
	 * 			| result == ship != null
	 */
	public boolean isValidShip(Ship ship) {
		return Ship.isShip(ship);

	}
	
	/**
	 * Check whether the given object is the ship of this bullet
	 * 
	 * @param object
	 * 			the object to check
	 * @return true if the given object is the ship of this bullet
	 * 			| result == (Ship.isShip(object) && this.getShip() ==  object)
	 */
	public boolean isShipFromBullet(ObjectInSpace object){
		if(Ship.isShip(object)){
			return (this.getShip() ==  object);
		} else {
			return false;
		}
	}
	
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
	 * Returns the density in km/km³.
	 */
	@Basic
	@Immutable
	public double getDensity() {
		return DENSITY;
	}

	/**
	 * Calculate the mass of a bullet on the basis of the radius
	 *  
	 * @param radius
	 * 			the radius of the bullet you want to calculate the mass from
	 * 
	 * @return the mass of this bullet on the basis of the radius
	 * 			| result == 4 * Math.PI * Math.pow(radius, 3) * getDensity() / 3
	 */
	public double calculateMass(double radius) {

		return 4 * Math.PI * Math.pow(radius, 3) * getDensity() / 3;
	}

	/**
	 * returns the amount of time the bullet has collide with the wand
	 */
	@Basic
	public int getCountCollision() {
		return countCollision;
	}

	/**
	 * add a collision to the amount of time the bullet has collide with the wand
	 * 
	 * @post he amount of time the bullet has collide with the wand is plus one
	 * 			| getCountCollision() + 1
	 */
	public void addCountCollision() {
		countCollision = getCountCollision() + 1;
	}

	/**
	 * let this bullet collide with the wand of the world
	 * 
	 * @effect if the bullet collides with the wand for the second time it will be terminated
	 * 			| if (getCountCollision() > 0)
	 * 			| then this.die()
	 * @effect if this is the first time the bullet collides with the wand and the wand is horizontal then the velocity in the vertical direction will be reversed
	 * 			| if (getCountCollision() == 0 && wand == 1)
	 * 			| then this.setVelocity(this.getVelocityX(), -(this.getVelocityY()))
	 * @effect if this is the first time the bullet collides with the wand and the wand is vertical then the velocity in the horizontal direction will be reversed
	 * 			| if (getCountCollision() == 0 && wand == 2)
	 * 			| then this.setVelocity(-(this.getVelocityX()), this.getVelocityY())
	 * @effect if this is the first time the bullet collides with the wand and it collides in a corner (it collides simultaneously with the horizontal and vertical wand) 
	 * 			then the velocity in the horizontal and vertical direction will be reversed
	 * 			| if (getCountCollision() == 0 && wand == 3)
	 * 			| then this.setVelocity(-(this.getVelocityX()), -(this.getVelocityY()))
	 */
	@Override
	public void collideWithWand() {
		int wand = collisionWithWhichWand();
		if (getCountCollision() == 0) {
			this.addCountCollision();
			if (wand == 1) {
				this.setVelocity(this.getVelocityX(), -(this.getVelocityY()));
			} else if (wand == 2) {
				this.setVelocity(-(this.getVelocityX()), this.getVelocityY());
			} else if (wand == 3) {
				this.setVelocity(-(this.getVelocityX()), -(this.getVelocityY()));
			}
		} else {
			this.die();
		}
	}
	
	/**
	 * Check whether class of the given object is Bullet or a subclass of Bullet
	 * 
	 * @param object
	 * 			the object you want to check	 
	 *  
	 * @return true if the class of the given object is Bullet or a subclass of Bullet
	 * 			| result == Bullet.class.isAssignableFrom(object.getClass()) 
	 */
	public static boolean isBullet(Object object){
		return Bullet.class.isAssignableFrom(object.getClass());
	}
}
