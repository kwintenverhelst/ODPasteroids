package asteroids.model.programs;

public class AndExpression extends BinaryExpression implements BooleanExpression{
	
	public AndExpression(Expression left, Expression right) {
		super(left, right);
	}
	
	@Override
	public boolean getValue() {
		if(getLeftOperand().getClass().isInstance(BooleanExpression.class) && getRightOperand().getClass().isInstance(BooleanExpression.class)){
			return ((BooleanExpression) getLeftOperand()).getValue() && ((BooleanExpression) getRightOperand()).getValue();
		} else {
			return false;
		}
	}
	
	@Override
	public String getOperatorSymbol() {
		return "&&";
	}

	

}
