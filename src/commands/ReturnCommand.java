package commands;

import java.io.IOException;

public class ReturnCommand implements Command {

    @Override
    public void doCommand(String[] args) throws IOException {

        StringBuilder exp = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            exp.append(args[i]);
        }

        //ParserMain.returnval = ShuntingYard.calc(exp.toString());
    }
}
