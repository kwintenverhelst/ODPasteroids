package asteroids.model.programs;

public class GetXExpression extends UnaryExpression implements DoubleExpression {

	public GetXExpression(Expression operand) {
		super(operand);
	}
	
	@Override
	public double getValue() {
		if (getOperand().getClass().isInstance(EntityExpression.class)) {
			return ((EntityExpression) getOperand()).getValue().getX();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "getx";
	}


}
