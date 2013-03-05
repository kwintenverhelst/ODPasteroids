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
	
	public void thrust(double amount);
	
	public void move (double dt);
	
	public void setAngle(double angle);
	
	public double getDistanceBetween(IShip ship);
	
	public boolean overlap(IShip ship);

	public double getTimeToCollision(IShip ship2);

	public double[] getCollisionPosition(IShip ship2);
	
}
