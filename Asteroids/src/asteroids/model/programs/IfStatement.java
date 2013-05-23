
package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class IfStatement extends Statement {
	
	/**
	 * 
	 * @param expression
	 * @param ifBody
	 * @param elseBody
	 */
	public IfStatement (int line, int column, Expression expression, Statement ifBody, Statement elseBody){
			super(line, column);
			setIfBody(ifBody);
			ifBody.setSuperStatement(this);
			setElseBody(elseBody);
			elseBody.setSuperStatement(this);
			setExpression(expression);
		}

	@Override @Basic
	public Statement getSuperStatement(){
		return superStatement;
	}
	
	@Override
	public boolean canHaveAsSuperStatement(Statement statement){
		if(statement == null)
			return true;
		if(statement instanceof WhileStatement)
			return true;
		if(statement instanceof IfStatement)
			return true;
		if(statement instanceof ForEachStatement)
			return true;
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
		
	@Override
	public void setSuperStatement(Statement statement){
		if(canHaveAsSuperStatement(statement))
			superStatement = statement;
	}
	
	private Statement superStatement;
		
	/**
	 * 
	 * @param statement
	 * @return
	 */
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		return true;
	}
		
	@Basic
	public Statement getIfBody(){
		return ifBody;
	}
	
	/**
	 * 
	 * @param statement
	 */
	public void setIfBody(Statement statement){
		assert canHaveAsSubStatement(statement);
		ifBody = statement;
	}
	
	private Statement ifBody;
	
	@Basic
	public Statement getElseBody(){
		return elseBody;
	}
	
	/**
	 * 
	 * @param statement
	 */
	public void setElseBody(Statement statement){
		assert canHaveAsSubStatement(statement);
		elseBody = statement;
	}
	
	private Statement elseBody;
	
	/**
	 * 
	 */
	@Override
	public boolean canHaveAsExpression(Expression expression) {
		if (expression.getType() == Type.BOOLEAN)
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
	
	/**
	 * 
	 */
	@Override
	public void execute() {
		if(!getProgram().isInterupted()){

			if((boolean) getExpression().getValue()){
				getIfBody().execute();	
			}
			else{
				getElseBody().execute();
			}
		}

	}




}