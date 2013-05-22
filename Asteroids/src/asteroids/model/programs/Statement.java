
package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.ObjectInSpace;

public abstract class Statement{
	
	@Model
	protected Statement (int line, int column){
		this.line = line;
		this.column = column;
	}
	
	@Basic
	public final int getLine(){
		return line;
	}
	
	private int line;
	
	@Basic 
	public final int getColumn(){
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
	
	public static void setProgram(Program program){
		PROGRAM=program;
	}
		
	
	public static Program getProgram(){
		return PROGRAM;
	}
	
	private static Program PROGRAM;
	
	
	
	
}
