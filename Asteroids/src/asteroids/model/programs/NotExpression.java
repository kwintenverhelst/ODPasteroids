package asteroids.model.programs;

public class NotExpression extends UnaryExpression implements BooleanExpression{
	
	public NotExpression(Expression operand, int line, int column) {
		super(operand,line, column);
	}

	@Override
	public Object getValue() {
		if(getOperand().hasTypeBoolean()){
			return !(boolean) getOperand().getValue();
		} else {
			throw new IllegalAccessError("hey");
		}
	}
	@Override
	public String getOperatorSymbol() {
		return "!";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}