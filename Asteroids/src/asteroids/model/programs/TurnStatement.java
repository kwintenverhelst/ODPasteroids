
package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class TurnStatement extends ActionStatement {
	
	public TurnStatement (int line, int column, Expression expression){
		super(line, column);
		setExpression(expression);
	}
	
	@Basic
	public Expression getExpression(){
		return expression;
	}
	
	@Override
	public boolean canHaveAsExpression(Expression expression) {
		if (expression.getType()==Type.DOUBLE)
			return true;
		return false;
	}
	
	public void setExpression(Expression expression){
		assert canHaveAsExpression(expression);
		this.expression= expression;
	}

	private Expression expression;
	
	@Override
	public void execute(){
		if(!getProgram().isInterupted()){
			if(getProgram().getLastExecuted()==this){
				if(getSuperStatement() != null)
					getSuperStatement().execute();
			}
			else{
				SelfExpression.getSelf().turn((double) getExpression().getValue());
				getProgram().setLastExecuted(this);
				getProgram().setInterupted(true);
			}		
		}
	}

}
