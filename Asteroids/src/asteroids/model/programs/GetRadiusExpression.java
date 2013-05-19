package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class GetRadiusExpression extends UnaryExpression implements DoubleExpression{

	public GetRadiusExpression(Expression operand, int line, int column) {
		super(operand, line, column);
	}

	@Override
	public Object getValue() {
		if(getOperand().hasTypeEntity()){
			return ((ObjectInSpace) getOperand().getValue()).getRadius();
		} else {
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "getradius";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
