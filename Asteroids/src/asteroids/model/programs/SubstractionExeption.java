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
	public SubstractionExeption(Expression left, Expression right, int line, int column)
			{
		super(left, right,line, column);
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
	public Object getValue() {
		if(getLeftOperand().hasTypeDouble() && getRightOperand().hasTypeDouble()){
			return (double) getLeftOperand().getValue() - (double) getRightOperand().getValue();
		} else {
			throw new IllegalArgumentException("on of your operand is false");
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
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
