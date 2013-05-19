package asteroids.model.programs;

public class OrExpression extends BinaryExpression implements BooleanExpression{
	
	public OrExpression(Expression left, Expression right, int line, int column) {
		super(left, right,line, column);
	}
	
	@Override
	public boolean getValue() {
		if(getLeftOperand().getClass().isInstance(BooleanExpression.class) && getRightOperand().getClass().isInstance(BooleanExpression.class)){
			return ((BooleanExpression) getLeftOperand()).getValue() || ((BooleanExpression) getRightOperand()).getValue();
		} else {
			return false;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "||";
	}

}
