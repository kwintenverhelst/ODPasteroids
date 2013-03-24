package asteroids.model;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Util;

public class PositionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	private Position position;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
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
		assertEquals(10.0, newPosition.getXCoordinate(), Util.EPSILON);
		assertEquals(20, newPosition.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVectorX_TrueCase() {
		position.changeVector(10, 0);
		assertEquals(10, position.getXCoordinate(), Util.EPSILON);
	}
	
	@Test
	public final void changeVectorX_LessThenZeroCase() {
		position.changeVector(-10, 0);
		assertEquals(screenSize.getWidth(), position.getXCoordinate(), Util.EPSILON);
	}
	
	@Test
	public final void changeVectorX_OutOfScreenCase() {
		position.changeVector(50000, 0);
		assertEquals(0, position.getXCoordinate(), Util.EPSILON);
	}
	
	@Test(expected = NullPointerException.class)
	public final void changeVectorX_FalseCase() throws Exception{
		position.changeVector(Double.NaN, 0);
	}
	
	
	@Test
	public final void changeVectorY_TrueCase() {
		position.changeVector(0,10);
		assertEquals(10, position.getYCoordinate(), Util.EPSILON);
	}
	
	@Test
	public final void changeVectorY_LessThenZeroCase() {
		position.changeVector(0,-10);
		assertEquals(screenSize.getHeight(), position.getYCoordinate(), Util.EPSILON);
	}
	
	@Test
	public final void changeVectorY_OutOfScreenCase() {
		position.changeVector(0,50000);
		assertEquals(0, position.getYCoordinate(), Util.EPSILON);
	}
	
	@Test(expected = NullPointerException.class)
	public final void changeVectorY_FalseCase() throws Exception{
		position.changeVector(0,Double.NaN);
	}
	

}
