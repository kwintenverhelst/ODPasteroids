package asteroids.model;

import java.util.*;

import javax.swing.Timer;

import asteroids.CollisionListener;
import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * ...
 * 
 * @invar ... 
 * 		| isValidHeight(getHeight())
 * @invar ...
 * 		| isValidWidth(getWidth())
 * @invar ... 
 * 		| hasProperObjectsInSpace()
 * 
 * @author Mathieu Vermeire & Kwinten Verhelst
 * @version 2.2
 * 
 */

public class World {

	/**
	 * 
	 * @param height
	 * @param width
	 * @post ... 
	 * 		| (new this).getHeight() == height
	 * @post ... 
	 * 		| (new this).getWidth() == width
	 * @throws IllegalArgumentException ... 
	 * 		| (!isValidHeight(height)) || (!isValidWidth(width))
	 */
	public World(double height, double width) throws IllegalArgumentException {
		if (!isValidHeight(height))
			throw new IllegalArgumentException("not a valid height");
		this.height = height;
		if (!isValidWidth(width))
			throw new IllegalArgumentException("not a valid width");
		this.width = width;
	}

	/**
	 * ...
	 */
	@Basic
	@Immutable
	public double getHeight() {
		return this.height;
	}

	/**
	 * @return ... 
	 * 		| result == (height >= 0) && (height <= getHeightLimit()) && (!Double.isNaN(height))
	 */
	public boolean isValidHeight(double height) {
		return (height >= 0 && height <= heightLimit && !Double.isNaN(height));
	}

	private final double height;

	/**
	 * ...
	 */
	@Basic
	@Immutable
	public static double getHeightLimit() {
		return heightLimit;
	}

	private final static double heightLimit = Double.MAX_VALUE;

	/**
	 * ...
	 */
	@Basic
	@Immutable
	public double getWidth() {
		return this.width;
	}

	/**
	 * @return ... 
	 * 		| result == (width >= 0) && (width <= getWidthLimit()) && (!Double.isNaN(width))
	 */
	public boolean isValidWidth(double width) {
		return (width >= 0 && width <= widthLimit && !Double.isNaN(width));
	}

	private final double width;

	/**
	 * ...
	 */
	@Basic
	@Immutable
	public static double getWidthLimit() {
		return widthLimit;
	}

	private final static double widthLimit = Double.MAX_VALUE;

	/**
	 * ...
	 */
	@Basic
	public boolean hasAsObjectInSpace(ObjectInSpace objectInSpace) {
		return objectsInSpace.contains(objectInSpace);
	}

	/**
	 * 
	 * @param objectInSpace
	 * @return ... 
	 * 		| result = (objectInSpace != null) 
	 * 		| && (objectInSpace.canHaveAsWorld(this)
	 *  	| && ((new this).isFullyInWorld(objectInSpace)) 
	 */
	public boolean canHaveAsObjectInSpace(ObjectInSpace objectInSpace) {
		if (objectInSpace == null){
			return false;
		}
		if (!objectInSpace.canHaveAsWorld(this)){
			return false;
		}
		if (!isFullyInWorld(objectInSpace)){
			return false;
		}

		return true;
	}

	/**
	 * ...
	 * 
	 * @param objectInSpace
	 * @return ... 
	 * 		| result ==(0<(objectInSpace.getX()-objectInSpace.getRadius())) 
	 * 		| && ((objectInSpace.getX()+objectInSpace.getRadius())<this.getWidth())
	 * 		| && (0<(objectInSpace.getY()-objectInSpace.getRadius())) 
	 * 		| && ((objectInSpace.getY()+objectInSpace.getRadius())<this.getHeigth())
	 */
	private boolean isFullyInWorld(ObjectInSpace objectInSpace) {
		if (Util.fuzzyLessThanOrEqualTo(getWidth(),(objectInSpace.getX() + objectInSpace.getRadius())))
			return false;
		if (Util.fuzzyLessThanOrEqualTo((objectInSpace.getX() - objectInSpace.getRadius()), 0))
			return false;
		if (Util.fuzzyLessThanOrEqualTo(getHeight(),(objectInSpace.getY() + objectInSpace.getRadius())))
			return false;
		if (Util.fuzzyLessThanOrEqualTo((objectInSpace.getY() - objectInSpace.getRadius()), 0))
			return false;
		return true;

	}

	/**
	 * ...
	 * 
	 * @return ... 
	 * 		| for each objectInSpace in ObjectInSpace: 
	 * 		| if(hasAsObjectInSpace(objectInSpace)) 
	 * 		| then canHaveAsObjectInSpace(objectInSpace) 
	 * 		| 	&& (objectInSpace.getWorld()==this)
	 */
	public boolean hasProperObjectsInSpace() {
		for (ObjectInSpace objectInSpace : this.objectsInSpace) {
			if (!canHaveAsObjectInSpace(objectInSpace))
				return false;
			if (objectInSpace.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * ...
	 * 
	 * @param objectInSpace
	 * @post ...
	 * 		| (new this).hasAsObjectInSpace(objectInSpace)
	 * @post ...
	 * 		| (new objectInSpace).getWorld()== this
	 * @post ...
	 * 		| for each otherObject in objectsInSpace:
	 * 		|	if(objectInSpace.overlap(otherObject)) && (otherObject != null)
	 * 		|		then objectInSpace.collide(otherObject)   
	 * @throws IllegalArgumentException ...
	 * 		| !canHaveAsObjectInSpace(objectInSpace)
	 */
	public void addObjectInSpace(ObjectInSpace objectInSpace)
		       throws IllegalArgumentException {
		if (!canHaveAsObjectInSpace(objectInSpace)){
			throw new IllegalArgumentException("This is object can't be in the world");
		}
		boolean noProblems = true;
		for (ObjectInSpace otherInSpace : objectsInSpace) {
			if(Asteroid.isAsteroid(objectInSpace) && Asteroid.isAsteroid(otherInSpace) && (((Asteroid) objectInSpace).getParent() != null) &&(((Asteroid) objectInSpace).getParent() == ((Asteroid) otherInSpace).getParent()) ){
				noProblems = true;
			} else{
				if(objectInSpace.overlap(otherInSpace)){
					if(Util.absoluteError(objectInSpace.getDistanceBetween(otherInSpace), 0) <= 0.01){
					
						objectInSpace.collide(otherInSpace);
					}
					else{
		    		noProblems = false;
					}
				}
			}
		}
		if(noProblems){
			objectInSpace.setWorld(this);
			objectsInSpace.add(objectInSpace);			
			addFirstCollision(objectInSpace);
			
		}
	}

	/**
	 * ...
	 * 
	 * @param objectInSpace
	 * @post ... 
	 * 		| !(new this).hasAsObjectInSpace(objectInSpace)
	 * @throws NullPointerException ... 
	 * 		| objectInSpace == null
	 * @throws IllegalArgumentException ...
	 * 		| !this.hasAsObjectInSpace(objectInSpace)
	 * @throws IllegalStateException ...
	 * 		| objectInSpace.getWorld()!=null
	 */
	@Raw
	public void removeObjectInSpace(ObjectInSpace objectInSpace)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		if (objectInSpace == null)
			throw new NullPointerException("not a valid object");
		
		if (objectInSpace.getWorld() != null)
			throw new IllegalStateException(
					"this object still refers to this world");
		objectsInSpace.remove(objectInSpace);
		removeFirstCollision(objectInSpace);
	}

	/**
	 * ...
	 * 
	 * @return ...
	 * 		 | for each objectInSpace in result :
	 * 		 | 		this.hasAsObjectInSpace(objectInSpace)
	 */
	public Set<ObjectInSpace> getAllObjectsInSpace() {
		return new HashSet<ObjectInSpace>(objectsInSpace);
	}

	/**
	 * @invar ... 
	 * 		| objectsInSpace != null
	 * @invar ...
	 * 		| for each objectInSpace in objectsInSpace:
	 *      |	  ((objectInSpace!=null) && (!objectInSpace.isTerminated())
	 */
	private final Set<ObjectInSpace> objectsInSpace = new HashSet<ObjectInSpace>();

	/**
	 * ...
	 * 
	 * @return ... 
	 * 		| for each ship in result :
	 * 		| 	Ship.isShip(ship) && this.hasAsObjectInSpace(ship)
	 * @throws IllegalStateException ...
	 *      |this.isTerminated()
	 */
	public Set<Ship> getShips() throws IllegalStateException {
		if (isTerminated())
			throw new IllegalStateException("World is Terminated");
		Set<Ship> ships = new HashSet<Ship>();
		for (ObjectInSpace objectInSpace : objectsInSpace) {
			if (objectInSpace instanceof Ship)
				ships.add((Ship) objectInSpace);
		}
		return ships;
	}

	/**
	 * ...
	 * 
	 * @return ... 
	 * 		| for each asteroid in result :
	 * 		| 	Asteroid.isAsteroid(asteroid) && this.hasAsObjectInSpace(asteroid)
	 * @throws IllegalStateException ...
	 * 		|this.isTerminated()
	 */
	public Set<Asteroid> getAsteroids() {
		if (isTerminated())
			throw new IllegalStateException("World is Terminated");
		Set<Asteroid> asteroids = new HashSet<Asteroid>();
		for (ObjectInSpace objectInSpace : objectsInSpace) {
			if (objectInSpace instanceof Asteroid)
				asteroids.add((Asteroid) objectInSpace);
		}
		return asteroids;
	}

	/**
	 * ...
	 * 
	 * @return ... 
	 * 		| for each bullet in result :
	 * 		| 	Bullet.isBullet(bullet) && this.hasAsObjectInSpace(bullet)
	 * @throws IllegalStateException ...
	 *      |this.isTerminated()
	 */
	public Set<Bullet> getBullets() {
		if (isTerminated())
			throw new IllegalStateException("World is Terminated");
		Set<Bullet> bullets = new HashSet<Bullet>();
		for (ObjectInSpace objectInSpace : objectsInSpace) {
			if (objectInSpace instanceof Bullet)
				bullets.add((Bullet) objectInSpace);
		}
		return bullets;
	}

	/**
	 * ...
	 * 
	 * @post ... 
	 * 		| (new this).isTerminated()
	 * @post ... 
	 * 		| for each objectInSpace in objectsInSpace: 
	 * 		| (new objectInSpace).isTerminated()
	 */
	@Raw
	public void terminate() {
		if (!isTerminated()) {
			for (ObjectInSpace objectInSpace : getAllObjectsInSpace())
				objectInSpace.terminate();
			this.isTerminated = true;
		}
	}

	/**
	 * ...
	 */
	@Basic
	public boolean isTerminated() {
		return isTerminated;
	}

	private boolean isTerminated = false;

	/**
	 * 
	 * @param objectInSpace
	 * @return ... 
	 * 		| for each otherObject in objectsInSpace: 
	 *      | if(Util.fuzzyLessThanOrEqualTo(objectInSpace.getTimeToCollisionWithWorldWand(),objectInSpace.getTimeToCollision(otherObject))
	 *      | result==null
	 * @return ...
	 * 		| else for each otherObject in objectsInSpace: 
	 *      |  Util.fuzzyLessThanOrEqualTo(objectInSpace.getTimeToCollision(result),objectInSpace.getTimeToCollision(otherObject))
	 */
	private ObjectInSpace calculateFirstCollision(ObjectInSpace objectInSpace) {
		ObjectInSpace firstCollision = null;
		double collisionTime = objectInSpace.getTimeToCollisionWithWorldWand();
		for (ObjectInSpace otherObject : getAllObjectsInSpace())
			if (Util.fuzzyLessThanOrEqualTo(
					objectInSpace.getTimeToCollision(otherObject),
					collisionTime))
				firstCollision = otherObject;
		return firstCollision;
	}

	/**
	 * ...
	 * 
	 * @param objectInSpace
	 * @effect ... 
	 * 		| if(objectInSpace!=null) 
	 * 		| then for each otherObject in objectsInSpace:
	 * 		| addFirstCollision(otherObject)
	 * @effect ... 
	 * 		| if(!objectInSpace.isTerminated()) 
	 *      |   addFirstCollision(objectInSpace)
	 */
	public void updateFirstCollisions(ObjectInSpace objectInSpace) {
		if (objectInSpace != null) {
			for (ObjectInSpace otherObject : getAllObjectsInSpace()) {
				if (firstCollisions.get(otherObject) == objectInSpace) {
					addFirstCollision(otherObject);
				}
			}
			if (!objectInSpace.isTerminated()) {
				addFirstCollision(objectInSpace);
			}
		}
	}

	/**
	 * ...
	 */
	public boolean hasAsFirstCollision(ObjectInSpace objectInSpace) {
		return firstCollisions.containsKey(objectInSpace);
	}

	/**
	 * ...
	 * 
	 * @param objectInSpace
	 * @post ...
	 * 		| (new this).hasAsFirstCollision(objectInSpace)
	 */
	private void addFirstCollision(@Raw ObjectInSpace objectInSpace) {
		firstCollisions.put(objectInSpace, calculateFirstCollision(objectInSpace));
	}

	/**
	 * ...
	 * 
	 * @param objectInSpace
	 * @post ..
	 * 		| !(new this).hasAsFirstCollision(objectInSpace)
	 */
	@Raw
	private void removeFirstCollision(ObjectInSpace objectInSpace) {
		for (ObjectInSpace otherObject : objectsInSpace) {
			if (firstCollisions.get(otherObject) == objectInSpace)
				addFirstCollision(otherObject);
		}
		firstCollisions.remove(objectInSpace);
	}

	/**
	 * @invar ... 
	 * 		| firstCollisions != null
	 * @invar ... 
	 * 		| for each key in firstCollisions.keySet(): 
	 *      |  (canHaveAsObjectInSpace(key) && (key.getWorld()==this)
	 * @invar ... 
	 * 		| for each key in firstCollisions.keySet(): 
	 *      |  	  (!firstCollisions.get(key).isTerminated())
	 *      | &&  (firstCollisions.get(key)!=key)
	 * @invar ...
	 * 		| for each key in firstCollisions.keySet(): 
	 *      |  	  this.hasAsObjectInSpace(firstCollisions.get(key))
	 *      | ||  (firstCollisions.get(key)==null)
	 */
	private final Map<ObjectInSpace, ObjectInSpace> firstCollisions = new HashMap<ObjectInSpace, ObjectInSpace>();

	/**
	 * ...
	 * 
	 * @param time
	 * @param collisionListener
	 * @post ... 
	 * 		| if((this.hasAsObjectInSpace(firstCollision)) 
	 * 		|	 && (firstCollisions.get(firstCollision)==null) 
	 * 		| 	 && (Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollisionWithWand(),time))
	 * 		| 	 && for each otherObject in objectsInSpace: 
	 * 		|    		if(firstCollisions.get(otherObject)!=null) 
	 * 		| 				(Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollisionWithWand(),otherObject.getTimeToCollision(firstCollisions.get(otherObject)))) 
	 * 		| 		 || if(firstCollisions.get(otherObject)==null) 
	 * 		|       		(Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollisionWithWand(),otherObject.getTimeToCollisionWithWand())) 
	 * 		| then for each otherObject in objectsInSpace: 
	 *  	| 	 objectInSpace.move(firstCollision.getTimeToCollisionWithWand()) 
	 *  	| 	 if(Ship.isShip(objectInSpace)) && ((Ship) objectInSpace.checkIfThrustIsEnabled())) 
	 *  	|		 then objectInSpace.thrust (firstCollision.getTimeToCollisionWithWand()) 
	 *  	|	 firstCollision.collideWithWand() 
	 *  	|	 collisionListener.boundaryCollision(firstCollider, firstCollider.getX(), firstCollider.getY())
	 *      |	 this.updateCollisions(firstCollision)
	 *      |	 this.evolve(time-firstCollision.getTimeToCollisionWithWand())
	 * @post ... 
	 * 		| if((hasAsObjectInSpace(firstCollision))
	 * 		|    && (firstCollisions.get(firstCollision)!=null) 
	 * 		|	 && (Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollision(firstCollisions.get(firstCollision)),time)) 
	 * 		| 	 && for each otherObject in objectsInSpace:
	 * 		| 			if(firstCollisions.get(otherObject)!=null) 
	 *      |				(Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollision(firstCollisions.get(firstCollision)),otherObject.getTimeToCollision(firstCollisions.get(otherObject))))
	 *      | 		 || if(firstCollisions.get(otherObject)==null) 
	 *      |				(Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollision(firstCollisions.get(firstCollision)),otherObject.getTimeToCollisionWithWand()))
	 *      | then for each otherObject in objectsInSpace: 
	 *      |	 objectInSpace.move(firstCollision.getTimeToCollision(firstCollisions.get(firstCollision))) 
	 *      | 	 if(Ship.isShip(objectInSpace)) && ((Ship) objectInSpace.checkIfThrustIsEnabled())) 
	 *      | 		then objectInSpace.thrust(firstCollision.getTimeToCollision(firstCollisions.get(firstCollision)))
	 *      |	 collisionListener.objectCollision(firstCollider, secondCollider, firstCollider.getCollisionPosition(secondCollider)[0],firstCollider.getCollisionPosition(secondCollider)[1])
	 *      | 	 firstCollision.collide(firstCollisions.get(firstCollision))
	 *      | 	 this.updateCollisions(firstCollision)
	 *      | 	 this.updateCollisions(firstCollisions.get(firstCollision)) 
	 *      |    this.evolve(time-firstCollision.getTimeToCollision(firstCollisions.get(firstCollision)))
	 * @post ... 
	 * 		| for each firstCollision in firstCollisions: 
	 * 		|   if((!Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollisionWithWand(),time) 
	 * 		|	&& (!Util.fuzzyLessThanOrEqualTo(firstCollision.getTimeToCollision(firstCollisions.get(firstCollision)),time)) 
	 * 		| 	then for each objectInSpace in objectsInSpace: 
	 * 		| 		objectInSpace.move(time) 
	 * 		| 	 && if(Ship.isShip(objectInSpace)) && ((Ship) objectInSpace.checkIfThrustIsEnabled()))
	 *      | 			then objectInSpace.thrust(time)
	 */
	public void evolve(double time, CollisionListener collisionListener) {
		double timeToFirstCollision = time;
		ObjectInSpace firstCollider = null;
		ObjectInSpace secondCollider = null;
		executePrograms();
		for (ObjectInSpace objectInSpace : objectsInSpace) {
			double timeToCollision;
			if (firstCollisions.get(objectInSpace) == null) {
				timeToCollision = objectInSpace.getTimeToCollisionWithWorldWand();
			} else {
				timeToCollision = objectInSpace.getTimeToCollision(firstCollisions.get(objectInSpace));
			}
			if (Util.fuzzyLessThanOrEqualTo(timeToCollision,timeToFirstCollision)) {
				timeToFirstCollision = timeToCollision;
				firstCollider = objectInSpace;
				secondCollider = firstCollisions.get(objectInSpace);
			}
		}
		if (firstCollider != null) {
			for (ObjectInSpace objectInSpace : objectsInSpace) {
				objectInSpace.move(timeToFirstCollision);
				if (objectInSpace instanceof Ship && ((Ship) objectInSpace).checkIfThrustIsEnabled()) {
					((Ship) objectInSpace).thrust(timeToFirstCollision);
					updateFirstCollisions(objectInSpace);
				}
			}

			if (secondCollider == null) {
				firstCollider.collideWithWand();
				collisionListener.boundaryCollision(firstCollider, firstCollider.getX(), firstCollider.getY());
			} else {
				collisionListener.objectCollision(firstCollider, secondCollider, firstCollider.getCollisionPosition(secondCollider)[0],firstCollider.getCollisionPosition(secondCollider)[1]);
				firstCollider.collide(secondCollider);
			}
			updateFirstCollisions(firstCollider);
			updateFirstCollisions(secondCollider);
			double newTime = time - timeToFirstCollision;
			evolve(newTime, collisionListener);
		} else {
			if (!(Util.fuzzyLessThanOrEqualTo(time, 0.0))) {
				for (ObjectInSpace objectInSpace : objectsInSpace) {
					objectInSpace.move(time);
					if (objectInSpace instanceof Ship && ((Ship) objectInSpace).checkIfThrustIsEnabled()) {
						((Ship) objectInSpace).thrust(time);
						updateFirstCollisions(objectInSpace);
					}

				}
			}
		}
	}
	
	private long timeOfLastExecute;
	
	private void executePrograms(){
		long now = System.currentTimeMillis();
		long timeSinceLastExecute = now - timeOfLastExecute;
		if(timeSinceLastExecute >= 200){
			timeOfLastExecute = now;
			for (Ship ship : getShips()) {
				if (ship.hasProgram()) {
					ship.getProgram().execute();
				}
			}
		}
	}
}
