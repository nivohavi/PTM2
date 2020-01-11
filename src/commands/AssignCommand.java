package commands;

import expressions.ShuntingYard;
import client_side.Parser;

import java.util.Map;

public class AssignCommand implements Command {
    @Override
    public int doCommand(String[] args)
    {
        if(args[2].equals("bind"))
        {
            Parser.bindsTable.put(args[0] , args[3]);
        }
        else
        {
            Double d;
            StringBuilder exp = new StringBuilder();
            if (args.length > 3) {
                for (int i = 2; i < args.length; i++)
                    exp.append(args[i]);
                 d = ShuntingYard.calc(exp.toString());
            }
            else {
                d = ShuntingYard.calc(args[2]);
            }
            Parser.symbolTable.put(args[0], d.toString());
            Parser.updateBoundedTo(args[0], d.toString());
        }
        return 0;
    }
}
