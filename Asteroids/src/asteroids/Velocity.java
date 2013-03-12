package asteroids;

import be.kuleuven.cs.som.annotate.*;

public class Velocity {
	
	/**
	 * The velocity of this ship in the x direction in km/s
	 */
	private double velocityX;
	
	/**
	 * The velocity of this ship in the y direction in km/s
	 */
	private double velocityY;
	
	/**
	 * Variable for the speed limit of this ship in km/s.
	 */
	private final double speedLimit = 300000;
	
	public Velocity (){
		setVelocity(0, 0);
	}
	
	public Velocity (double velocityX, double velocityY){
		setVelocity(velocityX, velocityY);
	}
	/**
	 * Check if this number is a valid number.
	 * 
	 * @param number
	 *       	 The number in double format to verify.
	 * @return True if double is a valid number otherwise it is false.
	 *         |result!=Double.isNaN(number)
	 */
	private boolean isValidDouble(double number) {

		return !Double.isNaN(number);
	}

	/**
	 * Returns the velocity of this ship in the x direction in km/s.
	 */
	@Basic
	public double getVelocityX() {
		return this.velocityX;
	}


	/**
	 * Returns the velocity of this ship in the y direction in km/s.
	 */
	@Basic
	public double getVelocityY() {
		return this.velocityY;
	}

	/**
	 * Returns the speed limit of this ship in km/s.
	 */
	@Basic
	@Immutable
	public double getSpeedLimit() {
		return speedLimit;
	}

	/**
	 * Checks if the velocity is a valid number and if it is less or equal to
	 * the speed limit.
	 * 
	 * @param velocityX
	 *            velocity in the x direction in km/s
	 * @param velocityY
	 *            velocity in the y direction in km/s
	 * @effect Returns true if the velocity is a valid number and if it is less
	 *         or equal to the speed limit.
	 *         |Util.fuzzyLessThanOrEqualTo(getVelocity(velocityX, velocityY),
	 *         getSpeedLimit())
	 */
	private boolean isValidVelocity(double velocityX, double velocityY) {
		return Util.fuzzyLessThanOrEqualTo(getVelocity(velocityX, velocityY),
				getSpeedLimit());
	}

	/**
	 * Calculates the norm of the given velocityX and velocityY in km/s.
	 * 
	 * @param velocityX
	 *            velocity in the x direction in km/s
	 * @param velocityY
	 *            velocity in the y direction in km/s
	 * @return The square root of the sum of powers of velocityX and velocityY
	 *         |result == Math.hypot(velocityX, velocityY).
	 */
	private double getVelocity(double velocityX, double velocityY) {
		return Math.hypot(velocityX, velocityY);
	}

	/**
	 * Changes the velocity of this ship in the given x and y direction.
	 * 
	 * @param velocityX
	 *            velocity in the x direction in km/s
	 * @param velocityY
	 *            velocity in the y direction in km/s
	 * @post If the given velocity is less than or equal to the speed limit, the
	 *       new velocity of this ship is equal to given velocity.
	 *       |if(isValidVelocity(velocityX, velocityY)) 
	 *       | then new.getVelocityX()=this.getVelocityX() 
	 *       | &&   new.getVelocityY()=this.getVelocityY()
	 * @post If the given velocity is greater than the speed limit, the new
	 *       velocity of this ship is equal the speed limit, the velocity in x
	 *       direction is the velocity times the cosine of the direction, the
	 *       velocity in y direction is the velocity times the sine of the
	 *       direction. 
	 *       |if!(isValidVelocity(velocityX, velocityY))
	 *       | then new.getVelocityX()=this.getSpeedLimit()*Math.cos(this.getDirection())
	 *       | && new.getVelocityY()=this.getSpeedLimit()*Math.sin(this.getDirection())
	 * @effect if one or both of the velocities is not a number then that velocity
	 *       	(those velocities) are set on zero
	 *       	 |if (!isValidDouble(velocityY))
	 *      	 |  then setVelocity(velocityX, 0.0); 
	 *      	 |if (!isValidDouble(velocityX)) 
	 *      	 | then setVelocity(0.0,velocityY);
	 *      	 |if (!isValidDouble(velocityX) && !isValidDouble(velocityY))
	 *     	 	 |then setVelocity(0.0, 0.0)
	 */		 	
	public void setVelocity(double velocityX, double velocityY) {
		if (isValidDouble(velocityX) && isValidDouble(velocityY)) {
			if (isValidVelocity(velocityX, velocityY)) {
				this.velocityX = velocityX;
				this.velocityY = velocityY;
			} 
			else {
				double direction = this.getDirection(velocityX, velocityY);
				this.velocityX = this.getSpeedLimit() * Math.cos(direction);
				this.velocityY = this.getSpeedLimit() * Math.sin(direction);
			}
		} 
		else {
			if (!isValidDouble(velocityY)) {
				setVelocity(velocityX, 0.0);
			}
			else if (!isValidDouble(velocityX)) {
				setVelocity(0.0,velocityY);
			}
			else {
				setVelocity(0.0, 0.0);
			}
		}
	}

	/**
	 * returns the angle of the velocity, which has the components velocityX and
	 * velocityY
	 * 
	 * @param velocityX
	 *            The x component of the velocity
	 * @param velocityY
	 *            The y component of the velocity
	 * @return the angle of the velocity
	 * 		 	| result == Math.atan(velocityY/velocityX)
	 */
	public double getDirection(double velocityX, double velocityY) {
		return Math.atan(velocityY / velocityX);
	}
}
