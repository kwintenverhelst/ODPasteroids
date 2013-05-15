package asteroids.model.programs;


public class ConstantExpression extends BasicExpression  implements DoubleExpression{

	public ConstantExpression(Object value) {
		if(value.getClass().isInstance(double.class)){
			this.value = value;
		}
	}

	@Override
	public double getValue() {
		return (double) this.value;
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
