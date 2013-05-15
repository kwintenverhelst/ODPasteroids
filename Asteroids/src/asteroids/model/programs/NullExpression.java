package asteroids.model.programs;

public class NullExpression extends BasicExpression {

	public NullExpression() {
		this.value = null;
	}
	
	public Object getValue() {
		return value;
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
