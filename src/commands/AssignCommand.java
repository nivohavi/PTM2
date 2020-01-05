package commands;

import expressions.ShuntingYard;
import client_side.Parser;

public class AssignCommand implements Command {
    @Override
    public void doCommand(String[] args)
    {
        if(args[2].equals("bind"))
        {
            if(Parser.bindsTable.containsKey(args[3]))
            {
                Parser.bindsTable.put(args[0] , Parser.symbolTable.get(args[3]));
            }
            else
            {
                Parser.bindsTable.put(args[0] , args[3]);
            }
        }
        else
        {
            StringBuilder exp = new StringBuilder();
            for (int i = 2; i < args.length; i++)
                exp.append(args[i]);
            Double d = ShuntingYard.calc(exp.toString());
            Parser.symbolTable.put(args[0], d);
        }
    }
}
