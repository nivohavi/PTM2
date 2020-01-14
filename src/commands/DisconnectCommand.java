package commands;

import client_side.Parser;
import client_side.SimulatorSocket;
import server_side.FlightClientHandler;
import server_side.MySerialServer;

import java.io.IOException;

public class DisconnectCommand implements Command {
    @Override
    public int doCommand(String[] array) {
        try {
            SimulatorSocket.getInstance().stop();
            FlightClientHandler.stop = true;
            if(Parser.socketToClose!=null)
                Parser.socketToClose.stop();
            Thread.sleep(1000);

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
