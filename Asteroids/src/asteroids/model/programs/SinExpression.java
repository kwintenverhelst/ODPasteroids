package asteroids.model.programs;

public class SinExpression extends UnaryExpression implements DoubleExpression{

	public SinExpression(Expression operand, int line, int column) {
		super(operand,line, column);
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
		return "sin";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
