package asteroids.model.programs;

public class GetYExpression extends UnaryExpression implements DoubleExpression{

	public GetYExpression(Expression operand, int line, int column) {
		super(operand, line, column);
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
