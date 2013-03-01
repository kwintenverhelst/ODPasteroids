package asteroids;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to create Spaceships for playing asteroids. 
 * A ship has 2D coordinates in km for its position, a velocity in km/s,
 * a radius and a direction.
 * The ships are able to move, turn and accelerate.
 * This class can also predict time and place of collision between 2 ships.
 * 
 * 
 * @version 1.0
 * @author Mathieu Vermeire
 * @author Kwinten Verhelst
 *
 */
public class Ship implements IShip {
	

    /**
     * Returns the y coordinate of the this ship's position expressed in km
     */
	@ Basic
	public double getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y
	 * 		  The y-coordinate of this ship in km.   	
	 * 
	 * @post  the new position y of this ship is equal to the given number.
	 * 	     | new.getY() == y
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid number.
	 * 		  | ! isValidDouble(y)
	 */
	public void setY(double y) throws IllegalArgumentException {
		if(! isValidDouble(y)) {
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
	/**
	 * Variable with the y coordinate of this ship in km.
	 */
	private double y;
	
	/**
	 *  Returns the x coordinate of the this ship's position expressed in km
	 */
	@ Basic
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 * 		  The x-coordinate of this ship in km.
	 * @post  the new position x of this ship is equal to the given number in km
	 * 	     | new.getX() == x
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid number
	 * 		  | ! isValidDouble(x)
	
	 */
	public void setX(double x) throws IllegalArgumentException {
		if(! isValidDouble(x)) {
			throw new IllegalArgumentException();
		}
		this.x = x;
	}
	
	/**
	 * Variable with the x coordinate of this ship in km.
	 */
	private double x;
	
	/**
	 * Check if this number is a valid number.
	 * 
	 * @param number
	 * 		  The number in double format to verify.
	 * @return True if double is a valid number otherwise it is false 
	 * 			| result !=  Double.isNaN(number)
	 */
	public boolean isValidDouble(double number){

		return !Double.isNaN(number);
	}
	
	/**
	 * Returns the velocity of this ship in the x direction in km/s.
	 */
	@ Basic
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * The velocity of this ship in the x direction in km/s
	 */
	private double velocityX;
	
	
	/**
	 * Returns the velocity of this ship in the y direction in km/s.
	 */
	@ Basic
	public double getVelocityY() {
		return this.velocityY;
	}

	/**
	 * The velocity of this ship in the y direction in km/s
	 */
	private double velocityY;
	
	/**
	 * Returns the speed limit of this ship in km/s.
	 */
	@Basic @Immutable
	public double getSpeedLimit() {
		return speedLimit;
	}

	/**
	 * Variable for the speed limit of this ship in km/s.
	 */
	private final double speedLimit = 300000;
	
	/**
	 * Checks if the velocity is a valid number and if it is less or equal to the speedlimit.
	 * 
	 * @param velocityX
	 * 		  velocity in the x direction in km/s
	 * @param velocityY
	 * 		  velocity in the y direction in km/s
	 * @effect Returns true if the velocity is a valid number and if it is less or equal to the speedlimit.
	 * 		   |Util.fuzzyLessThanOrEqualTo(getVelocity(velocityX, velocityY), getSpeedLimit())
	 */
	public boolean isValidVelocity(double velocityX, double velocityY){
		return Util.fuzzyLessThanOrEqualTo(getVelocity(velocityX, velocityY), getSpeedLimit());
	}
	
	/**
	 * Calculates the norm of the given velocityX and velocityY in km/s.
	 * 
	 * @param velocityX
	 * 		  velocity in the x direction in km/s
	 * @param velocityY
	 * 		  velocity in the y direction in km/s
	 * @return The square root of the sum of powers of velocityX and velocityY
	 * 		   | result == Math.sqrt((Math.pow(velocityX, 2) + Math.pow(velocityY, 2))
	 */		   
	public double getVelocity(double velocityX, double velocityY){
		return Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
	}
	
	/**
	 * Changes the velocity of this ship in the given x and y direction.
	 * 
	 * @param velocityX
	 * 		  velocity in the x direction  in km/s
	 * @param velocityY
	 * 		  velocity in the y direction in km/s
	 * @post  If the given velocity is less than or equal to the speed limit,
	 * 		  the new velocity of this ship is equal to given velocity.
	 * 		  |if(isValidVelocity(velocityX, velocityY))
	 *        | then new.getVelocityX()= this.getVelocityX() 
	 *        |		 &&  new.getVelocityY()= this.getVelocityY()
	 * @post  If the given velocity is greater than the speed limit,
	 * 		  the new velocity of this ship is equal the speed limit,
	 * 		  the velocity in x direction is the velocity times the cosine of the direction,
	 * 		  the velocity in y direction is the velocity times the sine of the direction.
	 * 		  |if!(isValidVelocity(velocityX, velocityY))
	 * 		  |  then new.getVelocityX()= this.getSpeedLimit()*Math.cos(this.getDirection())
	 *        |		 &&  new.getVelocityY()= this.getSpeedLimit()*Math.sin(this.getDirection()) 
	 */
	public void setVelocity(double velocityX, double velocityY){
		if(isValidVelocity(velocityX, velocityY)){
			this.velocityX= velocityX; 
			this.velocityY= velocityY;
		}
		else {
			this.velocityX= this.getSpeedLimit()*Math.cos(this.getDirection());
			this.velocityY= this.getSpeedLimit()*Math.cos(this.getDirection());		
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	public double getDirection(){
		return 0;
	}
}

	


