package asteroids.model;

import java.util.Random;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Asteroid extends ObjectInSpace {

	public Asteroid() {
		this(0, 0, 0, 0, 1);
	}

	public Asteroid(double x, double y, double velocityX, double velocityY,
			double radius) {

		super(x, y, velocityX, velocityY, radius, 1);
		setMass(calculateMass(radius));
	}

	public Asteroid(double x, double y, double velocityX, double velocityY,
			double radius, World world) {
		super(x, y, velocityX, velocityY, radius, 1);
		setMass(calculateMass(radius));
		setWorld(world);
	}

	private final static double DENSITY = 7.8 * Math.pow(10, 12);

	/**
	 * Terminate this asteroid.
	 */
	public void die() {
		World thisWorld = this.getWorld();
		this.terminate();
		if (this.getRadius() >= 30) {
			Random random = new Random();
			double randomDouble = random.nextDouble();
			Velocity newVelocity = Velocity.createVelocityInRandomDirection(1.5 * Velocity.norm(Velocity.createVelocity(this.getVelocityX(), this.getVelocityY())),randomDouble);
			double newRadius = this.getRadius() / 2;
			double angle = 2 * Math.PI * randomDouble;
			
			Asteroid asteroid1 = new Asteroid( this.getX() + newRadius * Math.cos(angle) + Util.EPSILON, this.getY() + newRadius * Math.sin(angle) + Util.EPSILON,  newVelocity.getXCoordinate() + Util.EPSILON, newVelocity.getYCoordinate() + Util.EPSILON, newRadius, thisWorld);
			Asteroid asteroid2 = new Asteroid( this.getX() - newRadius * Math.cos(angle) - Util.EPSILON, this.getY() - newRadius * Math.sin(angle) - Util.EPSILON,  newVelocity.getXCoordinate() - Util.EPSILON, newVelocity.getYCoordinate() - Util.EPSILON, newRadius, thisWorld);
			
			thisWorld.addObjectInSpace(asteroid1);
			thisWorld.addObjectInSpace(asteroid2);
		}
	}

	/**
	 * Returns the density in km/km³.
	 */
	@Basic
	@Immutable
	public double getDensity() {
		return DENSITY;
	}

	public double calculateMass(double radius) {

		return 4 * Math.PI * Math.pow(radius, 3) * getDensity() / 3;
	}

	public static boolean isAsteroid(Object object) {
		return Asteroid.class.isAssignableFrom(object.getClass());
	}
}
