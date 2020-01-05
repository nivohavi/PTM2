package client_side;

import commands.Command;
import commands.ConnectCommand;
import commands.OpenDataServerCommand;
import commands.AssignCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Parser
{
    private static Parser _instance = null;
    private GenericFactory commandFactory = new GenericFactory<Command>();
    public static HashMap<String,Object> symbolTable;
    public static HashMap<String,Object> bindsTable;

    private Parser()
    {
        commandFactory.insertProduct("openDataServer", OpenDataServerCommand.class);
        commandFactory.insertProduct("connect", ConnectCommand.class);
//        commandFactory.insertProduct("while",LoopCommand.class);
//        commandFactory.insertProduct("var",DefineVarCommand.class);
//        commandFactory.insertProduct("return",ReturnCommand.class);
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
    public void parseAndExecute(String[] command) throws IOException {
        Command commandObj = (Command)commandFactory.getNewProduct(command[0]);
        commandObj.doCommand(Arrays.copyOfRange(command, 1, command.length));
    }
}
