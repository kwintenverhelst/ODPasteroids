package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class AssignStatement extends Statement {
	
	public AssignStatement(int line, int column, String variable, Expression rhs){
		super(line, column);
		setVariable(variable);
		setExpression(rhs);
	}

	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		return false;
	}

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		if(getProgram().getValueOfGlobal(variable).getType()==expression.getType())
			return true;
		if(getProgram().getValueOfGlobal(variable).getType()==Type.ENTITY && expression.getType()==null)
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
	
	@Basic
	public String getVariable(){
		return variable;
	}
	
	/**
	 * 
	 * @param 
	 */
	public void setVariable(String variable){
		this.variable = variable;
	}

	private String variable;

	@Override
	public Statement getSuperStatement() {
		return superStatement;
	}

	@Override
	public boolean canHaveAsSuperStatement(Statement statement) {
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
	public void setSuperStatement(Statement statement) {
		superStatement=statement;
		
	}
	
	private Statement superStatement;
	
	@Override
	public void execute() {
		if(!getProgram().isInterupted())
			Statement.getProgram().setValueToGlobal(getVariable(), getExpression());
	}
}
