package sfu.cmpt307.handleInput;


public class Operation {
	
	private Operator operator;
	private Operand operand;
	
	public Operation(Operator operator, Operand operand) {
		this.operator = operator;
		this.operand = operand;
	}
	
	public int getOperandValue() {
		return this.operand.getValue();
	}
	
	public Operator getOperator() {
		return this.operator;
	}
	
	public static Operation make(Operator operator, Operand operand) {
		Operation result = new Operation(operator, operand);
		return result;
	}
	
	@Override
	public String toString() {
		if (operator == Operator.MAX || operator == Operator.MIN) {
			return operator.getLexeme() + "  ";
		}
		return operator.getLexeme() + " " + operand.getValue();
	}
}
