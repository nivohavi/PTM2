package commands;

import expressions.ShuntingYard;
import client_side.Parser;

public class AssignCommand implements Command {
    @Override
    public int doCommand(String[] args)
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
            Double d;
            StringBuilder exp = new StringBuilder();
            if (args.length > 3) {
                for (int i = 2; i < args.length; i++)
                    exp.append(args[i]);
                 d = ShuntingYard.calc(exp.toString());
            }
            else {
                d = Double.parseDouble(args[2]);
            }
            Parser.symbolTable.put(args[0], d.toString());
        }
        return 0;
    }
}
