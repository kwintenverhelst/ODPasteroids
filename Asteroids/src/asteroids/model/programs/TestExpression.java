package asteroids.model.programs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestExpression {
	
	private ProgramFactoryImpl factory;
	
	/**
	 * @post variable velocity with velocityX set to 40
	 * 		 and velocityY set to 30
	 */
	@Before
	public void setUp()  {
		factory = new ProgramFactoryImpl();
		
	}

	@Test
	public final void NotExpressions_SingleCase() {
		Expression newExpression1 = factory.createBooleanLiteral(0, 0, true);
		assertTrue( (boolean) newExpression1.getValue());
		Expression newExpression2 = factory.createNot(0, 0, newExpression1);
		assertFalse( (boolean) newExpression2.getValue());
		Expression newExpression3 = factory.createNot(0, 0, newExpression2);
		assertTrue( (boolean) newExpression3.getValue());
	}
	
	@Test
	public final void AndExpressions_SingleCase() {
		Expression newExpression1 = factory.createBooleanLiteral(0, 0, true);
		Expression newExpression2 = factory.createBooleanLiteral(0, 0, true);
		Expression newExpression3 = factory.createBooleanLiteral(0, 0, false);
		Expression newExpression4 = factory.createBooleanLiteral(0, 0, false);
		
		Expression newExpression5 = factory.createAnd(0, 0, newExpression1, newExpression2);
		Expression newExpression6 = factory.createAnd(0, 0, newExpression1, newExpression3);
		Expression newExpression7 = factory.createAnd(0, 0, newExpression4, newExpression2);
		Expression newExpression8 = factory.createAnd(0, 0, newExpression3, newExpression4);
		
		assertTrue( (boolean) newExpression5.getValue());
		assertFalse( (boolean) newExpression6.getValue());
		assertFalse( (boolean) newExpression7.getValue());
		assertFalse( (boolean) newExpression8.getValue());
	
	}
	
	@Test
	public final void OrExpressions_SingleCase() {
		Expression newExpression1 = factory.createBooleanLiteral(0, 0, true);
		Expression newExpression2 = factory.createBooleanLiteral(0, 0, true);
		Expression newExpression3 = factory.createBooleanLiteral(0, 0, false);
		Expression newExpression4 = factory.createBooleanLiteral(0, 0, false);
		
		Expression newExpression5 = factory.createOr(0, 0, newExpression1, newExpression2);
		Expression newExpression6 = factory.createOr(0, 0, newExpression1, newExpression3);
		Expression newExpression7 = factory.createOr(0, 0, newExpression4, newExpression2);
		Expression newExpression8 = factory.createOr(0, 0, newExpression3, newExpression4);
		
		assertTrue( (boolean) newExpression5.getValue());
		assertTrue( (boolean) newExpression6.getValue());
		assertTrue( (boolean) newExpression7.getValue());
		assertFalse( (boolean) newExpression8.getValue());
	}
	
}
