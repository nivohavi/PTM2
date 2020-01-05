package commands;

import server_side.ClientHandler;
import server_side.FlightClientHandler;
import server_side.MySerialServer;
import server_side.Server;

public class OpenDataServerCommand implements Command{

    @Override
    public void doCommand(String[] args) {
        ClientHandler fc = new FlightClientHandler(Integer.parseInt(args[2]));
        Server s1 = new MySerialServer();
        s1.start(Integer.parseInt(args[1]), fc);
    }
}
