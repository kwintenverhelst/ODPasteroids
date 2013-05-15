package asteroids.model.programs;

public class FalseExpression extends BasicExpression implements BooleanExpression{
	
	public FalseExpression(){
		this.value = false;
	}
	
	@Override
	public boolean getValue() {
		return (boolean) this.value;
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
