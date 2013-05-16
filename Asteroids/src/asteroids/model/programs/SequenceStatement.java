package asteroids.model.programs;

import java.util.List;

public class SequenceStatement extends Statement {

	public SequenceStatement (int line, int column, List<Statement> statements){
		super(line, column);
		
	}
	
	@Override
	public boolean canHaveAsSubstatement(Statement subStatement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		return false;
	}
	
	
	@Override
	public void execute() {
		
	}

}
