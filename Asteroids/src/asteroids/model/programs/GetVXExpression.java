package asteroids.model.programs;

public class GetVXExpression extends UnaryExpression implements DoubleExpression{

	public GetVXExpression(Expression operand, int line, int column) {
		super(operand, line, column);
	}

	@Override
	public double getValue() {
		if(getOperand().getClass().isInstance(EntityExpression.class)){
			return ((EntityExpression) getOperand()).getValue().getVelocityX();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "getvx";
	}

}
