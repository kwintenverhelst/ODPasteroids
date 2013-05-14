package asteroids.model.programs;

public class ThrustStatement extends ActionStatement {
	
	public ThrustStatement (int line, int column){
		super(line, column);
	}
	
	@Override
	public void execute(){
		getEntity().setThrusterActive(true);
		}

}
