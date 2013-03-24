package asteroids.model;

import java.util.Random;
import java.util.Set;

import asteroids.CollisionListener;
import asteroids.IFacade;

public class Facade implements IFacade<World, Ship, Asteroid, Bullet> {

	@Override
	public World createWorld(double width, double height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getWorldWidth(World world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWorldHeight(World world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Ship> getShips(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShip(World world, Ship ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeShip(World world, Ship ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evolve(World world, double dt,
			CollisionListener collisionListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double direction, double mass) {
		return new Ship(x, y, xVelocity, yVelocity, radius, mass, direction);
	}

	@Override
	public boolean isShip(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getShipX(Ship ship) {
		return ship.getX();
	}

	@Override
	public double getShipY(Ship ship) {
		return ship.getY();
	}

	@Override
	public double getShipXVelocity(Ship ship) {
		return ship.getVelocityX();
	}

	@Override
	public double getShipYVelocity(Ship ship) {
		return ship.getVelocityY();
	}

	@Override
	public double getShipRadius(Ship ship) {
		return ship.getRadius();
	}

	@Override
	public double getShipDirection(Ship ship) {
		return ship.getAngle();
	}

	@Override
	public double getShipMass(Ship ship) {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) {
		return null;
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) {
		return ship.checkIfThrustIsEnabled();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) {
		ship.setThrusterActive(active);
		
	}

	@Override
	public void turn(Ship ship, double angle) {
		ship.turn(angle);
	}

	@Override
	public void fireBullet(Ship ship) {
		ship.firebullet();
		
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) {
		return new Asteroid(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsteroid(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getAsteroidX(Asteroid asteroid) {
		return asteroid.getX();
	}

	@Override
	public double getAsteroidY(Asteroid asteroid) {
		return asteroid.getY();
	}

	@Override
	public double getAsteroidXVelocity(Asteroid asteroid) {
		return asteroid.getVelocityX();
	}

	@Override
	public double getAsteroidYVelocity(Asteroid asteroid) {
		return asteroid.getVelocityY();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBullets(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getBulletX(Bullet bullet) {
		return bullet.getX();
	}

	@Override
	public double getBulletY(Bullet bullet) {
		return bullet.getY();
	}

	@Override
	public double getBulletXVelocity(Bullet bullet) {
		return bullet.getVelocityX();
	}

	@Override
	public double getBulletYVelocity(Bullet bullet) {
		return bullet.getVelocityY();
	}

	@Override
	public double getBulletRadius(Bullet bullet) {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ship getBulletSource(Bullet bullet) {
		return bullet.getShip();
	}

}
