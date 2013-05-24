package asteroids.model.programs;

public class SquareRootExpression extends UnaryExpression  implements DoubleExpression{

	public SquareRootExpression(Expression operand, int line, int column) {
		super(operand,line, column);
	}

	@Override
	public Object getValue() {
		if(getOperand().hasTypeDouble()){
			return Math.sqrt((double) getOperand().getValue());
		} else {
			throw new IllegalArgumentException("on of your operand is false");
		}
	}

	@Override
	public Type getType() {
		return TYPE;
	}

}
