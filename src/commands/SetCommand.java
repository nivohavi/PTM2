package commands;

import client_side.SimulatorSocket;

import java.io.IOException;

public class SetCommand implements Command {
    @Override
    public int doCommand(String[] args) throws IOException {
        if(args[1].equals("-"))
        {
            SimulatorSocket.getInstance().sendString("set " + args[0] + " " + args[1] + args[2] );
        }
        else{
        SimulatorSocket.getInstance().sendString("set " + args[0] + " " + args[1] );
        }
        return 0;
    }
}
