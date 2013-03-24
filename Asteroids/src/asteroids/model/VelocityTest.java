package asteroids.model;

import static org.junit.Assert.*;


import org.junit.Before;

import org.junit.Test;

import asteroids.Util;

public class VelocityTest {

	private Velocity velocity;
	
	/**
	 * @post variable velocity with velocityX set to 40
	 * 		 and velocityY set to 30
	 */
	@Before
	public void setUp()  {
		velocity = Velocity.createVelocity(40, 30);
	}

	@Test
	public final void deaultConstructor_SingleCase() {
		Velocity newVelocity = new Velocity();
		assertEquals(0, newVelocity.getXCoordinate(), Util.EPSILON);
		assertEquals(0, newVelocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void constructor_SingleCase() {
		Velocity newVelocity = Velocity.createVelocity(10, 20);
		assertEquals(10, newVelocity.getXCoordinate(), Util.EPSILON);
		assertEquals(20, newVelocity.getYCoordinate(),Util.EPSILON);
	}

	@Test
	public final void changeVector_TrueCase() {
		velocity.changeVector(50, 50);
		assertEquals(50, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(50, velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_MoreThanSpeedLimitCase() {
		Velocity velocity = (Velocity) this.velocity.changeVector(400000, 550000);
		double direction = VectorInSpace.getDirection(velocity);
		assertEquals(velocity.getSpeedLimit()*Math.cos(direction), velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(velocity.getSpeedLimit()*Math.sin(direction), velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_VelocityXFalseCase() {
		velocity.changeVector(Double.NaN, 50);
		assertEquals(0, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(50, velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_VelocityYFalseCase() {
		velocity.changeVector(50, Double.NaN);
		assertEquals(50, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(0, velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_FalseCase() {
		velocity.changeVector(Double.NaN, Double.NaN);
		assertEquals(0, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(0, velocity.getYCoordinate(),Util.EPSILON);
	}
}