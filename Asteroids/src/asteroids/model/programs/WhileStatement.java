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
		setExpression(expression);
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
	
	@Basic
	public Statement getSubStatement(){
		return subStatement;
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
			getSubStatement().execute();	
		}

	}




}
