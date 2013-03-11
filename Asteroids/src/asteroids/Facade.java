package asteroids;

public class Facade implements IFacade {

	public IShip createShip() {
		return new Ship(0, 0, 0, 0, 50, 0);
	}

	public IShip createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) {
		return new Ship( x,  y,  xVelocity, yVelocity,  radius,  angle);
	}

	public double getX(IShip ship) {
		return ship.getX();
	}

	public double getY(IShip ship) {
		return ship.getY();
	}

	public double getXVelocity(IShip ship) {
		return ship.getVelocityX();
	}

	public double getYVelocity(IShip ship) {
		return ship.getVelocityY();
	}

	public double getRadius(IShip ship) {
		return ship.getRadius();
	}

	public double getDirection(IShip ship) {
		return ship.getAngle();
	}

	public void move(IShip ship, double dt) {
		ship.move(dt);

	}

	public void thrust(IShip ship, double amount) {
		ship.thrust(amount);

	}

	public void turn(IShip ship, double angle) {
		ship.turn(angle);

	}


	public double getDistanceBetween(IShip ship1, IShip ship2) {
		return ship1.getDistanceBetween(ship2);
	}


	public boolean overlap(IShip ship1, IShip ship2) {
		return ship1.overlap(ship2);
	}


	public double getTimeToCollision(IShip ship1, IShip ship2) {
		return ship1.getTimeToCollision(ship2);
	}

	
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		return ship1.getCollisionPosition(ship2);
	}

}
