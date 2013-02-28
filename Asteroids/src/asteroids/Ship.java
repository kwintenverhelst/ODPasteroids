package asteroids;
import be.kuleuven.cs.som.annotate.*;

public class Ship implements IShip {
	

    /**
     * return the y coordinate of the this ship's position expressed in km
     */
	@ Basic
	public double getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y
	 * 
	 * @post  the new position y of the ship is equal to the given number
	 * 	     | new.getY() == y
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid number
	 * 		  | ! isValidDouble(y)
	 */
	public void setY(double y) throws IllegalArgumentException {
		if(! isValidDouble(y)) {
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
	


	private double y;
	
	/**
	 * return the x coordinate of the this ship's position expressed in km
	 */
	@ Basic
	public double getX() {
		return x;
	}

	/**
	 * @post  the new position y of the ship is equal to the given number
	 * 	     | new.getX() == x
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid number
	 * 		  | ! isValidDouble(x)
	 * @param x
	 */
	public void setX(double x) {
		if(! isValidDouble(x)) {
			throw new IllegalArgumentException();
		}
		this.x = x;
	}
	
	private double x;
	
	/**
	 * @param
	 * @return true if double is a number otherwise it is false 
	 * 			| result !=  Double.isNaN(number)
	 */
	public boolean isValidDouble(double number){
		
		if(Double.isNaN(number)){
			return false;
			
		}
		else {
			return true;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@ Basic
	public double getVelocityX() {
		return velocityX;
	}
	
	/**
	 * 
	 * @param velocityX
	 */
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	/**
	 * 
	 * @return
	 */
	@ Basic
	public double getVelocityY() {
		return velocityY;
	}

	/**
	 * 
	 * @param velocityY
	 */
	public void setVelocityY(double velocityY) {
		
		this.velocityY = velocityY;
	}


	private final double speedOfLight;

	public double getSpeedOfLight() {
		return speedOfLight;
	}


	private double velocityX;
	
	private double velocityY;

	}

}
	


