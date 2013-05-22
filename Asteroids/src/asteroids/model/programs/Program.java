package asteroids.model.programs;

import java.util.Map;
import java.util.Set;

import asteroids.model.*;

public class Program {
	
	private Ship entity;
	
	private Map<String, Type> globals;
	
	private Map<String, Expression> globalsValue;
	
	private Statement statement;
	
	public Program() {
	}
	
	public Program(Map<String, Type> globals, Statement statement) {
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

	public void execute(){
		getStatement().execute();
	}
	public Map<String, Expression> getGlobalsValues() {
		return this.globalsValue;
	}
	
	public void defaultValue(){
		Set<String> globalNames = getGlobalsValues().keySet(); 
		for(String name : globalNames){
			Type type = getGlobals().get(name);
			Expression expression = null;
			if(type == Type.BOOLEAN){
				expression = new BooleanLiteral(0, 0, false);
			} else if(type == Type.DOUBLE){
				expression = new ConstantExpression(0, 0, 0);
			} else if(type == Type.ENTITY){
				expression = new NullExpression(0, 0);
			}
			getGlobalsValues().put(name, expression);
		}
	}
	
	public boolean hasAsGlobal(String name){
		return getGlobals().containsKey(name);
	}
	
	public void setValueToGlobal(String name, Expression expression){
		if(hasAsGlobal(name)){
			getGlobalsValues().remove(name);
			getGlobalsValues().put(name, expression);
		}
	}
	
	public Expression getValueOfGlobal(String name){
		return getGlobalsValues().get(name);
	}
}
