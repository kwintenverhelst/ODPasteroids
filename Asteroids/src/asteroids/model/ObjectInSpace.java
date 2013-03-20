package asteroids.model;

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
}
