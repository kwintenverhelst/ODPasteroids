package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create a vector in a 2D setting and with a x-coordinate
 * and a y-coordinate
 * 
 * @invar the x-coordinate and the y-coordinate doubles must be valid numbers.
 * 		| isValidDouble(getXCoordinate()()) && isValidDouble(getYCoordinate()())
 * 
 * @version 1.1
 * @author Mathieu Vermeire en Kwinten Verhelst
 */
@Value
public class VectorInSpace {

	/**
	 * Initialize a new vector with the given the x- and y-coordinate
	 * 
	 * @param xCoordinate
	 * 			 the x-coordinate of this vector
	 * @param yCoordinate
	 * 			 the y-coordinate of this vector
	 */
	protected VectorInSpace(double xCoordinate, double yCoordinate) {
		if (!isValidDouble(xCoordinate)) {
			xCoordinate = 0;
		}
		if (!isValidDouble(yCoordinate)) {
			yCoordinate = 0;
		}
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	/**
	 * variable registering the xCoordinate of the vector
	 * 
	 */
	private final double xCoordinate;

	/**
	 * variable registering the yCoordinate of the vector
	 * 
	 */
	private final double yCoordinate;

	/**
	 * return the xCoordinate of the vector
	 * 
	 */
	@Basic
	@Immutable
	public double getXCoordinate() {
		return xCoordinate;
	}

	/**
	 * return the xCoordinate of the vector
	 * 
	 */
	@Basic
	@Immutable
	public double getYCoordinate() {
		return yCoordinate;
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
	 * Makes a new vector with given x-coordinate and y-coordinate
	 * 
	 * @param xCoordinate
	 * 
	 * @param yCoordinate
	 * 
	 * @return a new vector with given x-coordinate and y-coordinate
	 */
	public VectorInSpace changeVector(double xCoordinate, double yCoordinate) {
		return new VectorInSpace(xCoordinate, yCoordinate);
	}

	
	/**
	 * Check whether this vector is equal to the given object
	 * 
	 * @return True if and only if the given object is effective. if this vector
	 *         and the given object belong to the same class and if this vector
	 *         and the other object interpreted as a vector have equal
	 *         x-coordinate and y-coordinate
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		VectorInSpace otherVectorInSpace = (VectorInSpace) other;
		return Util.fuzzyEquals(this.getXCoordinate(),
				otherVectorInSpace.getXCoordinate())
				&& Util.fuzzyEquals(this.getYCoordinate(),
						otherVectorInSpace.getYCoordinate());
	}

	/**
	 * Returns the hash code for this vector
	 */
	@Override
	public int hashCode() {
		return Double.valueOf(getXCoordinate()).hashCode()
				+ Double.valueOf(getYCoordinate()).hashCode();
	}

	/**
	 * Returns a textual representation of this vector
	 * 
	 * @return a textual representation of this vector
	 */
	@Override
	public String toString() {
		return "<" + Double.toString(getXCoordinate()) + ","
				+ Double.toString(getYCoordinate()) + ">";
	}

	/**
	 * returns the value of the inProduct between two given vectors
	 * 
	 * @param vector1
	 * 			the first vector that you want in the inProduct
	 * @param vector2
	 * 			the second vector that you want in the inProduct
	 * @return he value of the inProduct between two vectors
	 */
	public static double inProduct(VectorInSpace vector1, VectorInSpace vector2) {
		return ((vector1.getXCoordinate() * vector2.getXCoordinate()) + (vector1
				.getYCoordinate() * vector2.getYCoordinate()));
	}

	/**
	 * returns the norm of the given vector, the norm is length of a vector
	 * 
	 * @param vector
	 * 			the vector from which you want to know the norm
	 * @return the norm of the given vector
	 */
	public static double norm(VectorInSpace vector) {
		return Math.hypot(Math.abs(vector.getXCoordinate()), Math.abs(vector.getYCoordinate()));
	}

	/**
	 * returns the angle of the given vector and the positive x-as
	 * 
	 * @param vector
	 * 		the vector from which you want to know the angle with the positive x-as 
	 * 
	 * @return the angle of the vector 
	 * 			| result == Math.atan(getYCoordinate() / getXCoordinate())
	 */
	public static double getDirection(VectorInSpace vector) {
		return Math.atan( vector.getYCoordinate() / vector.getXCoordinate());
	}
	
	/**
	 * returns the subtract of the second given vector from the first given vector
	 * 
	 * @param vector1
	 * 			the vector from which you want to subtract the other vector
	 * @param vector2
	 * 			the vector that you subtract from the first
	 * @return the subtract of the second given vector from the first given vector
	 * 			
	 */
	public static VectorInSpace vectorChange(VectorInSpace vector1,
			VectorInSpace vector2) {
		return new VectorInSpace(vector1.getXCoordinate()
				- vector2.getXCoordinate(), vector1.getYCoordinate()
				- vector2.getYCoordinate());
	}
}
