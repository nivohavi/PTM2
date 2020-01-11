package client_side;

import commands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser
{
    private static Parser _instance = null;
    public static GenericFactory commandFactory = new GenericFactory<Command>();
    public static HashMap<String,String> symbolTable = new HashMap<String,String>();
    public static HashMap<String,String> bindsTable = new HashMap<String,String>();

    private Parser()
    {
        commandFactory.insertProduct("openDataServer", OpenDataServerCommand.class);
        commandFactory.insertProduct("connect", ConnectCommand.class);
//        commandFactory.insertProduct("while",LoopCommand.class);
        commandFactory.insertProduct("var",VarCommand.class);
        commandFactory.insertProduct("return", ReturnCommand.class);
        commandFactory.insertProduct("=",AssignCommand.class);
//        commandFactory.insertProduct("disconnect",DisconnectCommand.class);
//        commandFactory.insertProduct("print",PrintCommand.class);
//        commandFactory.insertProduct("sleep",SleepCommand.class);
//        commandFactory.insertProduct("predicate",PredicateCommand.class);
//        commandFactory.insertProduct("autoroute",AutoRouteCommand.class);
//        commandFactory.insertProduct("if",IfCommand.class);
    }

    // Singleton
    public static Parser getInstance()
    {
        if (_instance == null)
            _instance = new Parser();

        return _instance;
    }

    // input: line
    public int parseAndExecute(String[] command) throws IOException {
        int result;
        String[] splittedCommand = splitSpacesInExpression(command);
        Command commandObj = (Command)commandFactory.getNewProduct(splittedCommand[0]);
        if (commandObj != null)
            result = commandObj.doCommand(Arrays.copyOfRange(splittedCommand, 1, splittedCommand.length));
        else {
            commandObj = (Command) commandFactory.getNewProduct("=");
            result = commandObj.doCommand(splittedCommand);
        }
        return result;
    }

    public static String[] replaceCommandVariables(String[] command) {
        List<String> newCommand = new ArrayList<String>();
        for (String c : command) {
            newCommand.add(symbolTable.getOrDefault(c, c));
        }
        String[] result = new String[newCommand.size()];
        result = newCommand.toArray(result);

        return result;
    }

    public static String[] splitSpacesInExpression(String[] command) {
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
