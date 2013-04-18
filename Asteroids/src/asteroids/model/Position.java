package asteroids.model;


import be.kuleuven.cs.som.annotate.*;

/** 
 * A class to create a position in a 2D setting with a x-coordinate and a y-coordinate in km  (where the x-axis is horizontal and the y-axis is vertical)
 * this class is a subclass of VectorInSpace class
 *  
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
@Value
public class Position extends VectorInSpace{
	
	/**
	 * Initialize a new position on default, the x- and y-coordinate are both zero
	 * 
	 */
	public Position() {
		super(0,0);
	}
	
	/**
	 * Initialize a new position with the given the x- and y-coordinate
	 * 
	 * @param xCoordinate
	 * 			 the x-coordinate of this position
	 * @param yCoordinate
	 * 			 the y-coordinate of this position
	 */
	public Position(double x, double y) {
		super(x,y);
	}
	
	/**
	 * Makes a new position with given x-coordinate and y-coordinate
	 * 
	 * @param xCoordinate
	 * 
	 * @param yCoordinate
	 * 
	 * @return a new position with given x-coordinate and y-coordinate
	 */
	@Override
	public Position changeVector(double xCoordinate, double yCoordinate) {
		return  new Position(xCoordinate, yCoordinate);
	}
	
}
