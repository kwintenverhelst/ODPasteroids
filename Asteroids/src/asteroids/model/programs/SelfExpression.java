package asteroids.model.programs;

import asteroids.model.ObjectInSpace;

public class SelfExpression extends BasicExpression implements
EntityExpression{

	public SelfExpression(Object value) {
		if(value.getClass().isInstance(ObjectInSpace.class)){
			this.value = value;
		}
	}

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
	
	
}
