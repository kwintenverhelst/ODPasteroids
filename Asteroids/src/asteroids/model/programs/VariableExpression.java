package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class VariableExpression extends BasicExpression {

	public VariableExpression(String name, int line, int column) {
		super(line, column);
		this.name = name;

	}

	private String name;

	private Expression value;

	private Type type;

	private void setValue(String name) {
		if (Expression.getProgram() != null) {
			this.value = Expression.getProgram().getValueOfGlobal(getName());
			this.type = this.value.getType();
		}
	}

	@Basic
	public String getName() {
		return this.name;
	}

	@Override
	public Object getValue() {
		setValue(name);
		if (this.value == null) {
			return null;

		}
		return value.getValue();
	}

	@Override
	public Type getType() {
		return type;
	}

}
