package asteroids.model.programs;

import asteroids.model.Ship;

public class SelfExpression extends BasicExpression implements EntityExpression {

	public SelfExpression(int line, int column) {
		super(line, column);

	}
	
	private static Ship SELF;
	
	public static void setSelf(Ship self){
		SELF = self;
	}
	
	public static Ship getSelf(){
		return SELF;
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
