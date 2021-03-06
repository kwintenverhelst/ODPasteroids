package asteroids.model.programs;

import asteroids.Util;

public class IsNotEqualExpression extends BinaryExpression implements BooleanExpression{
	
	public IsNotEqualExpression(Expression left, Expression right, int line, int column) {
		super(left, right,line, column);
	}

	@Override
	public Object getValue() {
		if(getLeftOperand().getValue() == null || getRightOperand().getValue() == null){
			return  getLeftOperand().getValue() != null || getRightOperand().getValue() != null;
		} else if(getLeftOperand().hasTypeDouble() && getRightOperand().hasTypeDouble()){
			return ! Util.fuzzyEquals((double) getLeftOperand().getValue(), (double) getRightOperand().getValue());
		} else {
			throw new IllegalArgumentException("on of your operand is false");
		}
	}

	@Override
	public Type getType() {
		return TYPE;
	}
}
