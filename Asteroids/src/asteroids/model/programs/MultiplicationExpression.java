package asteroids.model.programs;


public class MultiplicationExpression extends BinaryExpression implements DoubleExpression{

	public MultiplicationExpression(Expression left, Expression right, int line, int column)
			 {
		super(left, right,line, column);
	}

	@Override
	public Object getValue() {
		if(getLeftOperand().hasTypeDouble() && getRightOperand().hasTypeDouble()){
			return (double) getLeftOperand().getValue() * (double) getRightOperand().getValue();
		} else {
			throw new IllegalArgumentException("on of your operand is false");
		}
	}

	@Override
	public Type getType() {
		return TYPE;
	}
}
