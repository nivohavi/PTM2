package commands;

import client_side.Parser;
import client_side.SimulatorSocket;
import ex4.Simulator;

import java.io.IOException;
import java.net.Socket;

public class ConnectCommand implements Command {

    @Override
    public int doCommand(String[] args) throws IOException {
        SimulatorSocket.getInstance(args[0], Integer.parseInt(args[1]));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
