
package asteroids.model.programs;

public abstract class Statement{

	/**
	 * 
	 * @return
	 */
	public abstract void execute();
	
	/**
	 * 
	 */
	@Override
	public abstract String toString();
	
	public abstract boolean canHaveAsSubstatement(Statement subStatement);
	
	public abstract boolean canHaveAsExpression(Expression expression);
	
	
	
	
	
}
