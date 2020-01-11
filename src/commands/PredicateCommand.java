package commands;

import client_side.Parser;

import java.io.IOException;

public class PredicateCommand implements Command{
    @Override
    public int doCommand(String[] args) throws IOException {
        double left;
        double right;
        boolean result = false;

        if(isDouble(args[0])) {
            left = Double.parseDouble(args[0]);
        }
        else {
            left = Double.parseDouble(Parser.symbolTable.get(args[0]));
        }
        if(isDouble(args[2])) {
            right = Double.parseDouble(args[2]);
        }
        else {
            right = Double.parseDouble(Parser.symbolTable.get(args[2]));
        }

        switch (args[1]) {
            case ">" : result = left > right;
                       break;
            case "<" : result = left < right;
                        break;
            case "==" : result = left == right;
                        break;
            case "!=" : result = left != right;
                        break;
            case ">=" : result = left >= right;
                        break;
            case "<=" : result = left <= right;
                        break;
        }

        return result ? 1 : 0;
    }

    private static boolean isDouble(String val){
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
