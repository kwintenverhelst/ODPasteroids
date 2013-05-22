package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;

public class PrintStatement extends Statement {

	public PrintStatement(int line, int column, Expression expression){
		super(line, column);
		this.expression = expression;
	}
	
	private Expression expression;
	
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
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
		setToPrint();
		System.out.println(toPrint);
	}

}
