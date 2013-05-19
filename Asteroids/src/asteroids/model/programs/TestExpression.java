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
	public final void BooleanExpressions_SingleCase() {
		Expression newExpression1 = new TrueExpression();
		assertTrue( ((BooleanExpression) newExpression1).getValue());
		Expression newExpression2 = new NotExpression(newExpression1);
		assertFalse( ((BooleanExpression) newExpression2).getValue());
	}
}
