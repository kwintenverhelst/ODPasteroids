package asteroids.model.programs;

import asteroids.model.ObjectInSpace;
import asteroids.model.programs.parsing.ProgramFactory.ForeachType;

public class ForEachStatement extends Statement {
	public enum ForeachType{
		SHIP, ASTEROID, BULLET, ANY
	}
	
	public ForEachStatement(ForeachType type, String variableName, Statement body){
		
	}
	
	@Override
	public boolean canHaveAsSubstatement(Statement subStatement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canHaveAsExpression(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute() {

		}



}
