package asteroids.model.programs;

public class AndExpression extends BinaryExpression implements BooleanExpression{
	
	public AndExpression(Expression left, Expression right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	public Object getValue() {
		if(getLeftOperand().hasTypeBoolean() && getRightOperand().hasTypeBoolean()){
			return (boolean) getLeftOperand().getValue() && (boolean) getRightOperand().getValue();
		} else {
			return false;
		}
	}
	
	@Override
	public String getOperatorSymbol() {
		return "&&";
	}

	@Override
	public Type getType() {
		return TYPE;
	}

}
