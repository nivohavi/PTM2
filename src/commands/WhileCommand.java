package commands;

import client_side.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhileCommand implements Command{

    @Override
    public int doCommand(String[] args) throws IOException {
        Command predicateCommand = (Command) Parser.commandFactory.getNewProduct("predicate");
        int result = predicateCommand.doCommand(args);
        return result;
    }
}
