package asteroids.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.antlr.v4.runtime.RecognitionException;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.ModelException;
import asteroids.model.programs.Expression;
import asteroids.model.programs.Program;
import asteroids.model.programs.ProgramFactoryImpl;
import asteroids.model.programs.Statement;
import asteroids.model.programs.Type;
import asteroids.model.programs.parsing.ProgramParser;

public class Facade implements IFacade<World, Ship, Asteroid, Bullet, Program> {

	public Facade() {

	}

	@Override
	public World createWorld(double width, double height) {
		return new World(height, width);
	}

	@Override
	public double getWorldWidth(World world) {
		return world.getWidth();
	}

	@Override
	public double getWorldHeight(World world) {
		return world.getHeight();
	}

	@Override
	public Set<Ship> getShips(World world) {
		return world.getShips();
	}

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		return world.getAsteroids();
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		return world.getBullets();
	}

	@Override
	public void addShip(World world, Ship ship) {
		try {
			world.addObjectInSpace(ship);
		} catch (IllegalArgumentException exc) {

		}
	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) {
		try {
			world.addObjectInSpace(asteroid);
		} catch (IllegalArgumentException exc) {

		}
	}

	@Override
	public void removeShip(World world, Ship ship) {
		world.removeObjectInSpace(ship);
	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		world.removeObjectInSpace(asteroid);

	}

	@Override
	public void evolve(World world, double dt,
			CollisionListener collisionListener) {
		world.evolve(dt, collisionListener);

	}

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double direction, double mass) {
		try {
			return new Ship(x, y, xVelocity, yVelocity, radius, mass, direction);
		} catch (IllegalArgumentException iExc) {
			throw new ModelException(iExc.getMessage());
		} catch (NullPointerException nExc) {
			throw new ModelException(nExc.getMessage());
		}
	}

	@Override
	public boolean isShip(Object o) {
		return Ship.isShip(o);
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
		return ship.getWorld();
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
		try {
			return new Asteroid(x, y, xVelocity, yVelocity, radius);
		} catch (IllegalArgumentException iExc) {
			throw new ModelException(iExc.getMessage());
		} catch (NullPointerException nExc) {
			throw new ModelException(nExc.getMessage());
		}

	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		return null;
	}

	@Override
	public boolean isAsteroid(Object o) {
		return Asteroid.isAsteroid(o);
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
		return asteroid.getWorld();
	}

	@Override
	public boolean isBullets(Object o) {
		return Bullet.isBullet(o);
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
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) {
		return bullet.getShip();
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> parseProgram(String text) {
		ProgramFactoryImpl factory = new ProgramFactoryImpl();
		ProgramParser<Expression, Statement, Type> parser = new ProgramParser<Expression, Statement, Type>(factory);
		try {
			parser.parse(text);
			List<String> errors = parser.getErrors();
			if (!errors.isEmpty()) {
				return ParseOutcome.failure(errors.get(0));
			} else {
				System.out.println(parser.getGlobals());
				System.out.println(parser.getStatement());
				return ParseOutcome.success(new Program(parser.getGlobals(),
						parser.getStatement()));
			}
		} catch (RecognitionException e) {
			return ParseOutcome.failure(e.getMessage());
		}
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromStream(
			InputStream stream) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromUrl(URL url)
			throws IOException {
		String text = "";
		try {
			URL programText = url;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					programText.openStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				text += "\n" + inputLine;
			}
			in.close();
		} catch (IOException e) {
			
		}
		return parseProgram(text);
	}

	@Override
	public boolean isTypeCheckingSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public asteroids.IFacade.TypeCheckOutcome typeCheckProgram(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShipProgram(Ship ship, Program program) {
		program.setEntity(ship);

	}

}
