package asteroids.model;

import static org.junit.Assert.*;

import java.util.Random;


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
		
		Velocity newVelocity2 = Velocity.createVelocity(400000, 550000);
		double direction = VectorInSpace.getDirection(newVelocity2);
		assertEquals(newVelocity2.getSpeedLimit()*Math.cos(direction), newVelocity2.getXCoordinate(), Util.EPSILON);
		assertEquals(newVelocity2.getSpeedLimit()*Math.sin(direction), newVelocity2.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void constructor_RandomCase() {
		Random random = new Random();
		Velocity newVelocity = Velocity.createVelocityInRandomDirection(500, random);
		assertTrue(newVelocity.hasValidVelocity());
		assertEquals(500, Velocity.norm(newVelocity),Util.EPSILON);
		
		Velocity newVelocity2 = Velocity.createVelocity(400000, 550000);
		assertTrue(newVelocity2.hasValidVelocity());
		assertEquals(newVelocity2.getSpeedLimit(), Velocity.norm(newVelocity2),Util.EPSILON);
	}
	

	@Test
	public final void changeVector_TrueCase() {
		velocity = velocity.changeVector(50, 50);
		assertEquals(50, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(50, velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_MoreThanSpeedLimitCase() {
		velocity = velocity.changeVector(400000, 550000);
		double direction = VectorInSpace.getDirection(velocity);
		assertEquals(velocity.getSpeedLimit()*Math.cos(direction), velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(velocity.getSpeedLimit()*Math.sin(direction), velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_XMoreThanSpeedLimitCase() {
		velocity = velocity.changeVector(550000 ,500);
		double direction = VectorInSpace.getDirection(velocity);
		assertEquals(velocity.getSpeedLimit()*Math.cos(direction), velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(velocity.getSpeedLimit()*Math.sin(direction), velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_VelocityXFalseCase() {
		velocity = velocity.changeVector(Double.NaN, 50);
		assertEquals(0, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(50, velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_VelocityYFalseCase() {
		velocity = velocity.changeVector(50, Double.NaN);
		assertEquals(50, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(0, velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void changeVector_FalseCase() {
		velocity =  velocity.changeVector(Double.NaN, Double.NaN);
		assertEquals(0, velocity.getXCoordinate(), Util.EPSILON);
		assertEquals(0, velocity.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void vectorChange_TrueCase() {
		Velocity newVelocity1 = Velocity.createVelocity(1000, 500);
		Velocity newVelocity2 = Velocity.createVelocity(250, 250);
		VectorInSpace newVelocity3 =  Velocity.vectorChange(newVelocity1, newVelocity2);
		
		assertEquals(750, newVelocity3.getXCoordinate(),Util.EPSILON);
		assertEquals(250, newVelocity3.getYCoordinate(),Util.EPSILON);
	}
	
	@Test
	public final void getDirection_TrueCase() {
		velocity = velocity.changeVector(50, 50);
		assertEquals(Math.PI/4, Velocity.getDirection(velocity),Util.EPSILON);
	}
	
	@Test
	public final void norm_TrueCase() {
		velocity = velocity.changeVector(50, 50);
		assertEquals(50*Math.sqrt(2), Velocity.norm(velocity),Util.EPSILON);
	}
	
	@Test
	public final void inProduct_TrueCase() {
		Velocity newVelocity1 = Velocity.createVelocity(1000, 500);
		Velocity newVelocity2 = Velocity.createVelocity(250, 250);
		
		assertEquals(375000,  Velocity.inProduct(newVelocity1, newVelocity2),Util.EPSILON);
	}
}