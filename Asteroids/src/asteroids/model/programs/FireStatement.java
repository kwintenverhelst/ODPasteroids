package asteroids.model.programs;

public class FireStatement extends ActionStatement {
	
	public FireStatement (int line, int column){
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
				SelfExpression.getSelf().firebullet();
				getProgram().setLastExecuted(this);
				getProgram().setInterupted(true);
			}		
		}
	}
}