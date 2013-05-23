package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;

public class PrintStatement extends Statement {

	public PrintStatement(int line, int column, Expression expression){
		super(line, column);
		this.expression = expression;
	}
	
	private Expression expression;
	
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
	
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		return false;
	}
	
	@Override
	public boolean hasAsSubStatement(Statement subStatement) {
		return false;
	}

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		return true;
	}
	
	@Basic
	public String getToPrint(){
		return toPrint;
	}
	
	public void setToPrint(){
		if(expression != null && expression.getValue() != null){
			toPrint = expression.getValue().toString();
		}
		else{
			toPrint = "";
		}
	}
	
	private String toPrint;
	
	@Override
	public void execute() {

		if(!getProgram().isInterupted())
			System.out.println(toPrint);
	}

}