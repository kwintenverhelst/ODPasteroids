package asteroids.model.programs;

public class NullExpression extends BasicExpression {

	public NullExpression(int line, int column) {
		super(line, column);
	}
	
	public Object getValue() {
		return null;
	}

	@Override
	public Type getType() {
		return null;
	}

	

}
