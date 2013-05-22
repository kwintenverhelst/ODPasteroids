package asteroids.model.programs;
import asteroids.model.Ship;
import be.kuleuven.cs.som.annotate.*;

public abstract class ActionStatement extends Statement {

	@Model
	protected ActionStatement(int line, int column) {
		super(line, column);
	}

	public Ship getEntity(){
		return SelfExpression.getSelf();
	}
	
	@Override
	public boolean  canHaveAsSubStatement(Statement subStatement) {
		return false;
	}

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		return false;
	}

}
