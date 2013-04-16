package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class ObjectInSpace {

	/**
	 * Initialize a new default object
	 * 
	 * @post the radius of this new object is set on default | (new this).radius
	 *       == 1
	 * @effect the position of this new object is set on default |setPosition()
	 * @effect the velocity of this new object is set on default |setVelocity()
	 * 
	 */
	public ObjectInSpace() {
		this(0, 0, 0, 0, 1, 1);
	}

	public ObjectInSpace(double radius) {
		this(0, 0, 0, 0, radius, 1);
	}

	/**
	 * Initialize a new object with given x-coordinate, given y-coordinate,
	 * given velocity in the x direction, given velocity in the y direction,
	 * given radius and given direction
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
	 * @post the given radius is set as the radius of this new object | (new
	 *       this).radius == radius
	 * @effect the given x-coordinate and the given y-coordinate is set as the
	 *         x-coordinate and the y-coordinate of this new object
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
	 *             |!isValidDouble(radius)
	 * 
	 */
	public ObjectInSpace(double x, double y, double velocityX,
			double velocityY, double radius, double mass)
			throws IllegalArgumentException, NullPointerException {
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
	 * variable registering the world of this object
	 * 
	 */
	private World world;

	/**
	 * Variable registering whether or not this object is terminated.
	 */
	private boolean isTerminated = false;

	/**
	 * Check whether this object is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}

	public abstract void die();

	/**
	 * Terminate this asteroid.
	 */
	public void terminate() {
		isTerminated = true;
		World world = this.getWorld();
		this.setWorld(null);
		world.removeObjectInSpace(this);
	}

	/**
	 * Returns the position of the this object
	 */
	@Basic
	public Position getPosition() {
		return position;
	}

	/**
	 * Returns the x coordinate of the this object's position expressed in km
	 * 
	 * @return the x coordinate of the this object's position expressed in km
	 *         |result == getPosition().getX()
	 */
	public double getX() {
		return getPosition().getXCoordinate();
	}

	/**
	 * Returns the y coordinate of the this object's position expressed in km
	 * 
	 * @return the y coordinate of the this object's position expressed in km
	 *         |result == getPosition().getY()
	 */
	public double getY() {
		return getPosition().getYCoordinate();
	}

	/**
	 * set the position of this object on the given x- and y-coordinates
	 * 
	 * @param x
	 *            The x-coordinate in km of the new position
	 * @param y
	 *            The y-coordinate in km of the new position
	 * @effect the new position of the object has the given x- and y-coordinates
	 *         |position.setPosition(x, y)
	 */
	public void setPosition(double x, double y) {
		if (this.getWorld() != null) {
			if (x > this.getWorld().getWidth() - this.getRadius()) {
				x = this.getWorld().getWidth() - this.getRadius();
			} else if (x < this.getRadius()) {
				x = this.getRadius();
			}
			if (y > this.getWorld().getHeight() - this.getRadius()) {
				y = this.getWorld().getHeight() - this.getRadius();
			} else if (y < this.getRadius()) {
				y = this.getRadius();
			}
		}
		position = position.changeVector(x, y);
	}

	/**
	 * Returns the velocity of this object in km/s.
	 */
	@Basic
	public Velocity getVelocity() {
		return velocity;
	}

	/**
	 * Returns the velocity of this object in the x direction in km/s.
	 * 
	 * @return the velocity of this object in the x direction in km/s |result ==
	 *         getVelocity().getVelocityX()
	 */
	public double getVelocityX() {
		return getVelocity().getXCoordinate();
	}

	/**
	 * Returns the velocity of this object in the y direction in km/s.
	 * 
	 * @return the velocity of this object in the y direction in km/s |result ==
	 *         getVelocity().getVelocityY()
	 */
	public double getVelocityY() {
		return getVelocity().getYCoordinate();
	}

	/**
	 * set the velocity of this object on the given x- and y-velocities
	 * 
	 * @param VelocityX
	 *            The x-velocity in km of the new velocity
	 * @param VelocityY
	 *            The y-velocity in km of the new velocity
	 * @effect the new velocity of the object has the given x- and y-coordinates
	 *         |velocity.setVelocity(velocityX, velocityY)
	 * 
	 */
	public void setVelocity(double velocityX, double velocityY) {
		velocity = Velocity.createVelocity(velocityX, velocityY);
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
	 * @return true if radius is larger then the lower bound radius | result ==
	 *         (radius > 0) && (isValidDouble(radius))
	 */
	public boolean isValidRadius(double radius) {
		return radius > 0 && isValidDouble(radius);
	}

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
	 * Check whether mass of this object is valid
	 * 
	 * @param mass
	 *            The mass to check
	 * @return true if mass | result == isValidDouble(mass) && mass > 0
	 */
	public boolean isValidMass(double mass) {
		return isValidDouble(mass) && mass > 0;
	}

	/**
	 * Set the mass of this object with the given mass
	 * 
	 * @param mass
	 *            the new mass of this object
	 * @post the new mass of this object is equal to the given mass
	 *       |new.getMass() == mass
	 * @throws IllegalArgumentException
	 *             the given mass is not a valid mass | !isValidMass(mass)
	 */
	protected void setMass(double mass) throws IllegalArgumentException {
		if (isValidMass(mass)) {
			this.mass = mass;
		} else {
			this.mass = 0;
		}
	}

	/**
	 * return the world of this object
	 * 
	 */
	@Basic
	public World getWorld() {
		return world;
	}

	/**
	 * Check whether this objectInSpace can have the given world as its world.
	 * 
	 * @param world
	 *            The world to check.
	 * @return If this objectInSpace is terminated, returns true if the given
	 *         world is null. | if (this.isTerminated()) | then result == (world
	 *         == null)
	 * @return If this objectInSpace is not terminated, returns true if the
	 *         given world is not null and not terminated. | if (!
	 *         this.isTerminated()) | then result == | (world != null) && | (!
	 *         world.isTerminated())
	 */
	@Raw
	public boolean canHaveAsWorld(World world) {
		if (this.isTerminated())
			return world == null;
		return (world != null) && (!world.isTerminated());
	}

	/**
	 * Check whether this objectInSpace has a proper world.
	 * 
	 * @return True if this objectInSpace can have this world as world and this
	 *         world is either null or it has this objectInSpace as one of its
	 *         objects. | result == | canHaveAsWorld(getWorld()) && | (
	 *         (getWorld() == null) || getWorld().hasAsObjectInSpace(this))
	 */
	@Raw
	public boolean hasProperWorld() {
		return canHaveAsWorld(getWorld())
				&& ((getWorld() == null) || (getWorld()
						.hasAsObjectInSpace(this)));
	}

	/**
	 * Set the world of this object with the given world
	 * 
	 * @param world
	 *            the new world of this object
	 * @post the new world of this object is equal to the given world
	 *       |new.getWorld() == world
	 * @throws IllegalArgumentException
	 *             This objectInSpace cannot have the given world as its world.
	 *             | ! canHaveAsWorld(world)
	 */
	public void setWorld(World world) {
		if (!canHaveAsWorld(world))
			throw new IllegalArgumentException("Inappropriate world");
		this.world = world;
	}

	/**
	 * Let the object move within a given time period
	 * 
	 * @effect the object has moved in the given time period
	 *         |this.setPosition(this.getVelocityX()*dt + this.getX(),
	 *         this.getVelocityY()*dt + this.getY())
	 * @param dt
	 *            the time in which the object needs to move
	 * @throws IllegalArgumentException
	 *             The given time is not a valid time |!isValidTime(dt)
	 */
	public void move(double dt) throws NullPointerException,
			IllegalArgumentException {
		if (isValidTime(dt)) {
			setPosition(this.getVelocityX() * dt + this.getX(),
					this.getVelocityY() * dt + this.getY());
		} else {
			throw new IllegalArgumentException(
					"the time must be more then zero");
		}
	}

	/**
	 * Check whether the time is more or equal to zero
	 * 
	 * @param dt
	 *            the time to check
	 * @return true if time is more or equal to zero and a number |result == dt
	 *         >= 0 && isValidDouble(dt)
	 */
	public boolean isValidTime(double dt) {
		return dt >= 0 && isValidDouble(dt);
	}

	/**
	 * Calculates the distance between this object and the given object.
	 * 
	 * @param object
	 *            The object between which you calculate the distance
	 * @return Returns the distance between this object and the given object.
	 *         |result == Math.hypot((this.getX() - object.getX()), (this.getY()
	 *         - object.getY()))- this.getRadius() - object.getRadius()
	 * @throws IllegalArgumentException
	 *             the given object is this object |this == object
	 * @throws NullPointerException
	 *             the given object is null |object == null
	 */
	public double getDistanceBetween(ObjectInSpace object)
			throws IllegalArgumentException, NullPointerException {
		if (this == object) {
			throw new IllegalArgumentException(
					"object cannot be compared to itself");
		} else if (object == null) {
			throw new NullPointerException("the given object does not exist");
		} else {
			VectorInSpace positionChange = Position.vectorChange(
					this.getPosition(), object.getPosition());
			return Position.norm(positionChange) - this.getRadius()
					- object.getRadius();
		}
	}

	/**
	 * returns true if this object overlaps with the given object
	 * 
	 * @param object
	 *            The object which to check if it overlaps with this object
	 * @return True if this object is the given object or if the distance
	 *         between the 2 objects equals 0. |distance =
	 *         this.getDistanceBetween(object) |result == ((this==object) ||
	 *         (Util.fuzzyLessThanOrEqualTo(distance, 0.0))
	 */
	public boolean overlap(ObjectInSpace object) {
		if (this == object) {
			return true;
		} else {
			if ((Bullet.isBullet(object) && Ship.isShip(this) && (((Bullet) object)
					.getShip() == this))
					|| (Bullet.isBullet(this) && Ship.isShip(object) && ((Bullet) this)
							.getShip() == object)) {
				return false;
			} else {
				double distance = this.getDistanceBetween(object);
				if (Util.fuzzyLessThanOrEqualTo(distance, Util.EPSILON)) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * returns the time in which this object collides with the given object, if
	 * they never collide it will give infinite back
	 * 
	 * @param object
	 *            the object from which we need to know when it will collide
	 *            with this object
	 * @return the time in which this object collides with the given object, if
	 *         they never collide it will give infinite back | with
	 *         object1==this position == (x1,y1) velocity == (vx1,vy1) | and
	 *         object2 position == (x2,y2) velocity == (vx2,vy2) | and DeltaR==
	 *         (DeltaX,DeltaY), DeltaV== (DeltaVX,DeltaVY), DeltaR*DeltaR==
	 *         (DeltaX)^2+(DeltaY)^2 | and DeltaV*DeltaV==
	 *         (DeltaVX)^2+(DeltaVY)^2 and DeltaV*DeltaR==
	 *         (DeltaVX)*(DeltaX)+(DeltaVY)*(DeltaY) | and Sigma==
	 *         this.getRadius() + object2.getRadius() | and d==
	 *         (DeltaV*DeltaR)^2 - (DeltaV*DeltaV)*(DeltaR*DeltaR -Sigma^2) |
	 *         if(DeltaV*DeltaR >= 0) | then result == Double.POSITIVE_INFINITY
	 *         | else if(d <= 0) | then result == Double.POSITIVE_INFINITY |
	 *         else | then result ==
	 *         -((DeltaV*DeltaR)+Math.sqrt(d))/(DeltaV*DeltaV)
	 * @throws NullPointerException
	 *             the given object is null |object == null
	 */
	public double getTimeToCollision(ObjectInSpace object)
			throws NullPointerException {
		if (object == null) {
			return Double.POSITIVE_INFINITY;
		}
		VectorInSpace positionChange = Position.vectorChange(
				this.getPosition(), object.getPosition());
		VectorInSpace velocity = Velocity.vectorChange(this.getVelocity(),
				object.getVelocity());

		double dvMultiDr = VectorInSpace.inProduct(velocity, positionChange);
		double dvMultiDv = VectorInSpace.inProduct(velocity, velocity);
		double drMultiDr = VectorInSpace.inProduct(positionChange,
				positionChange);

		if (Util.fuzzyEquals(dvMultiDr, 0.0)
				|| Double.compare(dvMultiDr, 0.0) > 0) {
			return Double.POSITIVE_INFINITY;
		} else {

			double dVariable = Math.pow(dvMultiDr, 2)
					- dvMultiDv
					* (drMultiDr - Math.pow(
							this.getRadius() + object.getRadius(), 2));

			if (Util.fuzzyLessThanOrEqualTo(dVariable, 0)) {
				return Double.POSITIVE_INFINITY;
			} else {
				double time = -((dvMultiDr + Math.sqrt(dVariable)) / (dvMultiDv));
				return time;
			}
		}

	}

	/**
	 * returns the time on which the object will collide with one of the
	 * horizontal wands
	 * 
	 * @return
	 */
	public double getTimeToCollisionWithWorldHorizentalWand() {
		double velocityY = this.getVelocityY();
		if (this.getWorld() != null) {
			if (velocityY > 0) {
				double afstand = getWorld().getHeight() - this.getY() - this.getRadius() ;
				return afstand / velocityY;
			} else if (velocityY < 0) {
				double afstand = this.getY() - this.getRadius();
				return afstand / velocityY;
			} else {
				return Double.POSITIVE_INFINITY;
			}
		} else {
			return Double.POSITIVE_INFINITY;
		}
	}

	/**
	 * returns the time on which the object will collide with one of the
	 * vertical wands
	 * 
	 * @return
	 */
	public double getTimeToCollisionWithWorldVerticalWand() {
		double velocityX = this.getVelocityX();
		if (this.getWorld() != null) {
			if (velocityX > 0) {
				double afstand = getWorld().getWidth() - this.getX() - this.getRadius();
				return afstand / velocityX;
			} else if (velocityX < 0) {
				double afstand = this.getX() - this.getRadius();
				return afstand / velocityX;
			} else {
				return Double.POSITIVE_INFINITY;
			}
		} else {
			return Double.POSITIVE_INFINITY;
		}
	}

	/**
	 * returns with which wand the object will first collide
	 * 
	 * @return
	 */
	public int collisionWithWhichWand() {
		double timeHorizental = getTimeToCollisionWithWorldHorizentalWand();
		double timeVertical = getTimeToCollisionWithWorldVerticalWand();
		if (!Double.isInfinite(timeHorizental)
				&& Double.isInfinite(timeVertical)) {
			return 1;
		} else if (Double.isInfinite(timeHorizental)
				&& !Double.isInfinite(timeVertical)) {
			return 2;
		} else {
			if (timeHorizental < timeVertical) {
				return 1;
			} else if (timeHorizental > timeVertical) {
				return 2;
			} else {
				return 3;
			}
		}
	}

	/**
	 * returns the smallest time needed to collide with one of the wands
	 * 
	 * @return
	 */
	public double getTimeToCollisionWithWorldWand() {
		int wand = collisionWithWhichWand();
		if (wand == 1) {
			return getTimeToCollisionWithWorldHorizentalWand();
		} else if (wand == 2) {
			return getTimeToCollisionWithWorldVerticalWand();
		} else if (wand == 3) {
			return getTimeToCollisionWithWorldHorizentalWand();
		} else {
			return Double.POSITIVE_INFINITY;
		}
	}

	/**
	 * returns the position where this object and the given object are going to
	 * collide
	 * 
	 * @param object
	 *            the object from which we need to know at which place it will
	 *            collide with this object
	 * @return the position where this object and the given object are going to
	 *         collide
	 * 
	 * @throws NullPointerException
	 *             the given object is null |object == null
	 */
	public double[] getCollisionPosition(ObjectInSpace object)
			throws NullPointerException {
		if (object == null) {
			throw new NullPointerException();
		}

		double time = getTimeToCollision(object);

		if (!Double.isInfinite(time)) {
			Position position1 = new Position(this.getX() + time
					* this.getVelocityX(), this.getY() + time
					* this.getVelocityY());
			Position position2 = new Position(object.getX() + time
					* object.getVelocityX(), object.getY() + time
					* object.getVelocityY());

			VectorInSpace positionChanged = Position.vectorChange(position2,
					position1);
			double directionChanged = Position.getDirection(positionChanged);

			double r1 = this.getRadius();

			double[] collisionPoint = new double[2];
			collisionPoint[0] = position1.getXCoordinate() + r1
					* Math.cos(directionChanged);
			collisionPoint[1] = position1.getYCoordinate() + r1
					* Math.sin(directionChanged);

			return collisionPoint;

		} else {
			return null;
		}

	}

	/**
	 * this object collides with the given object
	 */
	public void collide(ObjectInSpace object) {
		if (object != null ) {
			if (Bullet.isBullet(object) || Bullet.isBullet(this)) {
				
					object.die();
					this.die();
				
			} else {
				if (Asteroid.isAsteroid(this) && Asteroid.isAsteroid(object)) {
					this.bounce(this, object);
				} else if (Ship.isShip(object) && Ship.isShip(this)) {
					this.bounce(this, object);
				} else {
					if (Ship.isShip(object) && Asteroid.isAsteroid(this)) {
						object.die();
					} else if (Ship.isShip(this) && Asteroid.isAsteroid(object)) {
						this.die();
					}
				}
			}

		}
	}

	
	public void bounce(ObjectInSpace object1, ObjectInSpace object2) {
		double mass1 = object1.getMass();
		double mass2 = object2.getMass();
		double vx1 = object1.getVelocityX();
		double vy1 = object1.getVelocityY();
		double vx2 = object2.getVelocityX();
		double vy2 = object2.getVelocityY();
		double afstand = object1.getRadius() + object2.getRadius();
		VectorInSpace positionChange = Position.vectorChange(
				object1.getPosition(), object2.getPosition());
		VectorInSpace velocity = Velocity.vectorChange(object1.getVelocity(),
				object2.getVelocity());
		double dvMultiDr = VectorInSpace.inProduct(velocity, positionChange);

		double j = (2 * mass1 * mass2 * dvMultiDr) / (afstand * (mass1 + mass2));
		double jx = (j * positionChange.getXCoordinate()) / afstand;
		double jy = (j * positionChange.getYCoordinate()) / afstand;

		object1.setVelocity(vx1 + (jx / mass1), vy1 + (jy / mass1));
		object2.setVelocity(vx2 + (jx / mass2), vy2 + (jy / mass2));
	}

	public void collideWithWand() {
		int wand = collisionWithWhichWand();
		if (wand == 1) {
			this.setVelocity(this.getVelocityX(), -(this.getVelocityY()));
		} else if (wand == 2) {
			this.setVelocity(-(this.getVelocityX()), this.getVelocityY());
		} else if (wand == 3) {
			this.setVelocity(-(this.getVelocityX()), -(this.getVelocityY()));
		}
	}

}
