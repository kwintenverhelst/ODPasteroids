
package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.ObjectInSpace;

public abstract class Statement{
	
	@Model
	protected Statement (int line, int column){
		this.line = line;
		this.column = column;
	}
	
	public int getLine(){
		return line;
	}
	
	private int line;
	
	public int getColumn(){
		return column;
	}
		
	private int column;
	
	/**
	 * 
	 * @return
	 */
	public abstract void execute();

	public abstract boolean canHaveAsSubStatement(Statement subStatement);
	
	public abstract boolean canHaveAsExpression(Expression expression);
	
	public Program getProgram(){
		return null;
	}
	
	
	
	
	
	
}
