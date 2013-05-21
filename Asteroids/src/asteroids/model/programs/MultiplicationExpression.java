package asteroids.model.programs;


public class MultiplicationExpression extends BinaryExpression implements DoubleExpression{
	/**
	 * Initialize this new multiplication with given operands.
	 *
	 * @param  left
	 *         The left operand for this new multiplication.
	 * @param  right
	 *         The right operand for this new multiplication.
	 * @effect This new multiplication is initialized as a binary expression
	 *         with the given operands.
	 *       | super(left,right)
	 */
	public MultiplicationExpression(Expression left, Expression right, int line, int column)
			 {
		super(left, right,line, column);
	}

	/**
	 * Return the value of this multiplication.
	 *
	 * @return The product of the values of the operands of this addition.
	 *       | result ==
	 *       |   getLeftOperand().getValue() *
	 *       |   getRightOperand().getValue()
	 */
	@Override
	public Object getValue() {
		if(getLeftOperand().hasTypeDouble() && getRightOperand().hasTypeDouble()){
			return (double) getLeftOperand().getValue() * (double) getRightOperand().getValue();
		} else {
			throw new IllegalArgumentException("on of your operand is false");
		}
	}

	/**
	 * Return the symbol representing the operator of this multiplication.
	 * 
	 * @return The string "*"
	 *       | result.equals("*")
	 */
	@Override
	public String getOperatorSymbol() {
		return "*";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}
}
