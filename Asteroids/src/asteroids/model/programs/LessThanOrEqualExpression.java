package asteroids.model.programs;

public class LessThanOrEqualExpression extends BinaryExpression implements BooleanExpression{
	
	public LessThanOrEqualExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public boolean getValue() {
		if(getLeftOperand().getClass().isInstance(DoubleExpression.class) && getRightOperand().getClass().isInstance(DoubleExpression.class)){
			return ((DoubleExpression) getLeftOperand()).getValue() <= ((DoubleExpression) getRightOperand()).getValue();
		} else {
			return false;
		}
	}
	
	@Override
	public String getOperatorSymbol() {
		return "<=";
	}

}
