package asteroids.model.programs;

public class SquareRootExpression extends UnaryExpression  implements DoubleExpression{

	public SquareRootExpression(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue() {
		if(getOperand().getClass().isInstance(DoubleExpression.class)){
			return Math.sqrt(((DoubleExpression) getOperand()).getValue());
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "sqrt";
	}

}
