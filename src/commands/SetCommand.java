package commands;

import client_side.SimulatorSocket;

import java.io.IOException;

public class SetCommand implements Command {
    @Override
    public int doCommand(String[] args) throws IOException {
        SimulatorSocket.getInstance().sendString("set " + args[0] + " " + args[1] );
        return 0;
    }
}
