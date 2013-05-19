package asteroids.model.programs;

public class GetVYExpression extends UnaryExpression implements DoubleExpression{

	public GetVYExpression(Expression operand, int line, int column) {
		super(operand, line, column);
	}

	@Override
	public double getValue() {
		if(getOperand().getClass().isInstance(EntityExpression.class)){
			return ((EntityExpression) getOperand()).getValue().getVelocityY();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "getvy";
	}

}
