package asteroids.model.programs;

public class CosExpression extends UnaryExpression implements DoubleExpression{

	public CosExpression(Expression operand, int line, int column) {
		super(operand, line, column);
	}
	
	@Override
	public Object getValue() {
		if(getOperand().hasTypeDouble()){
			return Math.cos((double) getOperand().getValue());
		} else {
			return 0;
		}
	}
	
	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return "cos";
	}

	@Override
	public Type getType() {
		return TYPE;
	}

}
