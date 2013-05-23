package asteroids.model.programs;

public class SkipStatement extends ActionStatement {
	
	public SkipStatement (int line, int column){
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
				getProgram().setLastExecuted(this);
				getProgram().setInterupted(true);
			}		
		}
	}


}