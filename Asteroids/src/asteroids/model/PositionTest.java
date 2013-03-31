package asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Util;

public class PositionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	private Position position;
	
	
	/**
	 * @post variable position with x coordinate set to 20
	 * 		 and y coordinate set to 20
	 */
	@Before
	public void setUp()  {
		position = new Position(20, 20);
	}
	
	@Test
	public final void defaultConstructor_SingleCase() {
		Position newPosition = new Position();
		assertEquals(0, newPosition.getXCoordinate(), Util.EPSILON);
		assertEquals(0, newPosition.getYCoordinate(),Util.EPSILON);
	}

	@Test
	public final void constructor_SingleCase() {
		Position newPosition = new Position(10, 20);
		assertEquals(10, newPosition.getXCoordinate(), Util.EPSILON);
		assertEquals(20, newPosition.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVectorX_TrueCase() {
		position = position.changeVector(10, 0);
		assertEquals(10, position.getXCoordinate(), Util.EPSILON);
	}
	
	@Test
	public final void changeVectorY_TrueCase() {
		position = position.changeVector(0,10);
		assertEquals(10, position.getYCoordinate(), Util.EPSILON);
	}
	
	
	@Test
	public final void vectorChange_TrueCase() {
		Position newPosition1 = new Position(1000, 500);
		Position newPosition2 = new Position(250, 250);
		VectorInSpace newPosition3 = Position.vectorChange(newPosition1, newPosition2);

		assertEquals(750, newPosition3.getXCoordinate(),Util.EPSILON);
		assertEquals(250, newPosition3.getYCoordinate(),Util.EPSILON);
	}

}
