package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;

public abstract class ComposedExpression extends Expression {

	protected ComposedExpression(int line, int column) {
		super(line, column);
	}

	@Basic @Raw
	public abstract int getNbOperands();

	@Basic @Raw
	public abstract Expression getOperandAt(int index)
			throws IndexOutOfBoundsException;

	public boolean canHaveAsOperandAt(Expression expression, int index) {
		return (expression != null) && (!expression.hasAsSubExpression(this)) && (index > 0);
	}

	protected abstract void setOperandAt(int index, Expression operand);

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		if (expression == this)
			return true;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (getOperandAt(pos).hasAsSubExpression(expression))
				return true;
		return false;
	}	
}