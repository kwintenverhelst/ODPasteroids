package asteroids.model;

import java.awt.Dimension;
import java.awt.Toolkit;

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
	

	/**
	 * Variable with the size of your screen.
	 */
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Initialize a new position on default, the x- and y-coordinate are both zero
	 * 
	 * @effect the x- and y-coordinate are both zero
	 * 			|setX(0) && setY(0)
	 */
	public Position(){
		
		super(0,0);
	}
	
	/**
	 * Initialize a new position with the given the x- and y-coordinate
	 * @param x
	 * 			the x-coordinate of this position
	 * @param y
	 * 			the y-coordinate of this position
	 * @effect the x- and y-coordinate are the given the x- and y-coordinate
	 * 			|setX(x) && setY(y)
	 */
	public Position(double x, double y){
		super(x,y);
	}
	
	

	/**
	 * sets the x-coordinate to the given position,
	 * adjusted to your screen's width. 
	 * 
	 * @param x
	 *       	The x-coordinate in km.
	 * 
	 * @post if the given number lies between 0 and your screen's width 
	 * 		 then the new position x is equal to the given number.
	 *       | if (0< x < screenSize.getWidth())
	 *       | then new.getX() == x
	 * @post if the given number is smaller than 0
	 * 		  then the new position x is equal to your screen's width.
	 * 		 |if ( x < 0)
	 * 		 |then new.getX() == screenSize.getWidth()
	 * @post if the given number height than your screen's width
	 * 		  then the new position x is 0.
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
	 * sets the y-coordinate to the given position,
	 * adjusted to your screen's height. 
	 * 
	 * @param y
	 *     	  The y-coordinate in km.
	 * 
	 * @post if the given number lies between 0 and your screen's height 
	 * 		 then the new position y is equal to the given number.
	 *       | if (0< y < screenSize.getHeight())
	 *       | then new.getY() == y
	 * @post if the given number is smaller than 0
	 * 		  then the new position y is equal to your screen's height.
	 * 		 |if ( y < 0)
	 * 		 |then new.getY() == screenSize.getHeight()
	 * @post if the given number height than your screen's height
	 * 		  then the new position y is 0.
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
	

}
