package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

@Value
public class VectorInSpace {

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
	 * @return 
	 */
	@Override
	public String toString() {
		return "<" + Double.toString(getXCoordinate()) + ","
				+ Double.toString(getYCoordinate()) + ">";
	}

	public static double inProduct(VectorInSpace vector1, VectorInSpace vector2) {
		return ((vector1.getXCoordinate() * vector2.getXCoordinate()) + (vector1
				.getYCoordinate() * vector2.getYCoordinate()));
	}

	public static double norm(VectorInSpace vector) {
		return Math.hypot(Math.abs(vector.getXCoordinate()), Math.abs(vector.getYCoordinate()));
	}

	/**
	 * returns the angle of this vector
	 * 
	 * @return the angle of the vector | result ==
	 *         Math.atan(getYCoordinate() / getXCoordinate())
	 */
	public static double getDirection(VectorInSpace vector) {
		return Math.atan( vector.getYCoordinate() / vector.getXCoordinate());
	}
	
	public static VectorInSpace vectorChange(VectorInSpace vector1,
			VectorInSpace vector2) {
		return new VectorInSpace(vector1.getXCoordinate()
				- vector2.getXCoordinate(), vector1.getYCoordinate()
				- vector2.getYCoordinate());
	}
}
