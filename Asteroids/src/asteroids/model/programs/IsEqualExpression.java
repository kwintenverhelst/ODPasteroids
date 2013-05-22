package asteroids.model.programs;

import asteroids.Util;

public class IsEqualExpression  extends BinaryExpression implements BooleanExpression{
	
	public IsEqualExpression(Expression left, Expression right, int line, int column) {
		super(left, right,line, column);
	}

	@Override
	public Object getValue() {
		if(getLeftOperand().hasTypeDouble() && getRightOperand().hasTypeDouble()){
			return Util.fuzzyEquals((double) getLeftOperand().getValue(), (double) getRightOperand().getValue());
		} else if(!getLeftOperand().hasTypeDouble()){
			throw new IllegalArgumentException("on of your left operand is false");
		} else {
			throw new IllegalArgumentException("on of your right operand is false");

		}
	}

	@Override
	public String getOperatorSymbol() {
		return "==";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
