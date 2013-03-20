package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public abstract class ObjectInSpace {

	/**
	 * Initialize a new default object
	 * 
	 * @post the radius of this new object is set on default
	 *			| (new this).radius == 1
	 * @effect the position of this new object is set on default
	 *         |setPosition()
	 * @effect the velocity of this new object is set on default
	 *         |setVelocity()
	 * 
	 */
	public ObjectInSpace(){
		this(0,0,0,0,1,1);
	}
	
	public ObjectInSpace(double radius){
		this(0,0,0,0,radius, 1);
	}
	
	/**
	 * Initialize a new object with given x-coordinate, given y-coordinate, given
	 * velocity in the x direction, given velocity in the y direction, given
	 * radius and given direction
	 * 
	 * @param x
	 *            The x-coordinate of the position for this new object.
	 * @param y
	 *            The y-coordinate of the position for this new object.
	 * @param velocityX
	 *            The velocity in the x direction for this new object.
	 * @param velocityY
	 *            The velocity in the y direction for this new object.
	 * @param radius
	 *            The radius for this new object.
	 * @post the given radius is set as the radius of this new object 
	 *			| (new this).radius == radius
	 * @effect the given x-coordinate and the given y-coordinate is set as 
	 * 			the x-coordinate and the y-coordinate of this new object
	 *         |setPosition(x, y)
	 * @effect the given velocity in the x direction and the given velocity in
	 *         the y direction is set as the velocity in the x direction and the
	 *         velocity in the y direction of this new object
	 *         |setVelocity(velocityX, velocityY)
	 * @throws IllegalArgumentException
	 *             The given radius is not a valid radius for this new object.
	 *             |!isValidRadius(radius)
	 * @throws NullPointerException
	 *             one of the given coordinates or the radius is not an number
	 *        		|!isValidDouble(radius)
	 * 
	 */
	public ObjectInSpace(double x, double y, double velocityX, double velocityY, double radius, double mass) throws IllegalArgumentException,
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
		setMass(mass);
	}

	/**
	 * variable registering the position of the object
	 */
	
	private Position position = new Position();
	
	/**
	 * variable registering the velocity of the object
	 */
	private Velocity velocity = new Velocity();
	
	/**
	 * variable registering the radius of the object
	 */
	private final double radius;
	
	/**
	 * Returns the position of the this object
	 */
	@Basic
	public Position getPosition(){
		return position;
	}
	
	/**
	 * Returns the x coordinate of the this object's position expressed in km
	 * 
	 * @return the x coordinate of the this object's position expressed in km
	 * 			|result == getPosition().getX()
	 */
	public double getX(){
		return getPosition().getX();
	}
	
	/**
	 * Returns the y coordinate of the this object's position expressed in km
	 * 
	 * @return the y coordinate of the this object's position expressed in km
	 * 			|result == getPosition().getY()
	 */
	public double getY(){
		return getPosition().getY();
	}			
	
	/**
	 *  set the position of this object on the given x- and y-coordinates
	 * @param x
	 * 			The x-coordinate in km of the new position
	 * @param y
	 * 			The y-coordinate in km of the new position
	 * @effect the new position of the object has the given x- and y-coordinates	
	 * 			|position.setPosition(x, y)
	 */
	public void setPosition(double x, double y){
		position.setX(x);
		position.setY(y);
	}
	
	/**
	 * Returns the velocity of this object in km/s.
	 */
	@Basic
	public Velocity getVelocity(){
		return velocity;
	}
	
	/**
	 * Returns the velocity of this object in the x direction in km/s.
	 * 
	 * @return the velocity of this object in the x direction in km/s
	 * 			|result == getVelocity().getVelocityX()
	 */
	public double getVelocityX(){
		return getVelocity().getVelocityX();
	}
	
	/**
	 * Returns the velocity of this object in the y direction in km/s.
	 * 
	 * @return the velocity of this object in the y direction in km/s
	 * 			|result == getVelocity().getVelocityY()
	 */
	public double getVelocityY(){
		return getVelocity().getVelocityY();
	}
	
	/**
	 *  set the velocity of this object on the given x- and y-velocities
	 * @param VelocityX
	 * 			The x-velocity in km of the new velocity
	 * @param VelocityY
	 * 			The y-velocity in km of the new velocity
	 * @effect the new velocity of the object has the given x- and y-coordinates	
	 * 			|velocity.setVelocity(velocityX, velocityY)
	 * 
	 */
	public void setVelocity(double velocityX, double velocityY){
		velocity.setVelocity(velocityX, velocityY);
	}
	
	/**
	 * return the radius of the object
	 */
	@Basic
	@Immutable
	public double getRadius() {
		return radius;
	}

	/**
	 * Check whether the radius of the object is valid
	 * 
	 * @param radius
	 *            The radius to check
	 * @return true if radius is larger then the lower bound radius
	 *         | result == (radius > 0) && (isValidDouble(radius))
	 */
	public boolean isValidRadius(double radius) {
		return (radius > 0 && isValidDouble(radius));
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
	 * variable registering the mass of this object
	 *
	 */
	private double mass;

	/**
	 * return the mass of this object
	 *
	 */
	@Basic
	public double getMass() {
		return mass;
	}

	/**
 	*  Check weter mass of this object is valid
 	*  @param mass
 	*         The mass to check
 	*  @return true if mass 
 	*			| result == isValidDouble(mass) && mass > 0
 	*/
	public boolean isValidMass (double mass){
		if(isValidDouble(mass) && mass > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
 	*  Set the mass of this object with the given mass
 	* @param mass
 	* 			the new mass of this object
 	* @post the new mass of this object is equal to the given mass
 	* 			|new.getMass() == mass 
 	* @throws IllegalArgumentException
 	* 			the given mass is not a valid mass
 	* 			| !isValidMass(mass)
 	*/
	public void setMass(double mass) throws IllegalArgumentException{
		if(isValidMass(mass)){
			this.mass = mass;
		} else {
			throw new IllegalArgumentException("the mass of the object must be valid");
		}
	}
	/**
	 * Let the object move within a given time period
	 * 
	 * @effect the object has moved in the given time period
	 *         |this.setPosition(this.getVelocityX()*dt + this.getX(), this.getVelocityY()*dt + this.getY())
	 * @param dt
	 *         the time in which the object needs to move
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
	 * Calculates the distance between this object and the given object.
	 * 
	 * @param object
	 * 			The object between which you calculate the distance
	 * @return Returns the distance between this object and the given object.
	 * 			|result == Math.hypot((this.getX() - object.getX()), (this.getY() - object.getY()))- this.getRadius() - object.getRadius()
	 * @throws IllegalArgumentException
	 *             the given object is this object
	 *             |this == object
	 * @throws NullPointerException
	 *             the given object is null
	 *             |object == null
	 */
	public double getDistanceBetween(ObjectInSpace object)
			throws IllegalArgumentException, NullPointerException {
		if (this == object) {
			throw new IllegalArgumentException("object cannot be compared to itself");
		} else if (object == null) {
			throw new NullPointerException("the given object does not exist");
		} else {
			return Math.hypot((this.getX() - object.getX()), (this.getY() - object.getY()))
					- this.getRadius() - object.getRadius();
		}
	}

	/**
	 * returns true if this object overlaps with the given object
	 * 
	 * @param object
	 * 			The object which to check if it overlaps with this object
	 * @return True if this object is the given object or if the distance between the 2 objects equals 0.
	 * 			|distance = this.getDistanceBetween(object)
	 * 		 	|result == ((this==object) || (Util.fuzzyLessThanOrEqualTo(distance, 0.0))
	 */
	public boolean overlap(ObjectInSpace object) {
		if (this == object) {
			return true;
		} else {
			double distance = this.getDistanceBetween(object);
			if (Util.fuzzyLessThanOrEqualTo(distance, 0.0)) {
				return true;
			} else {
				return false;
			}

		}
	}

	/**
	 * returns the time in which this object collides with the given object, if they
	 * never collide it will give infinite back
	 * 
	 * @param object
	 *            the object from which we need to know when it will collide with
	 *            this object
	 * @return the time in which this object collides with the given object, if they
	 *         never collide it will give infinite back
	 *        |  with object1==this position == (x1,y1) velocity == (vx1,vy1)
	 *        |   and object2 position == (x2,y2) velocity == (vx2,vy2)
	 *        |   and DeltaR== (DeltaX,DeltaY), DeltaV== (DeltaVX,DeltaVY), DeltaR*DeltaR== (DeltaX)^2+(DeltaY)^2
	 *        |   and DeltaV*DeltaV== (DeltaVX)^2+(DeltaVY)^2 and DeltaV*DeltaR== (DeltaVX)*(DeltaX)+(DeltaVY)*(DeltaY)
	 *        |   and Sigma== this.getRadius() + object2.getRadius()
	 *        |   and d== (DeltaV*DeltaR)^2 - (DeltaV*DeltaV)*(DeltaR*DeltaR -Sigma^2)
	 *        |   if(DeltaV*DeltaR >= 0) 
	 *        |		then result == Double.POSITIVE_INFINITY
	 *        |	  else if(d <= 0)
	 *        |		then result == Double.POSITIVE_INFINITY
	 *        |	  else
	 *        |		then result == -((DeltaV*DeltaR)+Math.sqrt(d))/(DeltaV*DeltaV)
	 * @throws NullPointerException
	 *             the given object is null
	 *             |object == null
	 */
	public double getTimeToCollision(ObjectInSpace object) throws NullPointerException{
		if (object == null){
			throw new NullPointerException();
		}
		double x1 = this.getX();
		double y1 = this.getY();
		double vx1 = this.getVelocityX();
		double vy1 = this.getVelocityY();

		double x2 = object.getX();
		double y2 = object.getY();
		double vx2 = object.getVelocityX();
		double vy2 = object.getVelocityY();

		double dvMultiDr = (vx1 - vx2) * (x1 - x2) + (vy1 - vy2) * (y1 - y2);
		double dvMultiDv = Math.pow(vx1 - vx2, 2) + Math.pow(vy1 - vy2, 2);
		double drMultiDr = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);

		if (Util.fuzzyEquals(dvMultiDr, 0.0) || Double.compare(dvMultiDr, 0.0) > 0) {
			return Double.POSITIVE_INFINITY;
		} else {
					
			double dVariable = Math.pow(dvMultiDr, 2) - dvMultiDv * (drMultiDr - Math.pow(this.getRadius()+object.getRadius(), 2));
			
			if (Util.fuzzyLessThanOrEqualTo(dVariable, 0)) {
				return Double.POSITIVE_INFINITY;
			} else {
				double time = -((dvMultiDr + Math.sqrt(dVariable)) / (dvMultiDv));
				return time;
			}
		}

	}
	
	/**
	 * returns the position of the object at the moment of impact with the given object
	 * 
	 * @param object
	 * 			the object from which we need to know at which place this object will collide with it
	 * @return the position of the object at the moment of impact with the given object
	 * 			|if(Double.isInfinite(getTimeToCollision(object)))
	 * 			|	then result == null
	 * 			| else 
	 * 			|	then with double[] collisionPoint = new double[2]
	 * 			| 		and collisionPoint[0] = x1Collision
	 * 			|       and collisionPoint[1] = y1Collision;
	 * 			|		result == collisionPoint
	 * @throws NullPointerException
	 *             the given object is null
	 *             |object == null         
	 */
	public double[] getCollisionPosition(ObjectInSpace object) throws NullPointerException {
		if(object == null){
			throw new NullPointerException();
		}

		double time = getTimeToCollision(object);

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
