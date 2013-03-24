package asteroids.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;


/**
 * 
 * @author Mathieu Vermeire & Kwinten Verhelst
 * @version	2.0
 *
 */

public class World {
	
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
	public boolean isValidHeight(){
		return (height >= 0 && height <= heightLimit);
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
	public boolean isValidWidth(){
		return (width >= 0 && width <= widthLimit);
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
		 if(!objectInSpace.liesInWorld())
			 return false;
		 for (ObjectInSpace otherInSpace: objectsInSpace){
			 if(objectInSpace.overlap(otherInSpace))
				 return false;
		 }
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
		if (!CanHaveAsObjectInSpace(objectInSpace))
			throw new IllegalArgumentException("This is object can't be in the world");
		if (!(objectInSpace.getWorld()==this||objectInSpace.getWorld()==null))
			throw new IllegalArgumentException("The object belongs to another world");
		objectsInSpace.add(objectInSpace);
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
	

	
	public void evolve(){
		//TODO
	}
	
	public void Terminate(){
		//TODO
	}
	
	public boolean isTerminated(){
		return isTerminated;
	}
	
	private boolean isTerminated = false;
		


}

