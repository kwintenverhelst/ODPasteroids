package asteroids.model.programs;

public class ThrustStatement extends ActionStatement {
	
	public ThrustStatement (int line, int column){
		super(line, column);
	}
	
	@Override
	public void execute(){
		if(!getProgram().isInterupted()){
			if(getProgram().getLastExecuted()==this){
				if(getSuperStatement() != null)
					getSuperStatement().execute();
			}
			else{
				SelfExpression.getSelf().setThrusterActive(true);
				getProgram().setLastExecuted(this);
				getProgram().setInterupted(true);
			}		
		}
	}

}