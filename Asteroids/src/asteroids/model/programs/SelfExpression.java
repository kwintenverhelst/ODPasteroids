package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class SelfExpression extends BasicExpression implements EntityExpression {

	public SelfExpression(int line, int column) {
		super(line, column);

	}
	
	private static ObjectInSpace SELF;
	
	public static void setSelf(ObjectInSpace self){
		SELF = self;
	}
	
	@Override
	public Object getValue() {
		return SELF;
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
