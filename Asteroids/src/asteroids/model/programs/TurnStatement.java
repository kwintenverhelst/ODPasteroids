package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class TurnStatement extends ActionStatement {
	
	public TurnStatement (int line, int column, Expression expression){
		super(line, column);
		setExpression(expression);
	}
	
	@Override
	public boolean canHaveAsExpression(Expression expression) {
		if (expression.getType()==Type.DOUBLE)
			return true;
		return false;
	}
	
	@Basic
	public Expression getExpression(){
		return expression;
	}
	
	/**
	 * 
	 * @param expression
	 */
	public void setExpression(Expression expression){
		assert canHaveAsExpression(expression);
		this.expression= expression;
	}

	private Expression expression;
	
	@Override
	public void execute(){
		//TURN VALUE
		getEntity().turn(getExpression().getValue());
		}

}
