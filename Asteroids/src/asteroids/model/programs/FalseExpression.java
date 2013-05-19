package asteroids.model.programs;

public class FalseExpression extends BasicExpression implements BooleanExpression{
	
	public FalseExpression(int line, int column){
		super(line, column);
	}
	
	@Override
	public Object getValue() {
		return false;
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

	@Override
	public Type getType() {
		return TYPE;
	}

}
