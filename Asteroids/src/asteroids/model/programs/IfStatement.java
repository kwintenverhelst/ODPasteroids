package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class IfStatement extends Statement {
	
	/**
	 * 
	 * @param expression
	 * @param ifBody
	 * @param elseBody
	 */
	public IfStatement (int line, int column, Expression expression, Statement ifBody, Statement elseBody){
			super(line, column);
			setIfBody(ifBody);
			setElseBody(elseBody);
			setExpression(expression);
		}

		/**
		 * 
		 * @param statement
		 * @return
		 */
		@Override
		public boolean canHaveAsSubStatement(Statement subStatement) {
			return true;
		}
		
		@Basic
		public Statement getIfBody(){
			return ifBody;
		}
		
		/**
		 * 
		 * @param statement
		 */
		public void setIfBody(Statement statement){
			assert canHaveAsSubStatement(statement);
			ifBody = statement;
		}
		
		private Statement ifBody;
		
		@Basic
		public Statement getElseBody(){
			return elseBody;
		}
		
		/**
		 * 
		 * @param statement
		 */
		public void setElseBody(Statement statement){
			assert canHaveAsSubStatement(statement);
			elseBody = statement;
		}
		
		private Statement elseBody;
		
		/**
		 * 
		 */
		@Override
		public boolean canHaveAsExpression(Expression expression) {
			if (expression.getType() == Type.BOOLEAN)
				return true;
			return false;
		}
		
		@Basic
		public Expression getExpression(){
			return expression;
		}
		
		/**
		 * 
		 * @param expression
		 */
		public void setExpression(Expression expression){
			assert canHaveAsExpression(expression);
			this.expression= expression;
		}

		private Expression expression;
		
		/**
		 * 
		 */
		@Override
		public void execute() {
			// expression Bool.
			if(getExpression().getValue()){
				getIfBody().execute();	
			}
			else{
				getElseBody().execute();
			}

		}




	}
