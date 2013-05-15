package asteroids.model.programs;

public class TrueExpression extends BasicExpression implements BooleanExpression{
	
	
	public TrueExpression() {
		this.value = true;
	}

	@Override
	public boolean getValue() {
		return (boolean) value;
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
