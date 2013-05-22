package asteroids.model.programs;

import asteroids.model.Ship;

public class getDirectionExpression extends UnaryExpression implements
		DoubleExpression {

	public getDirectionExpression( int line, int column) {
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
