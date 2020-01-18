package client_side;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SimulatorSocket {
    private static SimulatorSocket _instance = null;
    public static Socket socket = null;

    private SimulatorSocket(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
    }

    public synchronized static SimulatorSocket getInstance(String ip, int port) throws IOException {
        if (_instance == null)
            _instance = new SimulatorSocket(ip, port);

        return _instance;
    }

    public synchronized static SimulatorSocket getInstance() throws IOException {
        if (_instance == null)
            _instance = null;

        return _instance;
    }

    public void sendString(String message) throws IOException {
        OutputStream outstream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outstream);

        out.println(message);
        out.flush();
    }

    public void stop()
    {
        try
        {
            sendString("bye");
            this.socket.close();
        }

        catch (IOException e) {}
    }
}
