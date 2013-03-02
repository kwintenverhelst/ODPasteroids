package asteroids;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * 
 * @invar The denominator of each rational number must be a legal denominator
 *        for a rational number. | isValidDenominator(getDenominator())
 * 
 * @version 1.0
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Ship implements IShip{
	/**
	 * Initialize a new ship with given x-coordinat, given y-coordinat, given
	 * velocity in the x direction, given velocity in the y direction, given
	 * radius and given direction
	 * 
	 * @param x
	 *            The x-coordinat for this new ship.
	 * @param y
	 *            The y-coordinat for this new ship.
	 * @param velocityX
	 *            The velocity in the x direction for this new ship.
	 * @param velocityY
	 *            The velocity in the y direction for this new ship.
	 * @param radius
	 *            The radius for this new ship.
	 * @param angle
	 *            The direction for this new ship.
	 * 
	 * @post the given radius is set as the radius of this new ship | (new
	 *       this).radius = radius
	 * @effect the given x-coordinat is set as the x-coordinat of this new ship
	 *         |setX(x)
	 * @effect the given y-coordinat is set as the y-coordinat of this new ship
	 *         |setY(y)
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
	 *             |!isValidDouble(x) || !isValidDouble(y) || !isValidDouble(radius)
	 * 
	 */
	public Ship(double x, double y, double velocityX, double velocityY,
			double radius, double angle) throws IllegalArgumentException,
			NullPointerException {
		setX(x);
		setY(y);
		setVelocity(velocityX, velocityY);
		if(isValidDouble(radius)) {
			if (!isValidRadius(radius)) {
				throw new IllegalArgumentException("Non-existing radius");
			} else {
				this.radius = radius;
			}
		} else {
			throw new NullPointerException("given radius is not a number");
		}
		setAngle(angle);
	}

	/**
	 * Returns the y coordinate of the this ship's position expressed in km
	 */
	@Basic
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 *            The y-coordinate of this ship in km.
	 * 
	 * @post the new position y of this ship is equal to the given number.
	 *       |new.getY() == y
	 * @throws NullPointerException
	 *             The given position is not a valid number. |!isValidDouble(y)
	 */
	public void setY(double y) throws NullPointerException {
		if (!isValidDouble(y)) {
			throw new NullPointerException("Non-existing y-coordinat");
		}
		this.y = y;
	}

	/**
	 * Variable with the y coordinate of this ship in km.
	 */
	private double y;

	/**
	 * Returns the x coordinate of the this ship's position expressed in km
	 */
	@Basic
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            The x-coordinate of this ship in km.
	 * @post the new position x of this ship is equal to the given number in km
	 *       |new.getX() == x
	 * @throws NullPointerException
	 *             The given position is not a valid number 
	 *             |!isValidDouble(x)
	 */
	public void setX(double x) throws NullPointerException {
		if (!isValidDouble(x)) {
			throw new NullPointerException("Non-existing x-coordinat");
		}
		this.x = x;
	}

	/**
	 * Variable with the x coordinate of this ship in km.
	 */
	private double x;

	/**
	 * Check if this number is a valid number.
	 * 
	 * @param number
	 *            The number in double format to verify.
	 * @return True if double is a valid number otherwise it is false.
	 *         |result!=Double.isNaN(number)
	 */
	public boolean isValidDouble(double number) {

		return !Double.isNaN(number);
	}

	/**
	 * Returns the velocity of this ship in the x direction in km/s.
	 */
	@Basic
	public double getVelocityX() {
		return this.velocityX;
	}

	/**
	 * The velocity of this ship in the x direction in km/s
	 */
	private double velocityX;

	/**
	 * Returns the velocity of this ship in the y direction in km/s.
	 */
	@Basic
	public double getVelocityY() {
		return this.velocityY;
	}

	/**
	 * The velocity of this ship in the y direction in km/s
	 */
	private double velocityY;

	/**
	 * Returns the speed limit of this ship in km/s.
	 */
	@Basic
	@Immutable
	public double getSpeedLimit() {
		return speedLimit;
	}

	/**
	 * Variable for the speed limit of this ship in km/s.
	 */
	private final double speedLimit = 300000;

	/**
	 * Checks if the velocity is a valid number and if it is less or equal to
	 * the speedlimit.
	 * 
	 * @param velocityX
	 *            velocity in the x direction in km/s
	 * @param velocityY
	 *            velocity in the y direction in km/s
	 * @effect Returns true if the velocity is a valid number and if it is less
	 *         or equal to the speedlimit.
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
	 * 			|result == Math.sqrt((Math.pow(velocityX, 2) + Math.pow(velocityY,2))
	 */
	public double getVelocity(double velocityX, double velocityY) {
		return Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
	}

	/**
	 * Changes the velocity of this ship in the given x and y direction.
	 * 
	 * @param velocityX
	 *            velocity in the x direction in km/s
	 * @param velocityY
	 *            velocity in the y direction in km/s
	 * @post If the given velocity is less than or equal to the speed limit, the
	 *       new velocity of this ship is equal to given velocity.
	 *       |if(isValidVelocity(velocityX, velocityY)) 
	 *       | then new.getVelocityX()=this.getVelocityX() 
	 *       | && new.getVelocityY()=this.getVelocityY()
	 * @post If the given velocity is greater than the speed limit, the new
	 *       velocity of this ship is equal the speed limit, the velocity in x
	 *       direction is the velocity times the cosine of the direction, the
	 *       velocity in y direction is the velocity times the sine of the
	 *       direction. 
	 *       |if!(isValidVelocity(velocityX, velocityY)) 
	 *       | then new.getVelocityX()=this.getSpeedLimit()*Math.cos(this.getDirection()) 
	 *       | && new.getVelocityY()=this.getSpeedLimit()*Math.sin(this.getDirection())
	 * @post if one or both of the velocities is not a number then that velocity 
	 * 			(those velocities) are set on zero
	 * 			| if (!isValidDouble(velocityY)) 
					then this.velocityY = 0;
				| || if (!isValidDouble(velocityX)) 
					then this.velocityX = 0
					
				
	 */
	public void setVelocity(double velocityX, double velocityY) {
		if (isValidDouble(velocityX) && isValidDouble(velocityY)) {
			if (isValidVelocity(velocityX, velocityY)) {
				this.velocityX = velocityX;
				this.velocityY = velocityY;
			} else {
				double direction = this.getDirection(velocityX, velocityY);
				this.velocityX = this.getSpeedLimit() * Math.cos(direction);
				this.velocityY = this.getSpeedLimit() * Math.sin(direction);
			}
		} else {
			if (isValidDouble(velocityX) && !isValidDouble(velocityY)) {
				this.velocityX = velocityX;
				this.velocityY = 0;
			} else if (!isValidDouble(velocityX) && isValidDouble(velocityY)) {
				this.velocityX = 0;
				this.velocityY = velocityY;
			} else {
				this.velocityX = 0;
				this.velocityY = 0;
			}
		}
	}

	/**
	 * returns the angle of the velocity, which has the components velocityX and
	 * velocityY
	 * 
	 * @param velocityX
	 *            The x component of the velocity
	 * @param velocityY
	 *            The y component of the velocity
	 * @return the angle of the velocity | result ==
	 *         Math.atan(velocityY/velocityX)
	 */
	private double getDirection(double velocityX, double velocityY) {
		return Math.atan(velocityY / velocityX);
	}

	/**
	 * variable registering the angle of the ship
	 */
	private double angle;

	/**
	 * Return the angle of the ship the angle express in which angle, with the
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
	 * @return true if angle | result == angle >= 0 && angle < 2*Math.PI
	 */
	public boolean isValidAngle(double angle) {
		if (angle >= 0 && angle < 2 * Math.PI && isValidDouble(angle)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Set the angle of the ship with the given angle
	 * 
	 * @param angle
	 *            the new angle of the ship
	 * @pre the given angle of the ship must be a valid angle |
	 *      isValidAngle(angle)
	 * @post the new angle of the ship is equal to the given angle
	 *       |new.getAngle() == angle
	 */
	public void setAngle(double angle) {
		assert isValidAngle(angle);
		this.angle = angle;
	}

	/**
	 * Constant that reflects the lowest possible radius of a ship
	 * 
	 * @return the lowest possible radius a ship can have | result = 10
	 */
	public final static double MIN_RADIUS = 10;

	/**
	 * variable registering the radius of the ship
	 */
	private final double radius;

	/**
	 * return radius of the ship
	 * 
	 */
	@Basic
	@Immutable
	public double getRadius() {
		return radius;
	}

	/**
	 * Check whether the radius of the ship is valid
	 * 
	 * @param radius
	 *            The radius to check
	 * @return true if radius is larger then the lower bound radius | result ==
	 *         radius > MIN_RADIUS && isValidDouble(radius)
	 */
	public boolean isValidRadius(double radius) {
		if (radius > MIN_RADIUS && isValidDouble(radius)) {
			return true;
		} else {
			return false;
		}

	}

}
