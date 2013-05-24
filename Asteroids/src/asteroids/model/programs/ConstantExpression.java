package asteroids.model.programs;

public class ConstantExpression extends BasicExpression implements
		DoubleExpression {

	public ConstantExpression(double value, int line, int column) {
		super(line, column);
		this.value = value;
	}

	private double value;

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public Type getType() {
		return TYPE;
	}
}
