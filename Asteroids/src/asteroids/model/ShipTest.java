package asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Util;

public class ShipTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	private Ship ship1;
	
	private Ship ship2;
	
	private Asteroid asteroid;
	
	private World world;

	@Before
	public void setUp() throws Exception {
		ship1 = new Ship(20, 30, 50, 40, 15, 5E15, Math.PI);
		ship2 = new Ship(500, 800, 50, 40, 15, 5E15, Math.PI);
		world = new World(1280, 720);
		asteroid = new Asteroid(100, 200, 50, 40, 20, world);
		ship1.setWorld(world);
		ship2.setWorld(world);
	}

	@Test
	public final void constructor_TrueCase() {
		Ship newShip = new Ship(20, 30, 50, 40, 15, 30, Math.PI);
		assertEquals(20, newShip.getX(), Util.EPSILON);
		assertEquals(30, newShip.getY(), Util.EPSILON);
		assertEquals(50, newShip.getVelocityX(), Util.EPSILON);
		assertEquals(40, newShip.getVelocityY(), Util.EPSILON);
		assertEquals(15, newShip.getRadius(), Util.EPSILON);
		assertEquals(30, newShip.getMass(), Util.EPSILON);
		assertEquals(Math.PI, newShip.getAngle(), Util.EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructor_IllegalArgumentCase() throws Exception {
		new Ship(20, 30, 50, 40, -5, 30, Math.PI);
	}

	@Test(expected = NullPointerException.class)
	public final void constructor_NullPointerCase() throws Exception {
		new Ship(20, 30, 50, 40, Double.NaN, 30, Math.PI);
	}

	@Test
	public final void isValidDouble_TrueCase() {
		assertTrue(ship1.isValidDouble(50));
	}

	@Test
	public final void isValidDouble_FalseCase() {
		assertFalse(ship1.isValidDouble(Double.NaN));
	}

	@Test
	public final void isValidAngle_TrueCase() {
		assertTrue(ship1.isValidAngle(1));
	}

	@Test
	public final void isValidAngle_NegativeCase() {
		assertFalse(ship1.isValidAngle(-10));
	}

	@Test
	public final void isValidAngle_MoreThan2PiCase() {
		assertFalse(ship1.isValidAngle(10));
	}

	@Test
	public final void isValidAngle_FalseCase() {
		assertFalse(ship1.isValidAngle(Double.NaN));
	}

	@Test
	public final void isValidRadius_TrueCase() {
		assertTrue(ship1.isValidRadius(50));
	}

	@Test
	public final void isValidRadius_LessThanMinimalRadiusCase() {
		assertFalse(ship1.isValidRadius(-1));
	}

	@Test
	public final void isValidRadius_FalseCase() {
		assertFalse(ship1.isValidRadius(Double.NaN));
	}

	@Test
	public final void isValidTime_TrueCase() {
		assertTrue(ship1.isValidTime(20));
	}

	@Test
	public final void setPosition_SingleCase() {
		ship1.setPosition(100, 200);
		assertEquals(100, ship1.getX(), Util.EPSILON);
		assertEquals(200, ship1.getY(), Util.EPSILON);

		ship1.setPosition(2000, 1500);
				
		assertEquals(705, ship1.getX(), Util.EPSILON);
		assertEquals(1265, ship1.getY(), Util.EPSILON);
		
		ship1.setPosition(0, 0);
		
		assertEquals(15, ship1.getX(), Util.EPSILON);
		assertEquals(15, ship1.getY(), Util.EPSILON);
	}

	@Test
	public final void setVelocity_SingleCase() {
		ship1.setVelocity(100, 200);
		assertEquals(100, ship1.getVelocityX(), Util.EPSILON);
		assertEquals(200, ship1.getVelocityY(), Util.EPSILON);

		ship1.setVelocity(1000000, 2000000);
		Velocity newVelocity = ship1.getVelocity();
		assertTrue(newVelocity.hasValidVelocity());
		
		ship1.setVelocity(-100, -200);
		assertEquals(-100, ship1.getVelocityX(), Util.EPSILON);
		assertEquals(-200, ship1.getVelocityY(), Util.EPSILON);

	}
	
	@Test
	public final void isValidTime_FalseCase() {
		assertFalse(ship1.isValidTime(-1));
	}
	
	@Test
	public final void turn_SingleCase() {
		ship1.turn(-Math.PI/2);
		assertEquals(Math.PI/2, ship1.getAngle(), Util.EPSILON);
	}

	@Test
	public final void thrust_TrueCase() {
		double velocityX = ship1.getVelocityX();
		double velocityY = ship1.getVelocityY();
		double thrust =   1.1*Math.pow(10, 18);
		
		
		ship1.setThrusterActive(true);
		ship1.thrust(5);
	    assertTrue(ship1.checkIfThrustIsEnabled());
	    assertTrue(ship1.isValidTime(5));
	    
	    
	    
	   assertEquals(velocityX +  (Math.cos(ship1.getAngle()) * Math.pow(5, 2)*thrust)/(ship1.getMass()), ship1.getVelocityX(), Util.EPSILON);
	   assertEquals(velocityY +  (Math.sin(ship1.getAngle()) * Math.pow(5, 2)*thrust)/(ship1.getMass()), ship1.getVelocityY(), Util.EPSILON);

	}

	@Test
	public final void thrust_FalseCase() {
		double velocityX = ship1.getVelocityX();
		double velocityY = ship1.getVelocityY();
		ship1.setThrusterActive(false);
	    ship1.thrust(5);
	    assertEquals(velocityX, ship1.getVelocityX(), Util.EPSILON);
	    assertEquals(velocityY, ship1.getVelocityY(), Util.EPSILON);
	}
	
	@Test
	public final void isShip_TrueCase() {
		assertTrue(Ship.isShip(ship1));
		assertTrue(Ship.isShip(ship2));
	}
	
	@Test
	public final void isShip_FalseCase() {
		assertFalse(Ship.isShip(asteroid));
	}

	@Test
	public final void getDistanceBetween_TrueCase() {
		Ship newShip = new Ship(16, 27, 30, -15, 20,30, 0);
	    assertEquals(-30, ship1.getDistanceBetween(newShip), Util.EPSILON);
    }
	
	@Test(expected = NullPointerException.class)
	public final void getDistanceBetween_NullPointerCase() {
		ship1.getDistanceBetween(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void getDistanceBetween_IllegalArgumentCase() {
		ship1.getDistanceBetween(ship1);
	}
	
	@Test
	public final void overlap_TrueCase() {
		Ship newShip1 = new Ship(20, 30, 50, 40, 15, 30, Math.PI);
		Ship newShip2 = new Ship(20, 30, 50, 40, 15, 30, Math.PI);
		assertTrue(newShip1.overlap(newShip2));
	}
	
	@Test
	public final void overlap_OtherTrueCase() {
		assertTrue(ship1.overlap(ship1));
	}

	@Test
	public final void overlap_FalseCase() {
		assertFalse(ship1.overlap(ship2));
	}
	
	@Test
	public final void getTimeToCollision_TrueCase() {
		Ship newShip1 = new Ship(200, 400, 1, 1, 50,30, 0);
		Ship newShip2 = new Ship(450, 600, 0, 0, 75,30, 0);
		assertEquals(140.2209, newShip1.getTimeToCollision(newShip2), Util.EPSILON);	
	}
	
	@Test
	public final void getTimeToCollision_InfiniteCase() {
		Ship newShip = new Ship(200, 300, 0, 500, 5,30, 30);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(newShip), Util.EPSILON);
		}

	@Test
	public final void getTimeToCollision_FalseCase() {
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(null), Util.EPSILON);
	}
	
	

	@Test(expected = NullPointerException.class)
	public final void getCollisionPosition_NullPointerCase() {
		ship1.getCollisionPosition(null);
	}

	@Test
	public final void getCollisionPosition_InfiniteCase() {
		Ship newShip = new Ship(20, 30, 50, 40, 15,30, 30);
		assertNull(ship1.getCollisionPosition(newShip));
	}

}

