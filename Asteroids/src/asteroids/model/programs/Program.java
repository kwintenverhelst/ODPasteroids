package asteroids.model.programs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

import asteroids.model.*;

public class Program {
	
	private Ship entity;
	
	private Map<String, Type> globals;
	
	private Map<String, Expression> globalsValue = new HashMap<String, Expression>();
	
	private Statement statement;
	
	public Program() {
	}
	
	public Program(Map<String, Type> globals, Statement statement) {
		Expression.setProgram(this);
		Statement.setProgram(this);
		this.globals = globals;
		this.statement = statement;
		defaultValue();
		
	}
	
	public Map<String, Type> getGlobals() {
		return this.globals;
	}
	
	public Statement getStatement() {
		return this.statement;
	}

	public Ship getEntity() {
		return this.entity;
	}
	
	public void setEntity(Ship entity){
		this.entity = entity;
		entity.setProgram(this);
		SelfExpression.setSelf(entity);
	}
	
	public World getWorld(){
		if(getEntity()==null)
			return null;
		return getEntity().getWorld();
	}


	public Map<String, Expression> getGlobalsValues() {
		return this.globalsValue;
	}
	
	public void defaultValue(){
		Set<String> globalNames = getGlobals().keySet(); 
		for(String name : globalNames){
			Type type = getGlobals().get(name);
			Expression expression = null;
			if(type == Type.BOOLEAN){
				System.out.print("boolean program");
				expression = new BooleanLiteral(0, 0, false);
			} else if(type == Type.DOUBLE){
				System.out.print("boolean program");
				expression = new ConstantExpression(0, 0, 0);
			} else if(type == Type.ENTITY){
				System.out.print("boolean program");
				expression = new NullExpression(0, 0);
			}
			System.out.print(expression.getValue());
			getGlobalsValues().put(name, expression);
		}
	}
	
	public boolean hasAsGlobal(String name){
		return getGlobals().containsKey(name);
	}
	
	public void setValueToGlobal(String name, Expression expression){
		System.out.println("change a value in program");

		if(hasAsGlobal(name)){
			System.out.println("change a value gelukt in program  " + name + "  " + expression);

			getGlobalsValues().remove(name);
			getGlobalsValues().put(name, expression);
		}
	}
	
	public Expression getValueOfGlobal(String name){
		System.out.println("expression uit global " + name + "   " + getGlobalsValues().get(name) + "  " + getGlobalsValues().get(name).getValue() );

		return getGlobalsValues().get(name);
	}
	
	@Basic
	public Statement getLastExecuted(){
		return lastExecuted;
	}
	
	public void setLastExecuted(Statement statement){
		lastExecuted = statement;
	}
	
	private Statement lastExecuted;
	
	private boolean executed;
	
	public void execute(){
		if(!executed){
			if(getLastExecuted() == null){
				getStatement().execute();
			}
			else{
				getLastExecuted().execute();
			}
			executed =true;
		}
	}

}