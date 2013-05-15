package asteroids.model.programs;

public class GetRadiusExpression extends UnaryExpression implements DoubleExpression{

	public GetRadiusExpression(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue() {
		if(getOperand().getClass().isInstance(EntityExpression.class)){
			return ((EntityExpression) getOperand()).getValue().getRadius();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "getradius";
	}

}
