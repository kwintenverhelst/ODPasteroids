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
			return 0;
		}
	}

	@Override
	public String getOperatorSymbol() {
		return "sqrt";
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
