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
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isValidY(){
		return true;			
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
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isValidX(){
		return true;			
	}
	
	private double x;
}



