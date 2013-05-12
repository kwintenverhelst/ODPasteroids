package asteroids.model.programs;

import org.stringtemplate.v4.compiler.STParser.expr_return;

public class WhileStatement extends Statement {
	
	public WhileStatement(Expression expression, Statement body){
		
	}

	@Override
	protected boolean canHaveAsStatement(Statement subStatement) {
		
		return false;
	}

	@Override
	protected boolean canHaveAsExpression(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute() {
	assert getExpression.hasAsType(BOOLEAN);
	assert HasAsSubStatement(assignStatement);
		while(getExpression()){
			getSubStatement().execute();	
		}

	}

	@Override
	public String toString() {
		String text= "while(" + getExpression().toString() + ") do {\n" + getSubStatement().toString() +"}\n";
		return text;
	}


}
