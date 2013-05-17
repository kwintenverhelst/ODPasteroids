package asteroids.model.programs;

import java.util.ArrayList;
import java.util.List;

public class SequenceStatement extends Statement {

	public SequenceStatement (int line, int column, List<Statement> statements){
		super(line, column);
		statements = new ArrayList<Statement>(statements);
	}
	
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		return true;
	}

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		return false;
	}
	
	public Statement getStatementAt(int index){
		return statements.get(index -1);
	}
	
	private List<Statement> statements;
	
	@Override
	public void execute() {
		for(int i=0; i<statements.size();i++){
			statements.get(i).execute();
		}
	}

}
