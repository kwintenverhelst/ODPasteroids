package asteroids.model.programs;

public class ThrustOffStatement extends ActionStatement {
	
	public ThrustOffStatement (int line, int column){
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
				SelfExpression.getSelf().setThrusterActive(false);
				getProgram().setLastExecuted(this);
				getProgram().setInterupted(true);
			}		
		}
	}	
}