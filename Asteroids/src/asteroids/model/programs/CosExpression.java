package asteroids.model.programs;

public class CosExpression extends UnaryExpression implements DoubleExpression{

	public CosExpression(Expression operand) {
		super(operand);
	}
	
	@Override
	public double getValue() {
		if(getOperand().getClass().isInstance(DoubleExpression.class)){
			return Math.cos(((DoubleExpression) getOperand()).getValue());
		} else {
			return 0;
		}
	}
	
	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return "cos";
	}

	

}
