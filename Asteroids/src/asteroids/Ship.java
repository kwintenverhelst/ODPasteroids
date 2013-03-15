package asteroids;

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
public class Ship implements IShip {
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
			double radius, double angle) throws IllegalArgumentException,
			NullPointerException {
		setPosition(x, y);
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
	 * variable registering the position of the ship
	 */
	
	private Position position = new Position();
	
	/**
	 * variable registering the velocity of the ship
	 */
	private Velocity velocity = new Velocity();
	
	/**
	 * Constant that reflects the lowest possible radius of a ship
	 */
	public final static double MIN_RADIUS = 10;

	/**
	 * variable registering the angle of the ship
	 */
	private double angle;
	
	/**
	 * variable registering the radius of the ship
	 */
	private final double radius;
	
	/**
	 * Returns the position of the this ship
	 */
	@Basic
	public Position getPosition(){
		return position;
	}
	
	/**
	 * Returns the x coordinate of the this ship's position expressed in km
	 * 
	 * @return the x coordinate of the this ship's position expressed in km
	 * 			|result == getPosition().getX()
	 */
	public double getX(){
		return getPosition().getX();
	}
	
	/**
	 * Returns the y coordinate of the this ship's position expressed in km
	 * 
	 * @return the y coordinate of the this ship's position expressed in km
	 * 			|result == getPosition().getY()
	 */
	public double getY(){
		return getPosition().getY();
	}			
	
	/**
	 *  set the position of this ship on the given x- and y-coordinates
	 * @param x
	 * 			The x-coordinate in km of the new position
	 * @param y
	 * 			The y-coordinate in km of the new position
	 * @effect the new position of the ship has the given x- and y-coordinates	
	 * 			|position.setPosition(x, y)
	 */
	public void setPosition(double x, double y){
		position.setX(x);
		position.setY(y);
	}
	
	/**
	 * Returns the velocity of this ship in km/s.
	 */
	@Basic
	public Velocity getVelocity(){
		return velocity;
	}
	
	/**
	 * Returns the velocity of this ship in the x direction in km/s.
	 * 
	 * @return the velocity of this ship in the x direction in km/s
	 * 			|result == getVelocity().getVelocityX()
	 */
	public double getVelocityX(){
		return getVelocity().getVelocityX();
	}
	
	/**
	 * Returns the velocity of this ship in the y direction in km/s.
	 * 
	 * @return the velocity of this ship in the y direction in km/s
	 * 			|result == getVelocity().getVelocityY()
	 */
	public double getVelocityY(){
		return getVelocity().getVelocityY();
	}
	
	/**
	 *  set the velocity of this ship on the given x- and y-velocities
	 * @param VelocityX
	 * 			The x-velocity in km of the new velocity
	 * @param VelocityY
	 * 			The y-velocity in km of the new velocity
	 * @effect the new velocity of the ship has the given x- and y-coordinates	
	 * 			|velocity.setVelocity(velocityX, velocityY)
	 * 
	 */
	public void setVelocity(double velocityX, double velocityY){
		velocity.setVelocity(velocityX, velocityY);
	}
	
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
	 *         |this.setPosition(this.getVelocityX()*dt + this.getX(), this.getVelocityY()*dt + this.getY())
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
				setPosition(this.getVelocityX() * dt + getX(), this.getVelocityY() * dt + getY());
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
	 *        |  with ship1==this position == (x1,y1) velocity == (vx1,vy1)
	 *        |   and ship2 position == (x2,y2) velocity == (vx2,vy2)
	 *        |   and DeltaR== (DeltaX,DeltaY), DeltaV== (DeltaVX,DeltaVY), DeltaR*DeltaR== (DeltaX)^2+(DeltaY)^2
	 *        |   and DeltaV*DeltaV== (DeltaVX)^2+(DeltaVY)^2 and DeltaV*DeltaR== (DeltaVX)*(DeltaX)+(DeltaVY)*(DeltaY)
	 *        |   and Sigma== this.getRadius() + ship2.getRadius()
	 *        |   and d== (DeltaV*DeltaR)^2 - (DeltaV*DeltaV)*(DeltaR*DeltaR -Sigma^2)
	 *        |   if(DeltaV*DeltaR >= 0) 
	 *        |		then result == Double.POSITIVE_INFINITY
	 *        |	  else if(d <= 0)
	 *        |		then result == Double.POSITIVE_INFINITY
	 *        |	  else
	 *        |		then result == -((DeltaV*DeltaR)+Math.sqrt(d))/(DeltaV*DeltaV)
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
	 * 			|if(Double.isInfinite(getTimeToCollision(ship)))
	 * 			|	then result == null
	 * 			| else 
	 * 			|	then with double[] collisionPoint = new double[2]
	 * 			| 		and collisionPoint[0] = x1Collision
	 * 			|       and collisionPoint[1] = y1Collision;
	 * 			|		result == collisionPoint
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
