package asteroids.model.programs;

public class ThrustOffStatement extends ActionStatement {
	
	public ThrustOffStatement (int line, int column){
		super(line, column);
	}
	
	@Override
	public void execute(){
		getEntity().setThrusterActive(false);
		}
}
