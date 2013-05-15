package asteroids.model.programs;

public class GetYExpression extends UnaryExpression implements DoubleExpression{

	public GetYExpression(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue() {
		if(getOperand().getClass().isInstance(EntityExpression.class)){
			return ((EntityExpression) getOperand()).getValue().getY();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "gety";
	}

}
