package asteroids.model.programs;


public class SubstractionExeption extends BinaryExpression  implements DoubleExpression{
	/**
	 * Initialize this new subtraction with given operands.
	 *
	 * @param  left
	 *		   The left operand for this new subtraction.
	 * @param  right
	 *		   The right operand for this new subtraction.
	 * @effect This new subtraction is initialized as a binary expression
	 *         with the given operands.
	 *       | super(left,right)
	 */
	public SubstractionExeption(Expression left, Expression right)
			{
		super(left, right);
	}

	/**
	 * Return the value of this subtraction.
	 *
	 * @return The difference between the value of the left operand
	 *		   and the right operand of this subtraction.
	 *       | result ==
	 *       |   getLeftOperand().getValue() - 
	 *       |   getRightOperand().getValue()
	 */
	@Override
	public double getValue() {
		if(getLeftOperand().getClass().isInstance(DoubleExpression.class) && getRightOperand().getClass().isInstance(DoubleExpression.class)){
			return ((DoubleExpression) getLeftOperand()).getValue() - ((DoubleExpression) getRightOperand()).getValue();
		} else {
			return 0;
		}
	}

	/**
	 * Return the symbol representing the operator of this subtraction.
	 * 
	 * @return The string "-"
	 *       | result.equals("-")
	 */
	@Override
	public String getOperatorSymbol() {
		return "-";
	}

}
