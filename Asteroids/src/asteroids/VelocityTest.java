package asteroids;

import static org.junit.Assert.*;


import org.junit.Before;

import org.junit.Test;

public class VelocityTest {

	private Velocity velocity;
	
	/**
	 * @post variable velocity with velocityX set to 40
	 * 		 and velocityY set to 30
	 */
	@Before
	public void setUp()  {
		velocity = new Velocity(40, 30);
	}

	@Test
	public final void deaultConstructor_SingleCase() {
		Velocity newVelocity = new Velocity();
		assertEquals(0, newVelocity.getVelocityX(), Util.EPSILON);
		assertEquals(0, newVelocity.getVelocityY(),Util.EPSILON);
	}
	
	@Test
	public final void constructor_SingleCase() {
		Velocity newVelocity = new Velocity(10, 20);
		assertEquals(10, newVelocity.getVelocityX(), Util.EPSILON);
		assertEquals(20, newVelocity.getVelocityY(),Util.EPSILON);
	}

	@Test
	public final void setVelocity_TrueCase() {
		velocity.setVelocity(50, 50);
		assertEquals(50, velocity.getVelocityX(), Util.EPSILON);
		assertEquals(50, velocity.getVelocityY(),Util.EPSILON);
	}
	
	@Test
	public final void setVelocity_MoreThanSpeedLimitCase() {
		velocity.setVelocity(400000, 550000);
		double direction = velocity.getDirection(400000, 550000);
		assertEquals(velocity.getSpeedLimit()*Math.cos(direction), velocity.getVelocityX(), Util.EPSILON);
		assertEquals(velocity.getSpeedLimit()*Math.sin(direction), velocity.getVelocityY(),Util.EPSILON);
	}
	
	@Test
	public final void setVelocity_VelocityXFalseCase() {
		velocity.setVelocity(Double.NaN, 50);
		assertEquals(0, velocity.getVelocityX(), Util.EPSILON);
		assertEquals(50, velocity.getVelocityY(),Util.EPSILON);
	}
	
	@Test
	public final void setVelocity_VelocityYFalseCase() {
		velocity.setVelocity(50, Double.NaN);
		assertEquals(50, velocity.getVelocityX(), Util.EPSILON);
		assertEquals(0, velocity.getVelocityY(),Util.EPSILON);
	}
	
	@Test
	public final void setVelocity_FalseCase() {
		velocity.setVelocity(Double.NaN, Double.NaN);
		assertEquals(0, velocity.getVelocityX(), Util.EPSILON);
		assertEquals(0, velocity.getVelocityY(),Util.EPSILON);
	}
}