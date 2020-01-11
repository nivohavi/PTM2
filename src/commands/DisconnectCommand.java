package commands;

import client_side.SimulatorSocket;
import server_side.FlightClientHandler;

import java.io.IOException;

public class DisconnectCommand implements Command {
    @Override
    public int doCommand(String[] array) {
        try {
            SimulatorSocket.getInstance().stop();
            FlightClientHandler.stop = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
