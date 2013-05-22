package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class VariableExpression extends BasicExpression  {

	public VariableExpression(String name, int line, int column) {
		super(line, column);
		this.name = name;
		setValue(name);
	}

	private String name;
	
	private Expression value;
	
	private Type type;

	private void setValue(String name){
		if(Expression.getProgram() != null){
			Expression.getProgram().getValueOfGlobal(getName());
			this.type = this.value.getType();
		}
	}
	
	@Basic
	public String getName(){
		return this.name;
	}
	@Override
	public Object getValue() {
		if(this.value == null){
			return null;
		}
		return value.getValue();
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
		return name + "";
	}
	
	@Override
	public Type getType() {
		return type;
	}

}
