package test;

import client_side.Lexer;
import client_side.Parser;

import java.io.IOException;

public class MyInterpreter {

	public static int interpret(String[] lines) {
		// call your interpreter here - call Parser / Lexer
		return Parser.getInstance().parseLineByLine(lines);
	}
}
