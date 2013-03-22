package asteroids.model;


import be.kuleuven.cs.som.annotate.*;

/** 
 * A class to create a position in a 2D setting with a x-coordinate and a y-coordinate in km
 * 
 * @invar x-coordinate, y-coordinate doubles must be valid numbers.
 * 		| isValidDouble(getX()) && isValidDouble(getY())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
@Value
public class Position extends VectorInSpace{
	
	public Position() {
		super(0,0);
	}
	
	/**
	 * Initialize a new position on default, the x- and y-coordinate are both zero
	 * 
	 * @effect the x- and y-coordinate are both zero
	 * 			|setX(0) && setY(0)
	 */
	public Position(double x, double y) {
		super(x,y);
	}
		
	public static VectorInSpace vectorChange(VectorInSpace vector1,
			VectorInSpace vector2) {
		return VectorInSpace.vectorChange(vector1, vector2);
	}



}
