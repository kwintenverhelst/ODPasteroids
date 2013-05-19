package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Value;

public class BooleanLiteral extends BasicExpression implements
		BooleanExpression {

	protected BooleanLiteral(int line, int column, boolean value) {
		super(line, column);
		this.value = value;
	}
	
	private boolean value;

	@Override
	public boolean getValue() {
		return this.value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}

	@Override
	public String toString() {
		return null;
	}

}
