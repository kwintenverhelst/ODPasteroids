package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class VariableExpression extends BasicExpression implements
		EntityExpression {

	public VariableExpression(String name, int line, int column) {
		super(line, column);
		this.name = name;

	}

	private String name;

	@Override
	public Object getValue() {
		return name;
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
