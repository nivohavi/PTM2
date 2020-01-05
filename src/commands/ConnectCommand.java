package commands;

import client_side.Parser;
import client_side.SimulatorSocket;

import java.io.IOException;
import java.net.Socket;

public class ConnectCommand implements Command {

    @Override
    public void doCommand(String[] args) throws IOException {
        SimulatorSocket.getInstance(args[0], Integer.parseInt(args[1]));
    }

    public static void stop()
    {
        //this._run = false;
        try
        {
            SimulatorSocket.getInstance().stop();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
