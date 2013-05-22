package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class AssignStatement extends Statement {
	
	public AssignStatement(int line, int column, String variable, Expression rhs){
		super(line, column);
		System.out.println(variable + "   :  assign  " + rhs);
		setVariable(variable);
		setExpression(rhs);
	}

	@Override
	public void execute() {
		
	}
	
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		return false;
	}

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		// check voorwaarden
		return true;
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
		//nog doen
	}

	private String variable;
	

}
