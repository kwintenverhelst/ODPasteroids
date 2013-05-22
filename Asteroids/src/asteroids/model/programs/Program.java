package asteroids.model.programs;

import java.util.Map;

import asteroids.model.*;

public class Program {
	
	private Ship entity;
	
	private Map<String, Type> globals;
	
	private Statement statement;
	
	public Program() {
	}
	
	public Program(Map<String, Type> globals, Statement statement) {
		this.globals = globals;
		this.statement = statement;
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
	}
	
	public World getWorld(){
		if(getEntity()==null)
			return null;
		return getEntity().getWorld();
	}

	public void execute(){
		getStatement().execute();
	}
}
