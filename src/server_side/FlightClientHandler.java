package server_side;

import client_side.Parser;

import java.io.*;

public class FlightClientHandler implements  ClientHandler {
    private volatile boolean stop;
    private int timeout;

    public FlightClientHandler(int timeout)
    {
        this.stop = false;
        this.timeout = timeout; // ms
    }

    @Override
    public void handleClient(InputStream in, OutputStream out) throws IOException {
        BufferedReader b_in = new BufferedReader(new InputStreamReader(in));
        String client_input;

        while (!stop && !(client_input = b_in.readLine()).equals("bye") ) {
            String[] vars = client_input.split(",");
            Parser.symbolTable.put(vars[0], vars[1]);

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopClient()
    {
        this.stop = true;
    }
}
