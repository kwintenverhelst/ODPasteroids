package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class GetVYExpression extends UnaryExpression implements DoubleExpression{

	public GetVYExpression(Expression operand, int line, int column) {
		super(operand, line, column);
	}

	@Override
	public Object getValue() {
		if(getOperand().hasTypeEntity()){
			return ((ObjectInSpace) getOperand().getValue()).getVelocityY();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "getvy";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
