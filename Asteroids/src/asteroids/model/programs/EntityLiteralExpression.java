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
	public Type getType() {
		return TYPE;
	}

}
