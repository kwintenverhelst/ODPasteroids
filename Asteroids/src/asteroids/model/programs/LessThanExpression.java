package asteroids.model.programs;

public class LessThanExpression extends BinaryExpression implements BooleanExpression{
	
	public LessThanExpression(Expression left, Expression right, int line, int column) {
		super(left, right,line, column);
	}

	@Override
	public Object getValue() {
		if(getLeftOperand().getValue() == null || getRightOperand().getValue() == null){
			return  false;
		} else if(getLeftOperand().hasTypeDouble() && getRightOperand().hasTypeDouble()){
			return (double) getLeftOperand().getValue() < (double) getRightOperand().getValue();
		} else if(!getLeftOperand().hasTypeDouble()) {
			throw new IllegalArgumentException("on of your operand is false left");
		} else {
			throw new IllegalArgumentException("on of your operand is false right");
	
		}
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}
}
