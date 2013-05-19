package asteroids.model.programs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestExpression {
	
	/**
	 * @post variable velocity with velocityX set to 40
	 * 		 and velocityY set to 30
	 */
	@Before
	public void setUp()  {
	}

	@Test
	public final void NotExpressions_SingleCase() {
		Expression newExpression1 = new BooleanLiteral(0, 0, true);
		assertTrue( (boolean) newExpression1.getValue());
		Expression newExpression2 = new NotExpression(newExpression1, 0, 0);
		assertFalse( (boolean) newExpression2.getValue());
		Expression newExpression3 = new NotExpression(newExpression2, 0, 0);
		assertTrue( (boolean) newExpression3.getValue());
	}
	
	@Test
	public final void AndExpressions_SingleCase() {
		Expression newExpression1 = new BooleanLiteral(0, 0, true);
		Expression newExpression2 = new BooleanLiteral(0, 0, true);
		Expression newExpression3 = new BooleanLiteral(0, 0, false);
		Expression newExpression4 = new BooleanLiteral(0, 0, false);
		
		Expression newExpression5 = new AndExpression(newExpression1, newExpression2, 0, 0);
		Expression newExpression6 = new AndExpression(newExpression1, newExpression3, 0, 0);
		Expression newExpression7 = new AndExpression(newExpression4, newExpression2, 0, 0);
		Expression newExpression8 = new AndExpression(newExpression3, newExpression4, 0, 0);
		
		assertTrue( (boolean) newExpression5.getValue());
		assertFalse( (boolean) newExpression6.getValue());
		assertFalse( (boolean) newExpression7.getValue());
		assertFalse( (boolean) newExpression8.getValue());
	
	}
	
	@Test
	public final void OrExpressions_SingleCase() {
		Expression newExpression1 = new BooleanLiteral(0, 0, true);
		Expression newExpression2 = new BooleanLiteral(0, 0, true);
		Expression newExpression3 = new BooleanLiteral(0, 0, false);
		Expression newExpression4 = new BooleanLiteral(0, 0, false);
		
		Expression newExpression5 = new OrExpression(newExpression1, newExpression2, 0, 0);
		Expression newExpression6 = new OrExpression(newExpression1, newExpression3, 0, 0);
		Expression newExpression7 = new OrExpression(newExpression4, newExpression2, 0, 0);
		Expression newExpression8 = new OrExpression(newExpression3, newExpression4, 0, 0);
		
		assertTrue( (boolean) newExpression5.getValue());
		assertTrue( (boolean) newExpression6.getValue());
		assertTrue( (boolean) newExpression7.getValue());
		assertFalse( (boolean) newExpression8.getValue());
	}
	
}
