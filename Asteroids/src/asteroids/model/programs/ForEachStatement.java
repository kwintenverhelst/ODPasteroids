package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import asteroids.model.*;
import asteroids.model.programs.parsing.ProgramFactory.ForeachType;

public class ForEachStatement extends Statement {
		
	public ForEachStatement(int line, int column, ForeachType type, String variableName, Statement body){
		super(line, column);
		setVariable(variableName);
		setSubStatement(body);
		body.setSuperStatement(this);
		this.type = type;
	}
	
	
	
	@Basic
	public String getVariableName(){
		return variable;
	}
	
	public boolean isValidVariable(String variable){
		return getProgram().hasAsGlobal(variable);
	}
	
	public void setVariable(String variable){
		assert isValidVariable(variable);
		this.variable = variable;
	}
	
	private String variable;

		@Basic
	public Statement getSubStatement(){
		return subStatement;
	}
		/**
	 * 
	 * @param statement
	 * @return
	 */
	@Override
	public boolean canHaveAsSubStatement(Statement subStatement) {
		if(subStatement instanceof ActionStatement)
			return false;
		return true;
	}
	
	@Override
	public boolean hasAsSubStatement(Statement statement){
		if(getSubStatement()==statement)
			return true;
		if(getSubStatement().hasAsSubStatement(statement))
			return true;
		return false;
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

	@Basic @Immutable
	public ForeachType getType(){
		return type;
	}
	
	private ForeachType type;
	
	@Override
	public void execute() {
		if(!getProgram().isInterupted()){
			if(getType()==ForeachType.SHIP){
				for(Ship ship: getProgram().getWorld().getShips()){
					getSubStatement().execute();
				}
			}
			else if(getType()==ForeachType.ASTEROID){
				for(Asteroid ship: getProgram().getWorld().getAsteroids()){
					getSubStatement().execute();
				}
			}
			else if(getType()==ForeachType.BULLET){
				for(Bullet ship: getProgram().getWorld().getBullets()){
					getSubStatement().execute();
				}
			}
			else if(getType()==ForeachType.ANY){
				for(ObjectInSpace ship: getProgram().getWorld().getAllObjectsInSpace()){
					getSubStatement().execute();
				}
			}
		
		}
	}
		

}