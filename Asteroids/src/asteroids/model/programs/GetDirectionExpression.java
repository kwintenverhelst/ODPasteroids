package asteroids.model.programs;

import asteroids.model.Ship;

public class GetDirectionExpression extends UnaryExpression implements
		DoubleExpression {

	public GetDirectionExpression( int line, int column) {
		super(new SelfExpression(line, column), line, column);
	}

	@Override
	public Object getValue() {
		if(getOperand().hasTypeEntity()){
			return ((Ship) getOperand().getValue()).getAngle();
		} else {
			throw new IllegalArgumentException("on of your operand is false");
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "getDirection";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
