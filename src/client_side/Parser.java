package client_side;

import commands.*;
import server_side.Server;

import java.io.IOException;
import java.util.*;

public class Parser {
    private static Parser _instance = null;
    public static Server socketToClose = null;
    public static GenericFactory commandFactory = new GenericFactory<Command>();
    public static HashMap<String,String> symbolTable = new HashMap<String,String>();
    public static HashMap<String,String> bindsTable = new HashMap<String,String>();

    private Parser() {
        commandFactory.insertProduct("openDataServer", OpenDataServerCommand.class);
        commandFactory.insertProduct("connect", ConnectCommand.class);
        commandFactory.insertProduct("while",WhileCommand.class);
        commandFactory.insertProduct("var",VarCommand.class);
        commandFactory.insertProduct("return", ReturnCommand.class);
        commandFactory.insertProduct("=",AssignCommand.class);
        commandFactory.insertProduct("disconnect",DisconnectCommand.class);
        commandFactory.insertProduct("predicate",PredicateCommand.class);
        commandFactory.insertProduct("set",SetCommand.class);
    }

    // Singleton
    public static Parser getInstance() {
        if (_instance == null)
            _instance = new Parser();

        return _instance;
    }

    // input: line
    public int parseAndExecute(String[] command) throws IOException {
        int result;
         Command commandObj = (Command)commandFactory.getNewProduct(command[0]);
        if (commandObj != null)
            result = commandObj.doCommand(Arrays.copyOfRange(command, 1, command.length));
        else {
            commandObj = (Command) commandFactory.getNewProduct("=");
            result = commandObj.doCommand(command);
        }

        return result;
    }

    public int parseLineByLine(String[] lines) {
        int result = 0;
        int while_index = -1;

        for (int i = 0; i < lines.length; i++) {
            String[] commandLine = Lexer.getInstance().lex(lines[i]);

            try {
                if (commandLine[0].equals("while")) {
                    result = parseAndExecute(commandLine);
                    if (result == 1) {
                        while_index = i;
                    }
                    else {
                       i += indicesCount(lines, i);
                    }
                }
                else {
                    if (commandLine[0].equals("}")) {
                        i = while_index - 1;
                    }
                    else {
                        if (commandLine[0].equals("")) {
                            commandLine = Arrays.copyOfRange(commandLine, 1, commandLine.length);
                        }
                        result = parseAndExecute(commandLine);
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int indicesCount(String[] lines, int i) {
        int skip = 0;
        String[] commandLine = Lexer.getInstance().lex(lines[i]);
        while (!commandLine[0].equals("}")) {
            i++;
            skip ++;
            commandLine = Lexer.getInstance().lex(lines[i]);
        }
        return skip;
    }

    public static void updateBoundedTo(String key, String newVal) {
        String bindName = Parser.bindsTable.get(key); // get SimX
        for (Map.Entry<String, String> entry : Parser.bindsTable.entrySet()) {
            if (entry.getValue().equals(bindName)) {
                Parser.symbolTable.put(entry.getKey(), newVal);
            }
        }
    }
}
