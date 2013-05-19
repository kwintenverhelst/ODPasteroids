package asteroids.model.programs;

public class NotExpression extends UnaryExpression implements BooleanExpression{
	
	public NotExpression(Expression operand, int line, int column) {
		super(operand,line, column);
	}

	@Override
	public boolean getValue() {
		if(BooleanExpression.class.isInstance(getOperand().getClass())){
			return !((BooleanExpression) getOperand()).getValue();
		} else {
			throw new IllegalAccessError("hey");
		}
	}
	@Override
	public String getOperatorSymbol() {
		return "!";
	}

}