package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class GetYExpression extends UnaryExpression implements DoubleExpression{

	public GetYExpression(Expression operand, int line, int column) {
		super(operand, line, column);
	}

	@Override
	public Object getValue() {
		if(getOperand().hasTypeEntity()){
			return ((ObjectInSpace) getOperand().getValue()).getY();
		} else {
			throw new IllegalArgumentException("on of your operand is false");
		}
	}

	@Override
	public Type getType() {
		return TYPE;
	}

}
