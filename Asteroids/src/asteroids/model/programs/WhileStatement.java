package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;

public class WhileStatement extends Statement {
	
	/**
	 * 
	 * @param expression
	 * @param body
	 */
	public WhileStatement(int line, int column, Expression expression, Statement body){
		super(line, column);
		setSubStatement(body);
		body.setSuperStatement(this);
		setExpression(expression);
	}
	
	@Basic
	public Statement getSubStatement(){
		return subStatement;
	}
	
	/**
	 * 
	 * @param statement
	 * @return
	 */
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		return true;
	}
	
	public boolean hasAsSubStatement(Statement statement){
		if(getSubStatement()==statement)
			return true;
		if(getSubStatement().hasAsSubStatement(statement))
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param statement
	 */
	public void setSubStatement(Statement statement){
		assert canHaveAsSubStatement(statement);
		subStatement = statement;
	}
	
	private Statement subStatement;
	
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
		
	public void setSuperStatement(Statement statement){
		if(canHaveAsSuperStatement(statement))
			superStatement = statement;
	}
	
	private Statement superStatement;
	
	/**
	 * 
	 */
	@Override
	public boolean canHaveAsExpression(Expression expression) {
		if (expression.getType()==Type.BOOLEAN)
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
			while((boolean) getExpression().getValue()){
				if(getProgram().isInterupted()){
					break;
				}
				else{
					getProgram().setLastExecuted(null);
					getSubStatement().execute();
				}
			}

		}
	}

