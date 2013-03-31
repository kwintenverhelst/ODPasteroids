package asteroids.model;

import java.util.*;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;


/**
 * 
 * @author Mathieu Vermeire & Kwinten Verhelst
 * @version	2.0
 *
 */

public class World {
	
	public World(double height, double width) throws IllegalArgumentException{
		if(!isValidHeight(height))
			throw new IllegalArgumentException("not a valid height");
		this.height=height;
		if(!isValidWidth(width))
			throw new IllegalArgumentException("not a valid width");
		this.width=width;
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	@Immutable
	public double getHeight (){
		return height;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isValidHeight(double height){
		return (height >= 0 && height <= heightLimit && height!=Double.NaN);
	}
	
	private final double height;
	
	/**
	 * 
	 * @return
	 */
	@Basic
	@Immutable
	public static double getHeightLimit(){
		return heightLimit;
	}
	
	private final static double heightLimit = Double.MAX_VALUE;
	
	/**
	 * 
	 * @return
	 */
	@Basic
	@Immutable
	public double getWidth (){
		return width;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isValidWidth(double width){
		return (width >= 0 && width <= widthLimit && width!=Double.NaN);
	}
	
	private final double width;
	
	/**
	 * 
	 * @return
	 */
	@Basic
	@Immutable
	public static double getWidthLimit(){
		return widthLimit;
	}
	
	private final static double widthLimit = Double.MAX_VALUE;

	/**
	 * 
	 * @param objectInSpace
	 * @return
	 */
	public boolean HasAsObjectInSpace (ObjectInSpace objectInSpace){
		return objectsInSpace.contains(objectInSpace);
	}
	
	/**
	 * 
	 * @param objectInSpace
	 * @return
	 */
	public boolean CanHaveAsObjectInSpace (ObjectInSpace objectInSpace){
		if((objectInSpace == null)||(this.isTerminated())||(objectInSpace.isTerminated()))
			return false;
		 if(!isFullyInWorld(objectInSpace))
			 return false;
		 for (ObjectInSpace otherInSpace: objectsInSpace){
			 if(objectInSpace.overlap(otherInSpace))
				 return false;
		 }
		return true;	 
	}
	
	/**
	 * 
	 * @param objectInSpace
	 * @return
	 */
	public boolean isFullyInWorld(ObjectInSpace objectInSpace){
		if(Util.fuzzyLessThanOrEqualTo(getWidth(), (objectInSpace.getX()+objectInSpace.getRadius())))
			return false;
		if(Util.fuzzyLessThanOrEqualTo((objectInSpace.getX()-objectInSpace.getRadius()),0))
			return false;
		if(Util.fuzzyLessThanOrEqualTo(getHeight(),(objectInSpace.getY()+objectInSpace.getRadius())))
			return false;
		if(Util.fuzzyLessThanOrEqualTo((objectInSpace.getY()-objectInSpace.getRadius()),0))
			return false;
		return true;
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean HasProperObjectsInSpace (){
		for(ObjectInSpace objectInSpace: this.objectsInSpace){
			if(!CanHaveAsObjectInSpace(objectInSpace))
				return false;
			if(objectInSpace.getWorld()!=this)
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param objectInSpace
	 * @throws IllegalArgumentException
	 */
	public void AddObjectInSpace (ObjectInSpace objectInSpace) throws IllegalArgumentException{
		//if (!CanHaveAsObjectInSpace(objectInSpace))
			//throw new IllegalArgumentException("This is object can't be in the world");
		objectInSpace.setWorld(this);
		objectsInSpace.add(objectInSpace);
		addFirstCollision(objectInSpace);
	}
	
	/**
	 * 
	 * @param objectInSpace
	 * @throws IllegalArgumentException
	 */
	public void RemoveObjectInSpace (ObjectInSpace objectInSpace) throws NullPointerException , IllegalArgumentException {
		if (objectInSpace==null)
			throw new NullPointerException("not a valid object");
		if (!this.HasAsObjectInSpace(objectInSpace))
			throw new IllegalArgumentException("this object is not in the world");
		if (objectInSpace.getWorld()==null)
			objectsInSpace.remove(objectInSpace);
			removeFirstCollision(objectInSpace);
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	public Set<ObjectInSpace> getAllObjectsInSpace() {
		return new HashSet<ObjectInSpace>(objectsInSpace);
	}
	
	private final Set<ObjectInSpace> objectsInSpace = new HashSet<ObjectInSpace>(); 
	
	/**
	 * 
	 * @return
	 * @throws IllegalStateException
	 */
	public Set<Ship> getShips() throws IllegalStateException {
		if (isTerminated())
			throw new IllegalStateException("World is Terminated");
		Set<Ship> ships = new HashSet<Ship>();
		for (ObjectInSpace objectInSpace : objectsInSpace){
			if (objectInSpace instanceof Ship) 
				ships.add((Ship) objectInSpace);
		}
		return ships;
	}

	/**
	 * 
	 * @return
	 */
	public Set<Asteroid> getAsteroids(){
		if (isTerminated())
			throw new IllegalStateException("World is Terminated");
		Set<Asteroid> asteroids = new HashSet<Asteroid>();
		for (ObjectInSpace objectInSpace : objectsInSpace){
			if (objectInSpace instanceof Asteroid) 
				asteroids.add((Asteroid) objectInSpace);
		}
		return asteroids;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<Bullet> getBullets() {
		if (isTerminated())
			throw new IllegalStateException("World is Terminated");
		Set<Bullet> bullets = new HashSet<Bullet>();
		for (ObjectInSpace objectInSpace : objectsInSpace){
			if (objectInSpace instanceof Bullet) 
				bullets.add((Bullet) objectInSpace);
		}
		return bullets;
	}
	
	/**
	 * 
	 */
	public void Terminate(){
		if (!isTerminated()) {
			for (ObjectInSpace objectInSpace : getAllObjectsInSpace())
				objectInSpace.terminate();
			this.isTerminated = true;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isTerminated(){
		return isTerminated;
	}
	
	private boolean isTerminated = false;
	
	/**
	 * 
	 * @param objectInSpace
	 * @return
	 */
	public ObjectInSpace calculateFirstCollision(ObjectInSpace objectInSpace){
		ObjectInSpace firstCollision = null;
		double collisionTime = objectInSpace.getTimeToCollisionWithWorldWand();
		for (ObjectInSpace otherObject : getAllObjectsInSpace())
			if(objectInSpace.getTimeToCollision(otherObject)<collisionTime)
				firstCollision = otherObject;
		return firstCollision;
	}
		
	/**
	 * 
	 * @param objectInSpace
	 */
	public void addFirstCollision(ObjectInSpace objectInSpace){
		firstCollisions.put(objectInSpace, calculateFirstCollision(objectInSpace));
	}
	
	/**
	 * 
	 * @param objectInSpace
	 */
	public void removeFirstCollision(ObjectInSpace objectInSpace){
		for(ObjectInSpace otherObject : objectsInSpace){
			if(firstCollisions.get(otherObject) == objectInSpace)
				addFirstCollision(otherObject);
		}
		firstCollisions.remove(objectInSpace);
	}
	
	private final Map<ObjectInSpace,ObjectInSpace> firstCollisions = new HashMap<ObjectInSpace, ObjectInSpace>();
	
	/**
	 * 
	 * @param time
	 */
	public void evolve(double time){
		double timeToFirstCollision = 0.0;
		ObjectInSpace firstCollision = null;
		ObjectInSpace firstCollisionOther = null;
		for(ObjectInSpace objectInSpace: objectsInSpace){
			if(objectInSpace.getTimeToCollision(firstCollisions.get(objectInSpace))>timeToFirstCollision){
				timeToFirstCollision = objectInSpace.getTimeToCollision(firstCollisions.get(objectInSpace));
				firstCollision = objectInSpace;
				firstCollisionOther= firstCollisions.get(objectInSpace);
			}
		}
		if(time > timeToFirstCollision){
			for(ObjectInSpace objectInSpace: objectsInSpace){
				objectInSpace.move(timeToFirstCollision-Util.EPSILON);
				if(objectInSpace instanceof Ship && ((Ship) objectInSpace).checkIfThrustIsEnabled())
					((Ship) objectInSpace).thrust(timeToFirstCollision-Util.EPSILON);
					addFirstCollision(objectInSpace);
			}
			firstCollision.collide(firstCollisionOther);
			if(!firstCollision.isTerminated()){
				addFirstCollision(firstCollision);
			}
			if(!firstCollisionOther.isTerminated() && firstCollisionOther!= null){
				addFirstCollision(firstCollisionOther);
			}
			double newTime = time - timeToFirstCollision;
			evolve(newTime);
		}
		for(ObjectInSpace objectInSpace: objectsInSpace){
			objectInSpace.move(time);
			if(objectInSpace instanceof Ship && ((Ship) objectInSpace).checkIfThrustIsEnabled()){
				((Ship) objectInSpace).thrust(time);
				addFirstCollision(objectInSpace);
			}
		}
	}

	

}


