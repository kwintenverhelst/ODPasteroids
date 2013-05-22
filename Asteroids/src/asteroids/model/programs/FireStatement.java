package asteroids.model.programs;

public class FireStatement extends ActionStatement {
	
	public FireStatement (int line, int column){
		super(line, column);
	}
	
	@Override
	public void execute(){
		 SelfExpression.getSelf().firebullet();
	}

}
