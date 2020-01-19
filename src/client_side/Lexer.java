package client_side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer
{
    private static Lexer _instance = null;

    private Lexer() {}

    public static Lexer getInstance() {
        if (_instance == null)
            _instance = new Lexer();

        return _instance;
    }

    public String[] lex(String line) {
        String [] splittedCommands = line.split("\\s+");
        if(splittedCommands[0].equals("set")) {
            return splittedCommands;
        }
        else {
            return splitSpacesInExpression(splittedCommands);
        }
    }

    private static String[] splitSpacesInExpression(String[] command) {
        List<String> newCommand = new ArrayList<String>();
        for (String c : command) {
            String[] split = c.split("(?<=[-+*/=()])|(?=[-+*/=()])");
            newCommand.addAll(Arrays.asList(split));
        }
        String[] result = new String[newCommand.size()];
        result = newCommand.toArray(result);

        return result;
    }

}
