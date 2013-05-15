package asteroids.model.programs;

public class NotExpression extends UnaryExpression implements BooleanExpression{
	
	public NotExpression(Expression operand) {
		super(operand);
	}

	@Override
	public boolean getValue() {
		if(getOperand().getClass().isInstance(BooleanExpression.class)){
			return !((BooleanExpression) getOperand()).getValue();
		} else {
			return false;
		}
	}
	@Override
	public String getOperatorSymbol() {
		return "!";
	}

}