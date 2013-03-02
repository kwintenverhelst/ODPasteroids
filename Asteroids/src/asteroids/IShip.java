package asteroids;

/**
 * Classes for representing ships should implement <code>IShip</code>. Do not
 * modify this file.
 */
public interface IShip {
	public double getY();
	
	public double getX();
	
	public double getVelocityX();
	
	public double getVelocityY();
	
	public double getAngle();
	
	public double getRadius();
}
