package commands;

import client_side.Parser;
import expressions.ShuntingYard;

import java.io.IOException;

public class ReturnCommand implements Command {

    @Override
    public int doCommand(String[] args) throws IOException {

        StringBuilder exp = new StringBuilder();
//        args = Parser.replaceCommandVariables(args);
        for (int i = 0; i < args.length; i++) {
            exp.append(args[i]);
        }

        double result = ShuntingYard.calc(exp.toString());
        return (int)result;
    }
}
