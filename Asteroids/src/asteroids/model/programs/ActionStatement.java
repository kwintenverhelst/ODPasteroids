
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
	public boolean hasAsSubStatement(Statement statement){
		return statement == this;
	}
	
	@Override
	public boolean  canHaveAsSubStatement(Statement subStatement) {
		return false;
	}
	
	@Basic
	public Statement getSuperStatement(){
		return superStatement;
	}
	
	public boolean canHaveAsSuperStatement(Statement statement){
		if(statement == null)
			return true;
		if(statement instanceof WhileStatement)
			return true;
		if(statement instanceof IfStatement)
			return true;
		if(statement instanceof ForEachStatement)
			return false;
		if(statement instanceof ActionStatement)
			return false;
		if(statement instanceof PrintStatement)
			return false;
		if(statement instanceof AssignStatement)
			return false;
		if(statement instanceof SequenceStatement)
			return true;
		return statement.canHaveAsSubStatement(this);
	}
		
	public void setSuperStatement(Statement statement){
		if(canHaveAsSuperStatement(statement))
			superStatement = statement;
	}
	
	private Statement superStatement;

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		return false;
	}
	
	public abstract void execute();
}
