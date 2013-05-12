package asteroids.model.programs;

public class IfStatement extends Statement {
	
	public IfStatement (Expression expression, Statement ifBody, Statement elseBody){
		
	}
	@Override
	protected boolean canHaveAsStatement(Statement subStatement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean canHaveAsExpression(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
