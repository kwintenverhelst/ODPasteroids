package asteroids;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		assertEquals(0, newPosition.getX(), Util.EPSILON);
		assertEquals(0, newPosition.getY(),Util.EPSILON);
	}

	@Test
	public final void constructor_SingleCase() {
		Position newPosition = new Position(10, 20);
		assertEquals(10.0, newPosition.getX(), Util.EPSILON);
		assertEquals(20, newPosition.getY(),Util.EPSILON);
	}
	
	@Test
	public final void setX_TrueCase() {
		position.setX(10);
		assertEquals(10, position.getX(), Util.EPSILON);
	}
	
	@Test
	public final void setX_LessThenZeroCase() {
		position.setX(-10);
		assertEquals(screenSize.getWidth(), position.getX(), Util.EPSILON);
	}
	
	@Test
	public final void setX_OutOfScreenCase() {
		position.setX(50000);
		assertEquals(0, position.getX(), Util.EPSILON);
	}
	
	@Test(expected = NullPointerException.class)
	public final void setX_FalseCase() throws Exception{
		position.setX(Double.NaN);
	}
	
	
	@Test
	public final void setY_TrueCase() {
		position.setY(10);
		assertEquals(10, position.getY(), Util.EPSILON);
	}
	
	@Test
	public final void setY_LessThenZeroCase() {
		position.setY(-10);
		assertEquals(screenSize.getHeight(), position.getY(), Util.EPSILON);
	}
	
	@Test
	public final void setY_OutOfScreenCase() {
		position.setY(50000);
		assertEquals(0, position.getY(), Util.EPSILON);
	}
	
	@Test(expected = NullPointerException.class)
	public final void setY_FalseCase() throws Exception{
		position.setY(Double.NaN);
	}
	

}
