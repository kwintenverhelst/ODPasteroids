package asteroids.model.programs;

import java.util.List;

import asteroids.model.programs.parsing.ProgramFactory;

public class ProgramFactoryImpl implements ProgramFactory<Expression, Statement, Type>{

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new ConstantExpression(d, line, column);
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(line, column, b);
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		return new AndExpression(e1, e2, line, column);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		return new OrExpression(e1, e2, line, column);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new NotExpression(e, line, column);
	}

	@Override
	public Expression createNull(int line, int column) {
		return new NullExpression(line, column);
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new SelfExpression(line, column);
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetXExpression(e, line, column);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetYExpression(e, line, column);
	}

	@Override
	public Expression createGetVX(int line, int column, Expression e) {
		return new GetVXExpression(e, line, column);
	}

	@Override
	public Expression createGetVY(int line, int column, Expression e) {
		return new GetVYExpression(e, line, column);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadiusExpression(e, line, column);
	}

	@Override
	public Expression createVariable(int line, int column, String name) {
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return new VariableExpression(name, line, column);
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new LessThanExpression(e1, e2, line, column);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new GreaterThanExpression(e1, e2, line, column);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LessThanOrEqualExpression(e1, e2, line, column);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new GreaterThanOrEqualExpression(e1, e2, line, column);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		return new IsEqualExpression(e1, e2, line, column);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new IsNotEqualExpression(e1, e2, line, column);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new AdditionExpression(e1, e2, line, column);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new SubstractionExeption(e1, e2, line, column);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		return new MultiplicationExpression(e1, e2, line, column);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new DivisionExpression(e1, e2, line, column);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new SquareRootExpression(e, line, column);
	}

	@Override
	public Expression createGetDirection(int line, int column) {
		return new GetDirectionExpression(line, column);
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return new SinExpression(e, line, column);
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return new CosExpression(e, line, column);
	}

	@Override
	public Statement createEnableThruster(int line, int column) {
		return new ThrustStatement(line, column);
	}

	@Override
	public Statement createDisableThruster(int line, int column) {
		return new ThrustOffStatement(line, column);
	}

	@Override
	public Statement createFire(int line, int column) {
		return new FireStatement(line, column);
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new TurnStatement(line, column, angle);
	}

	@Override
	public Statement createAssignment(int line, int column, String variable,
			Expression rhs) {
		return new AssignStatement(line, column, variable, rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		return new IfStatement(line, column, condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		return new WhileStatement(line, column, condition, body);
	}

	@Override
	public Statement createForeach(int line, int column,ForeachType type,
			String variableName, Statement body) {
		return new ForEachStatement(line, column, type, variableName, body);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new SkipStatement(line, column);
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new SequenceStatement(line, column, statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new PrintStatement(line, column, e);
	}

	@Override
	public Type createDoubleType() {
		return Type.DOUBLE;
	}

	@Override
	public Type createBooleanType() {
		return Type.BOOLEAN;
	}

	@Override
	public Type createEntityType() {
		return Type.ENTITY;
	}

}
