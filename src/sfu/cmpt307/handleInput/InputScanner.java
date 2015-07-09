package sfu.cmpt307.handleInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputScanner {
	
	private BufferedReader reader;
	private List<Integer> initialTreeElements = new ArrayList<Integer>();
	private List<Operation> operations = new ArrayList<Operation>();
	
	public InputScanner(String fileName) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(fileName));
	}
	
	public static InputScanner scan(String fileName) throws FileNotFoundException {
		InputScanner scanner = new InputScanner(fileName);
		scanner.readFile();
		return scanner;
	}
	
	public List<Integer> getInitalTreeElements() {
		return this.initialTreeElements;
	}
	
	public List<Operation> getOperations() {
		return this.operations;
	}
	
	private void readFile() {
		try {
			readInitalElements();
			readOperations();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readInitalElements() throws IOException {
		String line = reader.readLine();
		List<String> stringInput = new ArrayList<String>(
				Arrays.asList(line.split("\\s*,\\s*")));
		for (String element: stringInput) {
			initialTreeElements.add(Integer.parseInt(element));
		}
	}
	
	private void readOperations() throws IOException {
		String line = reader.readLine();
		while (line != null) {
			String [] operationTuple = line.split("\\s+");
			Operator operator = Operator.forLexeme(operationTuple[0]);
			if (operator == Operator.MAX || operator == Operator.MIN) {
				Operand operand = new Operand(0);
				operations.add(Operation.make(operator, operand));
			} else {
				Operand operand = new Operand(Integer.parseInt(operationTuple[1]));
				operations.add(Operation.make(operator, operand));
			}
			line = reader.readLine();
		}
	}
}
