package ex4;

import client_side.Lexer;
import client_side.Parser;

import java.io.IOException;

public class MyInterpreter {

	public static int interpret(String[] lines) {
		// call your interpreter here - call Parser / Lexer
		int result = 0;
		for (String line : lines) {
			String[] commandLine = Lexer.getInstance().lex(line);

			try {
				result = Parser.getInstance().parseAndExecute(commandLine);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
