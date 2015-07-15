package sfu.cmpt307.application;

import java.io.FileNotFoundException;
import sfu.cmpt307.handleInput.InputScanner;
import sfu.cmpt307.handleInput.Interpreter;

public class TwoThreeTests {
	
	public static void main(String[] args) throws FileNotFoundException {
		InputScanner input = InputScanner.scan();
		Interpreter.makeAndRunInterpreter(input);
	}
}
