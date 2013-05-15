package asteroids.model.programs;

public class DivisionExpression extends BinaryExpression implements DoubleExpression{

	public DivisionExpression(Expression left, Expression right) {
		super(left, right);
	}
	
	@Override
	public double getValue() {
		if(getLeftOperand().getClass().isInstance(DoubleExpression.class) && getRightOperand().getClass().isInstance(DoubleExpression.class)){
			return ((DoubleExpression) getLeftOperand()).getValue() / ((DoubleExpression) getRightOperand()).getValue();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "/";
	}

	

}
