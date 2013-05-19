package asteroids.model.programs;

public class IsNotEqualExpression extends BinaryExpression implements BooleanExpression{
	
	public IsNotEqualExpression(Expression left, Expression right, int line, int column) {
		super(left, right,line, column);
	}

	@Override
	public boolean getValue() {
		if(getLeftOperand().getClass().isInstance(DoubleExpression.class) && getRightOperand().getClass().isInstance(DoubleExpression.class)){
			return ((DoubleExpression) getLeftOperand()).getValue() != ((DoubleExpression) getRightOperand()).getValue();
		} else {
			return false;
		}
	}
	@Override
	public String getOperatorSymbol() {
		return "!=";
	}

}
