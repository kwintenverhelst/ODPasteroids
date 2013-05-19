package asteroids.model.programs;

public class LessThanExpression extends BinaryExpression implements BooleanExpression{
	
	public LessThanExpression(Expression left, Expression right, int line, int column) {
		super(left, right,line, column);
	}

	@Override
	public Object getValue() {
		if(getLeftOperand().hasTypeDouble() && getRightOperand().hasTypeDouble()){
			return (double) getLeftOperand().getValue() < (double) getRightOperand().getValue();
		} else {
			return false;
		}
	}
	
	@Override
	public String getOperatorSymbol() {
		return "<";
	}

	@Override
	public Type getType() {
		return TYPE;
	}
}
