package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

@Value
public class VectorInSpace {

	protected VectorInSpace(double xCoordinate, double yCoordinate) {
		if (!isValidDouble(xCoordinate) ) {
			xCoordinate =0;
		}
		if( !isValidDouble(yCoordinate)) {
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

	public VectorInSpace changeVector(double xCoordinate, double yCoordinate) {
		return new VectorInSpace(xCoordinate, yCoordinate);
	}

	public VectorInSpace addToVector(double addXCoordinate,
			double addYCoordinate) throws IllegalArgumentException {
		if (!isValidDouble(addXCoordinate) || !isValidDouble(addYCoordinate)) {
			throw new IllegalArgumentException(
					"the number must be a valid double");
		}
		return new VectorInSpace(getXCoordinate() + addXCoordinate,
				getYCoordinate() + addYCoordinate);
	}

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

	@Override
	public int hashCode() {
		return Double.valueOf(getXCoordinate()).hashCode()+Double.valueOf(getYCoordinate()).hashCode();
	}

	@Override
	public String toString() {
		return "[" + Double.toString(getXCoordinate()) + " " + Double.toString(getYCoordinate()) + "]";
	}
	
	public static double inProduct(VectorInSpace vector1, VectorInSpace vector2){
		return ((vector1.getXCoordinate() * vector2.getXCoordinate()) + (vector1.getYCoordinate() * vector2.getYCoordinate()));
	}
	
	public static double norm(VectorInSpace vector){
		return Math.hypot(vector.getXCoordinate(), vector.getYCoordinate());
	}
	
	public static VectorInSpace vectorChange(VectorInSpace vector1, VectorInSpace vector2){
		return new VectorInSpace(vector1.getXCoordinate()-vector2.getXCoordinate(), vector1.getYCoordinate() - vector2.getYCoordinate());
	}
}
