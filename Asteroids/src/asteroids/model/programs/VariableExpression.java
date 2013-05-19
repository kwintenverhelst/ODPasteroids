package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class VariableExpression extends BasicExpression implements
		EntityExpression {

	public VariableExpression(ObjectInSpace value, int line, int column) {
		super(line, column);
		this.value = value;

	}

	private ObjectInSpace value;

	@Override
	public ObjectInSpace getValue() {
		return (ObjectInSpace) value;
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
	
	@Override
	public Type getType() {
		return TYPE;
	}

}
