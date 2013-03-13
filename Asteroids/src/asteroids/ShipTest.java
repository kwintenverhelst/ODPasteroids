package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShipTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	private Ship ship;
	
	@Before
	public void setUp() throws Exception {
		ship = new Ship(20, 30, 50, 40, 15, 30);
	}
	
	@Test
	public final void constructor_TrueCase() {
		Ship newShip = new Ship(20, 30, 50, 40, 15, 30);
		assertEquals(20, newShip.getX(), Util.EPSILON);
		assertEquals(30, newShip.getY(), Util.EPSILON);
		assertEquals(50, newShip.getVelocityX(), Util.EPSILON);
		assertEquals(40, newShip.getVelocityY(), Util.EPSILON);
		assertEquals(15, newShip.getRadius(), Util.EPSILON);
		assertEquals(30, newShip.getAngle(), Util.EPSILON);
	}
		
	
	@Test(expected = IllegalArgumentException.class)
	public final void constructor_IllegalArgumentCase() throws Exception{
		new Ship(20, 30, 50, 40, 5, 30);
	}
	
	@Test(expected = NullPointerException.class)
	public final void constructor_NullPointerCase() throws Exception {
		new Ship(20, 30, 50, 40, Double.NaN, 30);
	}
	
	@Test
	public final void isValidDouble_TrueCase() {
		assertTrue(ship.isValidDouble(50));
	}

	@Test
	public final void isValidDouble_FalseCase() {
		assertFalse(ship.isValidDouble(Double.NaN));
	}
	
	@Test
	public final void isValidAngle_TrueCase() {
		assertTrue(ship.isValidAngle(1));
	}

	@Test
	public final void isValidAngle_NegativeCase() {
		assertFalse(ship.isValidAngle(-10));
	}
	
	@Test
	public final void isValidAngle_MoreThan2PiCase() {
		assertFalse(ship.isValidAngle(10));
	}
	
	@Test
	public final void isValidAngle_FalseCase() {
		assertFalse(ship.isValidAngle(Double.NaN));
	}
	
	@Test
	public final void isValidRadius_TrueCase() {
		assertTrue(ship.isValidRadius(50));
	}
	
	@Test
	public final void isValidRadius_LessThanMinimalRadiusCase() {
		assertFalse(ship.isValidRadius(9));
	}

	@Test
	public final void isValidRadius_FalseCase() {
		assertFalse(ship.isValidRadius(Double.NaN));
	}
	
	@Test
	public final void isValidTime_TrueCase() {
		assertTrue(ship.isValidTime(20));
	}

	@Test
	public final void isValidTime_FalseCase() {
		assertFalse(ship.isValidTime(-1));
	}
	
	@Test
	public final void isValidThrust_TrueCase() {
		assertTrue(ship.isValidThrust(20));
	}

	@Test
	public final void isValidThrust_FalseCase() {
		assertFalse(ship.isValidThrust(-1));
	}
	
	@Test
	public final void move_TrueCase() {
	    Ship newShip = new Ship(100, 100, 30, -15, 20, 0);
	    newShip.move(1);
	    assertEquals(130, newShip.getX(), Util.EPSILON);
	    assertEquals(85, newShip.getY(), Util.EPSILON);
	}

	@Test(expected = NullPointerException.class)
	public final void move_NullPointerCase() {
		ship.move(Double.NaN);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void move_IllegalArgumentCase() {
		ship.move(-10);
	}
	
	@Test
	public final void thrust_TrueCase() {
		Ship newShip = new Ship(100, 100, 30, -15, 20, 0);
	    newShip.thrust(5);
	    assertEquals(35, newShip.getVelocityX(), Util.EPSILON);
	    assertEquals(-15, newShip.getVelocityY(), Util.EPSILON);
	}

	@Test
	public final void thrust_NullPointerCase() {
		Ship newShip = new Ship(100, 100, 30, -15, 20, 0);
	    newShip.thrust(Double.NaN);
	    assertEquals(30, newShip.getVelocityX(), Util.EPSILON);
	    assertEquals(-15, newShip.getVelocityY(), Util.EPSILON);
	}
	
	@Test
	public final void thrust_IllegalArgumentCase() {
		Ship newShip = new Ship(100, 100, 30, -15, 20, 0);
	    newShip.thrust(-10);
	    assertEquals(30, newShip.getVelocityX(), Util.EPSILON);
	    assertEquals(-15, newShip.getVelocityY(), Util.EPSILON);
	}
	
	@Test
	public final void turn_SingleCase() {
		Ship newShip = new Ship(100, 100, 30, -15, 20, 0);
	    newShip.setAngle(20);
	    assertEquals(20, newShip.getAngle(), Util.EPSILON);
	}

	@Test
	public final void getDistanceBetween_TrueCase() {
		Ship newShip = new Ship(16, 27, 30, -15, 20, 0);
	    assertEquals(-30, ship.getDistanceBetween(newShip), Util.EPSILON);
    }

	@Test(expected = NullPointerException.class)
	public final void getDistanceBetween_NullPointerCase() {
		ship.getDistanceBetween(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void getDistanceBetween_IllegalArgumentCase() {
		ship.getDistanceBetween(ship);
	}
	
	@Test
	public final void overlap_TrueCase() {
		Ship newShip = new Ship(30, 30, 50, 40, 15, 0);
		assertTrue(ship.overlap(newShip));
	}
	
	@Test
	public final void overlap_OtherTrueCase() {
		assertTrue(ship.overlap(ship));
	}

	@Test
	public final void overlap_FalseCase() {
		Ship newShip = new Ship(100, 100, 30, -15, 20, 0);
		assertFalse(ship.overlap(newShip));
	}
	
	@Test
	public final void getTimeToCollision_TrueCase() {
		Ship newShip1 = new Ship(10, 10, 0, 0, 10, 0);
		Ship newShip2 = new Ship(30, 40, -15, -20, 10, 0);
		assertEquals(2.23598995, newShip1.getTimeToCollision(newShip2), Util.EPSILON);	
	}
	
	@Test
	public final void getTimeToCollision_InfiniteCase() {
		Ship newShip = new Ship(20, 30, 50, 40, 15, 30);
		assertEquals(Double.POSITIVE_INFINITY, ship.getTimeToCollision(newShip), Util.EPSILON);
		}

	@Test(expected = NullPointerException.class)
	public final void getTimeToCollision_FalseCase() {
		ship.getTimeToCollision(null);
	}
	
	@Test
	public final void getCollisionPosition_TrueCase() {
		Ship newShip1 = new Ship(10, 10, 0, 0, 10, 0);
		Ship newShip2 = new Ship(30, 40, -15, -20, 10, 0);
		double[] collisionPoints = new double[2];
		collisionPoints[0] = 10;
		collisionPoints[1] = 10;
		assertArrayEquals(collisionPoints, newShip1.getCollisionPosition(newShip2),Util.EPSILON);
		
	}
	

	@Test(expected = NullPointerException.class)
	public final void getCollisionPosition_NullPointerCase() {
		ship.getCollisionPosition(null);
	}

	@Test
	public final void getCollisionPosition_InfiniteCase() {
		Ship newShip = new Ship(20, 30, 50, 40, 15, 30);
		assertNull(ship.getCollisionPosition(newShip));
	}
	
	
	
	
	
}
