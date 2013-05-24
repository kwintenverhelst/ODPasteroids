package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class EntityLiteralExpression extends BasicExpression implements
		EntityExpression {

	public EntityLiteralExpression(ObjectInSpace value, int line, int column) {
		super(line, column);
		this.value = value;
	}

	private ObjectInSpace value; 
	
	@Override
	public Object getValue() {
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
		return  value + "  entity expression";
	}

	@Override
	public Type getType() {
		return TYPE;
	}

}
