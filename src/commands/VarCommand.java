package commands;

import client_side.Parser;

import java.io.IOException;

public class VarCommand implements Command {

    @Override
    public int doCommand(String[] args) throws IOException {
        if (args.length == 1) {
            Parser.symbolTable.put(args[0], null);
        }
        else {
            if (args[1].equals("="))
            {
                Command c = (Command)Parser.commandFactory.getNewProduct("=");
                c.doCommand(args);
            }
        }
        return 0;
    }
}
