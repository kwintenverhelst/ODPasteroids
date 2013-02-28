package asteroids;

public class Ship implements IShip {
	

    /**
     * 
     * @return
     */
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
	 * 
	 * @return
	 */
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



