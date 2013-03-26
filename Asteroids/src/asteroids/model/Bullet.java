package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Bullet extends ObjectInSpace {


	public Bullet(Ship ship) {
		super(3);
		double radius = 3;
		this.ship = ship;
		setPosition(ship.getX()-(radius+ship.getRadius())*Math.cos(ship.getAngle()), ship.getY()-(radius+ship.getRadius())*Math.sin(ship.getAngle()));
		setVelocity(250*Math.cos(ship.getAngle()), 250*Math.sin(ship.getAngle()));
		setMass(calculateMass(radius));
	}

	private final static double DENSITY = 2.65 * Math.pow(10, 12);

	/**
	 * variable registering the ship of this bullet
	 * 
	 */
	private final Ship ship;

	/**
	 * Terminate this bullet.
	 */
	public  void terminate(){
		
	}
		
	/**
	 * return the ship of this bullet
	 * 
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
		if (ship != null) {
			return true;
		} else {
			return false;
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

	public double calculateMass(double radius) {

		return 4 * Math.PI * Math.pow(radius, 3) * getDensity() / 3;
	}
}
