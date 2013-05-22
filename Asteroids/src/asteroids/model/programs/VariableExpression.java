package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.model.ObjectInSpace;

public class VariableExpression extends BasicExpression  {

	public VariableExpression(String name, int line, int column) {
		super(line, column);
		System.out.println(name + "   :  variable" + line + "    " + column);
		this.name = name;

	}

	private String name;
	
	private Expression value;
	
	private Type type;

	private void setValue(){
		
	}
	
	@Basic
	public String getName(){
		return this.name;
	}
	@Override
	public Object getValue() {
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
