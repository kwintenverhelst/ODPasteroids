package asteroids.model.programs;

public class NullExpression extends BasicExpression {

	public NullExpression(int line, int column) {
		super(line, column);
	}
	
	public Object getValue() {
		return null;
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
		return "null expression";
	}

	@Override
	public Type getType() {
		return null;
	}

	

}
