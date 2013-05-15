package asteroids.model.programs;

public class SinExpression extends UnaryExpression implements DoubleExpression{

	public SinExpression(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue() {
		if(getOperand().getClass().isInstance(DoubleExpression.class)){
			return Math.cos(((DoubleExpression) getOperand()).getValue());
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "sin";
	}

}
