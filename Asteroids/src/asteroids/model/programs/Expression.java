package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;


public abstract class Expression {
	
	@Model
	protected Expression (int line, int column){
		this.line = line;
		this.column = column;
	}
	
	@Basic
	public final int getLine(){
		return line;
	}
	
	private final int line;
	
	@Basic 
	public final int getColumn(){
		return column;
	}
		
	private final int column;
	
	public static void setProgram(Program program){
		PROGRAM=program;
	}
		
	
	public static Program getProgram(){
		return PROGRAM;
	}
	
	private static Program PROGRAM;
	
	public abstract Object getValue();
	
	public abstract boolean hasAsSubExpression(Expression expression);

	@Override
	public String toString(){
		return this.getValue() + "";
	}

	public abstract Type getType();
	
	public boolean hasTypeBoolean(){
		return this.getValue() != null && this.getType() == Type.BOOLEAN;
	}

	public boolean hasTypeDouble(){
		return this.getValue() != null && this.getType() == Type.DOUBLE;
	}
	
	public boolean hasTypeEntity(){
		return this.getValue() != null && this.getType() == Type.ENTITY;
	}
}
