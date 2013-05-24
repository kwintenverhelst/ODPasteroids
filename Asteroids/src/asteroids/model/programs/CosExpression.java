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
			throw new IllegalArgumentException("on of your operand is false");
		}
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
