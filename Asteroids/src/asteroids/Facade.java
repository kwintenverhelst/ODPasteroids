package asteroids;

public class Facade implements IFacade {

	@Override
	public IShip createShip() {
		// TODO Auto-generated method stub
		return new Ship(200, 400, 0, 0, 50, 0);
	}

	@Override
	public IShip createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) {
		// TODO Auto-generated method stub
		return new Ship( x,  y,  xVelocity, yVelocity,  radius,  angle);
	}

	@Override
	public double getX(IShip ship) {
		// TODO Auto-generated method stub
		return ship.getX();
	}

	@Override
	public double getY(IShip ship) {
		// TODO Auto-generated method stub
		return ship.getY();
	}

	@Override
	public double getXVelocity(IShip ship) {
		// TODO Auto-generated method stub
		return ship.getVelocityX();
	}

	@Override
	public double getYVelocity(IShip ship) {
		// TODO Auto-generated method stub
		return ship.getVelocityY();
	}

	@Override
	public double getRadius(IShip ship) {
		// TODO Auto-generated method stub
		return ship.getRadius();
	}

	@Override
	public double getDirection(IShip ship) {
		// TODO Auto-generated method stub
		return ship.getAngle();
	}

	@Override
	public void move(IShip ship, double dt) {
		ship.move(dt);

	}

	@Override
	public void thrust(IShip ship, double amount) {
		ship.thrust(amount);

	}

	@Override
	public void turn(IShip ship, double angle) {
		ship.setAngle(getDirection(ship)+angle);

	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return null;
	}

}
