package asteroids.model;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;

public class WorldTest {

	private Ship ship1;
	
	private Ship ship2;
	
	private Ship ship3;
	
	private Asteroid asteroid;
	
	private World world1;
	
	private World world2;
	
	private Bullet bullet1;
	
	private Bullet bullet2;

	
	@Before
	public void setUp() throws Exception {
		ship1 = new Ship(20, 30, 50, 40, 15, 5E15, 0);
		ship2 = new Ship(500, 800, -50, -40, 15, 5E15, Math.PI);
		ship3 = new Ship(500, 800, 0, 0, 15, 5E15, Math.PI);
		world1 = new World(1280, 720);
		world2 = new World(1280, 720);
		asteroid = new Asteroid(100, 200, 50, 40, 20);
		
		world1.addObjectInSpace(ship1);
		world1.addObjectInSpace(ship2);
		world2.addObjectInSpace(ship3);
		world1.addObjectInSpace(asteroid);
		
		bullet1 = ship1.firebullet();
		bullet2 = ship2.firebullet();
		
	}

	@Test
	public final void constructor_TrueCase() {
		World newWorld = new World(250, 500);
		assertEquals(250.0, newWorld.getHeight(), Util.EPSILON);
		assertEquals(500.0, newWorld.getWidth(), Util.EPSILON);
		assertFalse(newWorld.isTerminated());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void constructor_IllegalArgumentHeightCase() {
		new World(-250, 500);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void constructor_IllegalArgumentWidthCase() {
		new World(250, Double.NaN);
	}
	
	@Test
	public final void isValidHeight_TrueCase() {
		assertTrue(world1.isValidHeight(500));
	}

	@Test
	public final void isValidHeight_LessThanZeroCase() {
		assertFalse(world1.isValidHeight(-10));
	}
	
		
	@Test
	public final void isValidHeight_NotANumberCase() {
		assertFalse(world1.isValidHeight(Double.NaN));
	}
	
	@Test
	public final void isValidWidth_TrueCase() {
		assertTrue(world1.isValidWidth(500));
	}

	@Test
	public final void isValidWidth_LessThanZeroCase() {
		assertFalse(world1.isValidWidth(-10));
	}
		
	@Test
	public final void isValidWidth_NotANumberCase() {
		assertFalse(world1.isValidWidth(Double.NaN));
	}
	
	@Test
	public final void canHaveAsObjectInSpace_TrueCase() {
		Ship ship4 = new Ship(30, 30, 20, 20, 20, 20, 0);
		assertTrue(world1.canHaveAsObjectInSpace(ship4));
	}
	
	@Test
	public final void canHaveAsObjectInSpace_FalseCase() {
		Ship ship4 = new Ship(15, 30, 20, 20, 20, 20, 0);
		ship2.die();
		assertFalse(world1.canHaveAsObjectInSpace(null));
		assertFalse(world1.canHaveAsObjectInSpace(ship4));
		assertFalse(world1.canHaveAsObjectInSpace(ship2));
	}
	
	@Test 
	public final void removeObjectInSpace_TrueCase() {
		ship1.die();
		world1.removeObjectInSpace(ship1);
		HashSet<Ship> ships = (HashSet<Ship>) world1.getShips();
		assertFalse(ships.contains(ship1));
		
	}
	
	@Test (expected = NullPointerException.class)
	public final void removeObjectInSpace_NullPointerExceptionCase() {
		world1.removeObjectInSpace(null);
		
	}
	
	@Test (expected = IllegalStateException.class)
	public final void removeObjectInSpace_IllegalStateExceptionCase() {
		world1.removeObjectInSpace(ship1);
	}
	
	@Test 
	public final void terminate_SingleCase() {
		world1.terminate();
		assertTrue(world1.isTerminated());
		assertTrue(ship1.isTerminated());
		assertFalse(ship3.isTerminated());
	}
}
