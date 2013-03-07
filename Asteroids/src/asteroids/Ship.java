package asteroids;

import java.awt.Dimension;
import java.awt.Toolkit;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create Spaceships for playing asteroids. 
 * A ship has 2D coordinates in km for its position, a velocity in km/s,
 * a radius and a direction.
 * The ships are able to move, turn and accelerate.
 * This class can also predict time and place of collision between 2 ships.
 * 
 * @invar X coordinate, Y coordinate, VelocityX, Velocity Y doubles must be valid numbers.
 * 		| isValidDouble(getX()) && isValidDouble(getY())
 * 		| isValidDouble(getVelocityX()) && isValidDouble(getVelocityY()) 
 * @invar Angle must be a valid angle
 * 		| isValidAngle(getAngle())
 * @invar Radius must be a valid radius
 * 		| isValidRadius(getRadius())
 * @invar The velocity must be a valid velocity, less than the speed limit.
 * 		| isValidVelocity(getVelocityX(),getVelocityY())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
public class Ship implements IShip {
	/**
	 * Initialize a new ship with given x-coordinate, given y-coordinate, given
	 * velocity in the x direction, given velocity in the y direction, given
	 * radius and given direction
	 * 
	 * @param x
	 *            The x-coordinate for this new ship.
	 * @param y
	 *            The y-coordinate for this new ship.
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
	 *			| (new this).radius = radius
	 * @effect the given x-coordinate is set as the x-coordinate of this new ship
	 *         |setX(x)
	 * @effect the given y-coordinate is set as the y-coordinate of this new ship
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
	 *             |!isValidDouble(x) || !isValidDouble(y) ||
	 *             !isValidDouble(radius)
	 * 
	 */
	public Ship(double x, double y, double velocityX, double velocityY,
			double radius, double angle) throws IllegalArgumentException,
			NullPointerException {
		setX(x);
		setY(y);
		setVelocity(velocityX, velocityY);
		if (isValidDouble(radius)) {
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
	 * sets the y coordinate of this ship to the given position,
	 * adjusted to your screen's height. 
	 * 
	 * @param y
	 *     	  The y-coordinate of this ship in km.
	 * 
	 * @post if the given number lies between 0 and your screen's height 
	 * 		 then the new position y of this ship is equal to the given number.
	 *       | if (0< y < screenSize.getHeight())
	 *       | then new.getY() == y
	 * @post if the given number is smaller than 0
	 * 		  then the new position y of this ship is equal to your screen's height.
	 * 		 |if ( y < 0)
	 * 		 |then new.getY() == screenSize.getHeight()
	 * @post if the given number height than your screen's height
	 * 		  then the new position y of this ship is 0.
	 * 		 |if (screenSize.getHeight() < y)
	 * 		 |then new.getY() == 0
	 * @throws NullPointerException
	 *         The given position is not a valid number.
	 *         |!isValidDouble(y)
	 */
	public void setY(double y) throws NullPointerException {
		double height = screenSize.getHeight();
		if (!isValidDouble(y)) {
			throw new NullPointerException("Non-existing y-coordinate");
		} else if (y > height) {
			this.y = 0;
		} else if (y < 0) {
			this.y = height;
		} else {
			this.y = y;
		}
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
	 * sets the x coordinate of this ship to the given position,
	 * adjusted to your screen's width. 
	 * 
	 * @param x
	 *       	The x-coordinate of this ship in km.
	 * 
	 * @post if the given number lies between 0 and your screen's width 
	 * 		 then the new position x of this ship is equal to the given number.
	 *       | if (0< x < screenSize.getWidth())
	 *       | then new.getX() == x
	 * @post if the given number is smaller than 0
	 * 		  then the new position x of this ship is equal to your screen's width.
	 * 		 |if ( x < 0)
	 * 		 |then new.getX() == screenSize.getWidth()
	 * @post if the given number height than your screen's width
	 * 		  then the new position x of this ship is 0.
	 * 		 |if (screenSize.getWidth() < x)
	 * 		 |then new.getX() == 0
	 * @throws NullPointerException
	 *         The given position is not a valid number.
	 *         |!isValidDouble(x)
	 */
	public void setX(double x) throws NullPointerException {
		double width = screenSize.getWidth();
		if (!isValidDouble(x)) {
			throw new NullPointerException("Non-existing x-coordinat");
		} else if (x > width) {
			this.x = 0;
		} else if (x < 0) {
			this.x = width;
		} else {
			this.x = x;
		}
	}

	/**
	 * Variable with the x coordinate of this ship in km.
	 */
	private double x;
	
	/**
	 * Variable with the size of your screen.
	 */
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Check if this number is a valid number.
	 * 
	 * @param number
	 *       	 The number in double format to verify.
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
	public double getVelocity(double velocityX, double velocityY) {
		return Math.hypot(velocityX, velocityY);
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
	 *       | &&   new.getVelocityY()=this.getVelocityY()
	 * @post If the given velocity is greater than the speed limit, the new
	 *       velocity of this ship is equal the speed limit, the velocity in x
	 *       direction is the velocity times the cosine of the direction, the
	 *       velocity in y direction is the velocity times the sine of the
	 *       direction. 
	 *       |if!(isValidVelocity(velocityX, velocityY))
	 *       | then new.getVelocityX()=this.getSpeedLimit()*Math.cos(this.getDirection())
	 *       | && new.getVelocityY()=this.getSpeedLimit()*Math.sin(this.getDirection())
	 * @effect if one or both of the velocities is not a number then that velocity
	 *       	(those velocities) are set on zero
	 *       	 |if (!isValidDouble(velocityY))
	 *      	 |  then setVelocity(velocityX, 0.0); 
	 *      	 |if (!isValidDouble(velocityX)) 
	 *      	 | then setVelocity(0.0,velocityY);
	 *      	 |if (!isValidDouble(velocityX) && !isValidDouble(velocityY))
	 *     	 	 |then setVelocity(0.0, 0.0)
	 */		 	
	public void setVelocity(double velocityX, double velocityY) {
		if (isValidDouble(velocityX) && isValidDouble(velocityY)) {
			if (isValidVelocity(velocityX, velocityY)) {
				this.velocityX = velocityX;
				this.velocityY = velocityY;
			} 
			else {
				double direction = this.getDirection(velocityX, velocityY);
				this.velocityX = this.getSpeedLimit() * Math.cos(direction);
				this.velocityY = this.getSpeedLimit() * Math.sin(direction);
			}
		} 
		else {
			if (!isValidDouble(velocityY)) {
				setVelocity(velocityX, 0.0);
			}
			else if (!isValidDouble(velocityX)) {
				setVelocity(0.0,velocityY);
			}
			else {
				setVelocity(0.0, 0.0);
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
	 * @return the angle of the velocity
	 * 		 	| result == Math.atan(velocityY/velocityX)
	 */
	private double getDirection(double velocityX, double velocityY) {
		return Math.atan(velocityY / velocityX);
	}

	/**
	 * variable registering the angle of the ship
	 */
	private double angle;

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
	 * Constant that reflects the lowest possible radius of a ship
	 */
	public final static double MIN_RADIUS = 10;

	/**
	 * variable registering the radius of the ship
	 */
	private final double radius;

	/**
	 * return the radius of the ship
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
	 * @return true if radius is larger then the lower bound radius
	 *         | result == (radius > MIN_RADIUS) && (isValidDouble(radius))
	 */
	public boolean isValidRadius(double radius) {
		return (radius > MIN_RADIUS && isValidDouble(radius));
	}

	/**
	 * Let the ship move within a given time period
	 * 
	 * @effect the ship has moved in the given time period
	 *         |setY(this.velocityY*dt+getY()) && setY(this.velocityX*dt+getX())
	 * @param dt
	 *         the time in which the ship needs to move
	 * @throws NullPointerException
	 *             The given time is not a number
	 *         |!isValidDouble(dt)
	 * @throws IllegalArgumentException
	 *             The given time is not a valid time
	 *         |!isValidTime(dt)
	 */
	public void move(double dt) throws NullPointerException,
			IllegalArgumentException {
		if (isValidDouble(dt)) {
			if (isValidTime(dt)) {
				setX(this.velocityX * dt + getX());
				setY(this.velocityY * dt + getY());

			} else {
				throw new IllegalArgumentException("the time must be more then zero");
			}
		} else {
			throw new NullPointerException("the time you have given is not a number");
		}

	}

	/**
	 * Check whether the time is more or equal to zero
	 * 
	 * @param dt
	 *          the time to check
	 * @return true if time is more or equal to zero
	 *         |result == dt >= 0
	 */
	public boolean isValidTime(double dt) {
		return dt >= 0;
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
		double newVelocityX = this.velocityX + amount * Math.cos(getAngle());
		double newVelocityY = this.velocityY + amount * Math.sin(getAngle());
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

	/**
	 * Calculates the distance between this ship and the given ship.
	 * 
	 * @param ship
	 * 			The ship between which you calculate the distance
	 * @return Returns the distance between this ship and the given ship.
	 * 			|result == Math.hypot((this.getX() - ship.getX()), (this.getY() - ship.getY()))- this.getRadius() - ship.getRadius()
	 * @throws IllegalArgumentException
	 *             the given ship is this ship
	 *             |this == ship
	 * @throws NullPointerException
	 *             the given ship is null
	 *             |ship == null
	 */
	public double getDistanceBetween(IShip ship)
			throws IllegalArgumentException, NullPointerException {
		if (this == ship) {
			throw new IllegalArgumentException("Ship cannot be compared to itself");
		} else if (ship == null) {
			throw new NullPointerException("the given ship does not exist");
		} else {
			return Math.hypot((this.getX() - ship.getX()), (this.getY() - ship.getY()))
					- this.getRadius() - ship.getRadius();
		}
	}

	/**
	 * returns true if this ship overlaps with the given ship
	 * 
	 * @param ship
	 * 			The ship which to check if it overlaps with this ship
	 * @return True if this ship is the given ship or if the distance between the 2 ships equals 0.
	 * 			|distance = this.getDistanceBetween(ship)
	 * 		 	|result == ((this==ship) || (Util.fuzzyLessThanOrEqualTo(distance, 0.0))
	 */
	public boolean overlap(IShip ship) {
		if (this == ship) {
			return true;
		} else {
			double distance = this.getDistanceBetween(ship);
			if (Util.fuzzyLessThanOrEqualTo(distance, 0.0)) {
				return true;
			} else {
				return false;
			}

		}
	}

	/**
	 * returns the time in which this ship collides with the given ship, if they
	 * never collide it will give infinite back
	 * 
	 * @param ship
	 *            the ship from which we need to know when it will collide with
	 *            this ship
	 * @return the time in which this ship collides with the given ship, if they
	 *         never collide it will give infinite back
	 *        |  result == 
	 * @throws NullPointerException
	 *             the given ship is null
	 *             |ship == null
	 */
	public double getTimeToCollision(IShip ship) throws NullPointerException{
		if (ship == null){
			throw new NullPointerException();
		}
		double x1 = this.getX();
		double y1 = this.getY();
		double vx1 = this.getVelocityX();
		double vy1 = this.getVelocityY();

		double x2 = ship.getX();
		double y2 = ship.getY();
		double vx2 = ship.getVelocityX();
		double vy2 = ship.getVelocityY();

		double dvMultiDr = (vx1 - vx2) * (x1 - x2) + (vy1 - vy2) * (y1 - y2);
		double dvMultiDv = Math.pow(vx1 - vx2, 2) + Math.pow(vy1 - vy2, 2);
		double drMultiDr = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);

		if (Util.fuzzyEquals(dvMultiDr, 0.0) || Double.compare(dvMultiDr, 0.0) > 0) {
			return Double.POSITIVE_INFINITY;
		} else {
					
			double dVariable = Math.pow(dvMultiDr, 2) - dvMultiDv * (drMultiDr - Math.pow(this.getRadius()+ship.getRadius(), 2));
			
			if (Util.fuzzyLessThanOrEqualTo(dVariable, 0)) {
				return Double.POSITIVE_INFINITY;
			} else {
				double time = -((dvMultiDr + Math.sqrt(dVariable)) / (dvMultiDv));
				return time;
			}
		}

	}
	
	
	/**
	 * returns the position of the ship at the moment of impact with the given ship
	 * 
	 * @param ship
	 * 			the ship from which we need to know at which place this ship will collide with it
	 * @return the position of the ship at the moment of impact with the given ship
	 * @throws NullPointerException
	 *             the given ship is null
	 *             |ship == null         
	 */
	public double[] getCollisionPosition(IShip ship) throws NullPointerException {
		if(ship == null){
			throw new NullPointerException();
		}

		double time = getTimeToCollision(ship);

		if (!Double.isInfinite(time)) {
			double x1Collision = this.getX() + time * this.getVelocityX();
			double y1Collision = this.getY() + time * this.getVelocityY();
			double[] collisionPoint = new double[2];
			collisionPoint[0] = x1Collision;
			collisionPoint[1] = y1Collision;
			return collisionPoint;
		} else {
			return null;
		}
	
	}

	
}
