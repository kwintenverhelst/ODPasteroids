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
	
	/**
	 * 
	 * @param statement
	 */
	public void setSubStatement(Statement statement){
		assert canHaveAsSubStatement(statement);
		subStatement = statement;
	}
	
	private Statement subStatement;
	
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
		//Variable Name
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
