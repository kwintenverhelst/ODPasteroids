package asteroids.model.programs;

public abstract class BasicExpression extends Expression {

	protected BasicExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public final boolean hasAsSubExpression(Expression expression) {
		return expression == this;
	}
}