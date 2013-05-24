package asteroids.model.programs;


public class BooleanLiteral extends BasicExpression implements
		BooleanExpression {

	public BooleanLiteral(int line, int column, boolean value) {
		super(line, column);
		this.value = value;
	}
	
	private boolean value;

	@Override
	public Object getValue() {
		return this.value;
	}
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
