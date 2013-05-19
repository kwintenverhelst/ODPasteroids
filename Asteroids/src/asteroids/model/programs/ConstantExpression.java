package asteroids.model.programs;

public class ConstantExpression extends BasicExpression implements
		DoubleExpression {

	public ConstantExpression(double value, int line, int column) {
		super(line, column);
		this.value = value;
	}

	private double value;

	@Override
	public double getValue() {
		return this.value;
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
