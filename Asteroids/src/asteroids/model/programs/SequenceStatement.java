package asteroids.model.programs;


import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;

public class SequenceStatement extends Statement {

	public SequenceStatement (int line, int column, List<Statement> statements){
		super(line, column);
		this.statements = statements;
		for(Statement statement: this.statements){
			statement.setSuperStatement(this);
		}
	}
	
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		return true;
	}
	
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
	public boolean canHaveAsExpression(Expression expression) {
		return false;
	}
	
	public Statement getStatementAt(int index){
		return statements.get(index);
	}
	
	private List<Statement> statements;
	
	@Override
	public void execute() {
		if(!getProgram().isInterupted()){
			if(getProgram().getLastExecuted()==null){
				for(int i = 0; i<statements.size();i++){
					statements.get(i).execute();
				}
			}
//			else{
//				int b = 0;
//				for(int a=0; a<statements.size();a++){
//					if(statements.get(a)==getProgram().getLastExecuted())
//						b = a+1;
//				}
//				if(b == statements.size()){
//					if(getSuperStatement()!=null){
//						getSuperStatement().execute();
//					}
//
//				}
//				else{
//					for(int i=b; i<statements.size();i++){
//						statements.get(i).execute();
//					}
//				}			
//			}
		}
	}
}
