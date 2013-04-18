package asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;

public class WorldTest {

	private World world1;
	
	@Before
	public void setUp() throws Exception {
		world1 = new World(1000, 1500);
		
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
}
