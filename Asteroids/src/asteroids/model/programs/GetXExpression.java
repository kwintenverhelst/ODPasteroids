package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class GetXExpression extends UnaryExpression implements DoubleExpression {

	public GetXExpression(Expression operand, int line, int column) {
		super(operand, line, column);
	}
	
	@Override
	public Object getValue() {
		if (getOperand().hasTypeEntity()) {
			return ((ObjectInSpace) getOperand().getValue()).getX();
		} else {
			throw new IllegalArgumentException("on of your operand is false");
		}
	}

	@Override
	public Type getType() {
		return TYPE;
	}

}
