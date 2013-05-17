package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;

public class PrintStatement extends Statement {

	public PrintStatement(int line, int column, Expression expression){
		super(line, column);
		setToPrint(expression);
	}
	
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
	
	public void setToPrint(Expression expression){
		toPrint = expression.getValue().toString();
	}
	
	private String toPrint;
	
	@Override
	public void execute() {
		System.out.println(toPrint);
	}

}
