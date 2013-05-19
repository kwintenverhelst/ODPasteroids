package asteroids.model.programs;

public class TrueExpression extends BasicExpression implements
		BooleanExpression {

	public TrueExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean getValue() {
		return true;
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
