package asteroids.model.programs;


public class AdditionExpression extends BinaryExpression  implements DoubleExpression{

	/**
	 * Initialize this new addition with given operands.
	 *
	 * @param  left
	 *         The left operand for this new addition.
	 * @param  right
	 *         The right operand for this new addition.
	 * @param line 
	 * @param column 
	 * @effect This new addition is initialized as a binary expression
	 *         with the given operands.
	 *       | super(left,right)
	 */
	public AdditionExpression(Expression left, Expression right, int line, int column)
			 {
		super(left, right, line, column);
	}

	/**
	 * Return the value of this addition.
	 *
	 * @return The sum of the values of the operands of this addition.
	 *       | result ==
	 *       |   getLeftOperand().getValue() + 
	 *       |   getRightOperand().getValue()
	 */
	@Override
	public double getValue() {
		if(getLeftOperand().getClass().isInstance(DoubleExpression.class) && getRightOperand().getClass().isInstance(DoubleExpression.class)){
			return ((DoubleExpression) getLeftOperand()).getValue() + ((DoubleExpression) getRightOperand()).getValue();
		} else {
			return 0;
		}
	}

	/**
	 * Return the symbol representing the operator of this addition.
	 * 
	 * @return The string "+"
	 *       | result.equals("+")
	 */
	@Override
	public String getOperatorSymbol() {
		return "+";
	}
}
